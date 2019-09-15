package com.liyulin.dubbo.demo.mall.product.test.rpc;

import java.util.List;

import org.apache.dubbo.config.annotation.Reference;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.liyulin.dubbo.demo.mall.rpc.product.AsyncProductRpc;
import com.liyulin.dubbo.demo.mall.rpc.product.request.ProductSearchReqBody;
import com.liyulin.dubbo.demo.mall.rpc.product.response.QryProductByIdRespBody;
import com.liyulin.dubbo.demo.sdk.test.AbstractIntegrationTest;

public class AsyncProductRpcServiceIntegrationTest extends AbstractIntegrationTest {

	@Reference(validation = "true")
	private AsyncProductRpc asyncProductRpc;
	
	@Test
	public void testQryById() {
		QryProductByIdRespBody product = asyncProductRpc.qryById(1L);
		Assertions.assertThat(product).isNotNull();
	}
	
	@Test
	public void testSearch() throws Exception {
		ProductSearchReqBody productSearchReqBody = ProductSearchReqBody.builder().name("phone").price(3L).build();
		List<QryProductByIdRespBody> products = asyncProductRpc.search(productSearchReqBody);
		
		Assertions.assertThat(products).isNotEmpty();
	}
	
}