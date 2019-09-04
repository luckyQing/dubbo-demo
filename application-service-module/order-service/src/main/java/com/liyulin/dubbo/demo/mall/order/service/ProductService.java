package com.liyulin.dubbo.demo.mall.order.service;

import java.util.List;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import com.liyulin.dubbo.demo.mall.rpc.product.ProductRpc;
import com.liyulin.dubbo.demo.mall.rpc.product.request.ProductSearchReqBody;
import com.liyulin.dubbo.demo.mall.rpc.product.response.QryProductByIdRespBody;

@Service
public class ProductService {

	@Reference(validation = "true", async = true)
	private ProductRpc productRpc;

	public QryProductByIdRespBody qryById(Long id) {
		return productRpc.qryById(id);
	}

	public List<QryProductByIdRespBody> search(ProductSearchReqBody req) {
		return productRpc.search(req);
	}

}