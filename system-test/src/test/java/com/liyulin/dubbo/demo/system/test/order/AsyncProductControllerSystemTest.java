package com.liyulin.dubbo.demo.system.test.order;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.alibaba.fastjson.TypeReference;
import com.liyulin.dubbo.demo.mall.rpc.order.request.AsyncResultReqBody;
import com.liyulin.dubbo.demo.mall.rpc.order.response.AsyncResultRespBody;
import com.liyulin.dubbo.demo.mall.rpc.product.request.ProductSearchReqBody;
import com.liyulin.dubbo.demo.sdk.test.AbstractSystemTest;
import com.liyulin.dubbo.demo.sdk.util.HttpUtil;
import com.liyulin.dubbo.demo.system.test.config.SystemTestConfig;

public class AsyncProductControllerSystemTest extends AbstractSystemTest {

	@Test
	public void testAsyncByRpcContext() throws Exception {
		String url = SystemTestConfig.ENV_BASE_URL.getMallOrder()+"asyncProduct/asyncByRpcContext";
		
		ProductSearchReqBody searchReqBody = ProductSearchReqBody.builder().name("phone").price(3L).build();
		AsyncResultReqBody asyncResultReqBody = AsyncResultReqBody.builder().id(1L).search(searchReqBody).build();
		
		AsyncResultRespBody asyncResultRespBody = HttpUtil.postWithRaw(url, asyncResultReqBody, new TypeReference<AsyncResultRespBody>() {
		});
		Assertions.assertThat(asyncResultRespBody).isNotNull();
		Assertions.assertThat(asyncResultRespBody.getProduct()).isNotNull();
		Assertions.assertThat(asyncResultRespBody.getProducts()).isNotEmpty();
	}
	
	@Test
	public void testAsyncByCompletableFuture() throws Exception {
		String url = SystemTestConfig.ENV_BASE_URL.getMallOrder()+"asyncProduct/asyncByCompletableFuture";
		
		ProductSearchReqBody searchReqBody = ProductSearchReqBody.builder().name("phone").price(3L).build();
		AsyncResultReqBody asyncResultReqBody = AsyncResultReqBody.builder().id(1L).search(searchReqBody).build();
		
		AsyncResultRespBody asyncResultRespBody = HttpUtil.postWithRaw(url, asyncResultReqBody, new TypeReference<AsyncResultRespBody>() {
		});
		Assertions.assertThat(asyncResultRespBody).isNotNull();
		Assertions.assertThat(asyncResultRespBody.getProduct()).isNotNull();
		Assertions.assertThat(asyncResultRespBody.getProducts()).isNotEmpty();
	}
	
}