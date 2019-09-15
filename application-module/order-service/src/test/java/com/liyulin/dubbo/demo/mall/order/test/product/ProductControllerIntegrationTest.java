package com.liyulin.dubbo.demo.mall.order.test.product;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;

import com.alibaba.fastjson.TypeReference;
import com.liyulin.dubbo.demo.mall.order.service.ProductService;
import com.liyulin.dubbo.demo.mall.rpc.product.ProductRpc;
import com.liyulin.dubbo.demo.mall.rpc.product.request.ProductSearchReqBody;
import com.liyulin.dubbo.demo.mall.rpc.product.response.QryProductByIdRespBody;
import com.liyulin.dubbo.demo.sdk.test.AbstractIntegrationTest;

public class ProductControllerIntegrationTest extends AbstractIntegrationTest {

	@Mock
	private ProductRpc productRpc;
	@Autowired
	@InjectMocks
	private ProductService productService;
	
	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testQryById() throws Exception {
		Mockito.when(productRpc.qryById(Mockito.anyLong())).thenReturn(new QryProductByIdRespBody());
		
		LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("id", "1");
		QryProductByIdRespBody product = super.get("/order/product/qryById", params,
				new TypeReference<QryProductByIdRespBody>() {
				});
		Assertions.assertThat(product).isNotNull();
	}

	@Test
	public void testSearch() throws Exception {
		List<QryProductByIdRespBody> mockProducts = new ArrayList<>();
		mockProducts.add(QryProductByIdRespBody.builder().name("test").price(3L).build());
		Mockito.when(productRpc.search(Mockito.any())).thenReturn(mockProducts);
		
		ProductSearchReqBody productSearchReqBody = ProductSearchReqBody.builder().name("phone").price(3L).build();
		List<QryProductByIdRespBody> products = super.post("/order/product/search", productSearchReqBody,
				new TypeReference<List<QryProductByIdRespBody>>() {
				});
		Assertions.assertThat(products).isNotEmpty();
	}

}