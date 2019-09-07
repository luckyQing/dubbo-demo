package com.liyulin.dubbo.demo.mall.order.test.product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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
	
	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
	
	// FIXME:
	@Test
	public void testAsyncByRpcContext() throws Exception {
		// stubbing qryById
		Mockito.when(asyncProductRpc.qryById(Mockito.anyLong())).thenReturn(new QryProductByIdRespBody());

		// stubbing search
		List<QryProductByIdRespBody> mockProducts = new ArrayList<>();
		mockProducts.add(QryProductByIdRespBody.builder().name("test").price(3L).build());
		Mockito.when(asyncProductRpc.search(Mockito.any())).thenReturn(mockProducts);
		
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