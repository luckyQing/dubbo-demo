package com.liyulin.dubbo.demo.mall.order.test.product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import com.alibaba.fastjson.TypeReference;
import com.liyulin.demo.sdk.test.AbstractIntegrationTest;
import com.liyulin.dubbo.demo.mall.order.controller.AsyncProductController;
import com.liyulin.dubbo.demo.mall.order.service.AsyncProductService;
import com.liyulin.dubbo.demo.mall.rpc.order.request.AsyncResultReqBody;
import com.liyulin.dubbo.demo.mall.rpc.order.response.AsyncResultRespBody;
import com.liyulin.dubbo.demo.mall.rpc.product.AsyncProductRpc;
import com.liyulin.dubbo.demo.mall.rpc.product.request.ProductSearchReqBody;
import com.liyulin.dubbo.demo.mall.rpc.product.response.QryProductByIdRespBody;

public class AsyncProductControllerIntegrationTest extends AbstractIntegrationTest {

	@Test
	public void testAsyncByRpcContext() throws Exception {
		// FIXME:
		AsyncProductService asyncProductService = applicationContext.getBean(AsyncProductService.class);
		AsyncProductService asyncProductServiceSpy = Mockito.spy(asyncProductService);
		
		AsyncProductRpc asyncProductRpc = Mockito.mock(AsyncProductRpc.class);
		setMockAttribute(asyncProductServiceSpy, asyncProductRpc);
		
		AsyncProductController asyncProductController = applicationContext.getBean(AsyncProductController.class);
		setMockAttribute(asyncProductController, asyncProductServiceSpy);
		
		// stubbing qryById
		Mockito.when(asyncProductRpc.qryById(Mockito.anyLong())).thenReturn(new QryProductByIdRespBody());
		Mockito.when(asyncProductServiceSpy.getDubboRpcFuture()).thenReturn(CompletableFuture.completedFuture(new QryProductByIdRespBody()));
		
		// stubbing search
		List<QryProductByIdRespBody> mockProducts = new ArrayList<>();
		mockProducts.add(QryProductByIdRespBody.builder().name("test").price(3L).build());
		
		Mockito.when(asyncProductRpc.search(Mockito.any())).thenReturn(mockProducts);
		Mockito.when(asyncProductServiceSpy.getDubboRpcFuture()).thenReturn(CompletableFuture.completedFuture(mockProducts));
		
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
		AsyncProductRpc asyncProductRpc = Mockito.mock(AsyncProductRpc.class);
		AsyncProductService asyncProductService = applicationContext.getBean(AsyncProductService.class);
		setMockAttribute(asyncProductService, asyncProductRpc);
		
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