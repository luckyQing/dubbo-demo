package com.liyulin.dubbo.demo.mall.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liyulin.dubbo.demo.mall.rpc.product.ProductRpc;
import com.liyulin.dubbo.demo.mall.rpc.product.request.ProductSearchReqBody;
import com.liyulin.dubbo.demo.mall.rpc.product.response.QryProductByIdRespBody;

@Service
public class ProductService {

	@Autowired
	private ProductRpc productRpc;

	public QryProductByIdRespBody qryById(Long id) {
		return productRpc.qryById(id);
	}

	public List<QryProductByIdRespBody> search(ProductSearchReqBody req) {
		return productRpc.search(req);
	}

}