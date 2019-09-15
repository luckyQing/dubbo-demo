package com.liyulin.dubbo.demo.system.test.order;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.alibaba.fastjson.TypeReference;
import com.liyulin.dubbo.demo.mall.rpc.product.request.ProductSearchReqBody;
import com.liyulin.dubbo.demo.mall.rpc.product.response.QryProductByIdRespBody;
import com.liyulin.dubbo.demo.sdk.test.AbstractSystemTest;
import com.liyulin.dubbo.demo.sdk.util.HttpUtil;
import com.liyulin.dubbo.demo.system.test.config.SystemTestConfig;

public class ProductControllerSystemTest extends AbstractSystemTest {
	
	@Test 
	public void testQryById() throws Exception { 
		String url = SystemTestConfig.ENV_BASE_URL.getMallOrder()+"product/qryById?id=1";
		QryProductByIdRespBody product = HttpUtil.get(url, new TypeReference<QryProductByIdRespBody>() {
		});
		Assertions.assertThat(product).isNotNull();
	  }

	@Test
	public void testSearch() throws Exception {
		String url = SystemTestConfig.ENV_BASE_URL.getMallOrder()+"product/search";
		ProductSearchReqBody productSearchReqBody = ProductSearchReqBody.builder().name("phone").price(3L).build();
		List<QryProductByIdRespBody> products = HttpUtil.postWithRaw(url, productSearchReqBody, new TypeReference<List<QryProductByIdRespBody>>() {
		});
		Assertions.assertThat(products).isNotEmpty();
	}

}