package com.liyulin.dubbo.demo.order.provider.service;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import com.liyulin.dubbo.demo.rpc.product.ProductRpc;
import com.liyulin.dubbo.demo.rpc.product.response.QryProductByIdRespBody;

@Service
public class ProductService {

	@Reference
	private ProductRpc productRpc;

	public QryProductByIdRespBody qryById(Long id) {
		return productRpc.qryById(id);
	}

}