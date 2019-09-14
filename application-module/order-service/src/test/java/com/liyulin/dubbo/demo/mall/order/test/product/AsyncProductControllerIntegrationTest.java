package com.liyulin.dubbo.demo.mall.order.test.product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.dubbo.rpc.RpcContext;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.agent.PowerMockAgent;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.TypeReference;
import com.liyulin.demo.sdk.test.AbstractIntegrationTest;
import com.liyulin.dubbo.demo.mall.order.service.AsyncProductService;
import com.liyulin.dubbo.demo.mall.rpc.order.request.AsyncResultReqBody;
import com.liyulin.dubbo.demo.mall.rpc.order.response.AsyncResultRespBody;
import com.liyulin.dubbo.demo.mall.rpc.product.AsyncProductRpc;
import com.liyulin.dubbo.demo.mall.rpc.product.request.ProductSearchReqBody;
import com.liyulin.dubbo.demo.mall.rpc.product.response.QryProductByIdRespBody;

public class AsyncProductControllerIntegrationTest extends AbstractIntegrationTest {

	@Mock
	private AsyncProductRpc asyncProductRpc;
	@Autowired
	@InjectMocks
	private AsyncProductService asyncProductService;
	@Rule
	public PowerMockRule rule = new PowerMockRule();

	@Before
	public void setup() {
		PowerMockAgent.initializeIfNeeded();
	}
	
	@Test
	@PrepareForTest(RpcContext.class)
	public void testAsyncByRpcContext() throws Exception {
		PowerMockito.mockStatic(RpcContext.class);
		PowerMockito.when(RpcContext.getContext()).thenReturn(PowerMockito.mock(RpcContext.class));
		// 用于判断qryById、search的返回值
		AtomicInteger counter = new AtomicInteger(0);
		PowerMockito.when(RpcContext.getContext().getFuture()).then((invocation)->{
			// stubbing qryById
			if(counter.getAndIncrement()==0) {
				return CompletableFuture.completedFuture(new QryProductByIdRespBody());
			}
			
			// stubbing search
			List<QryProductByIdRespBody> mockProducts = new ArrayList<>();
			mockProducts.add(QryProductByIdRespBody.builder().name("test").price(3L).build());
			return CompletableFuture.completedFuture(mockProducts);
		});
		
		ProductSearchReqBody searchReqBody = ProductSearchReqBody.builder().name("phone").price(3L).build();
		AsyncResultReqBody asyncResultReqBody = AsyncResultReqBody.builder().id(1L).search(searchReqBody).build();
		AsyncResultRespBody asyncResultRespBody = super.post("/order/asyncProduct/asyncByRpcContext", asyncResultReqBody,
				new TypeReference<AsyncResultRespBody>() {
				});
		Assertions.assertThat(asyncResultRespBody).isNotNull();
		Assertions.assertThat(asyncResultRespBody.getProduct()).isNotNull();
		Assertions.assertThat(asyncResultRespBody.getProducts()).isNotEmpty();
	}

	@Test
	public void testAsyncByCompletableFuture() throws Exception {
		// stubbing asyncQryById
		Mockito.when(asyncProductRpc.asyncQryById(Mockito.anyLong())).thenReturn(CompletableFuture.completedFuture(new QryProductByIdRespBody()));

		// stubbing asyncSearch
		List<QryProductByIdRespBody> mockProducts = new ArrayList<>();
		mockProducts.add(QryProductByIdRespBody.builder().name("test").price(3L).build());
		Mockito.when(asyncProductRpc.asyncSearch(Mockito.any())).thenReturn(CompletableFuture.completedFuture(mockProducts));
		
		ProductSearchReqBody searchReqBody = ProductSearchReqBody.builder().name("phone").price(3L).build();
		AsyncResultReqBody asyncResultReqBody = AsyncResultReqBody.builder().id(1L).search(searchReqBody).build();
		AsyncResultRespBody asyncResultRespBody = super.post("/order/asyncProduct/asyncByCompletableFuture", asyncResultReqBody,
				new TypeReference<AsyncResultRespBody>() {
				});
		Assertions.assertThat(asyncResultRespBody).isNotNull();
		Assertions.assertThat(asyncResultRespBody.getProduct()).isNotNull();
		Assertions.assertThat(asyncResultRespBody.getProducts()).isNotEmpty();
	}
	
}