package com.liyulin.dubbo.demo.order.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import com.liyulin.dubbo.demo.rpc.product.ProductRpc;
import com.liyulin.dubbo.demo.rpc.product.request.ProductSearchReqBody;
import com.liyulin.dubbo.demo.rpc.product.response.QryProductByIdRespBody;

@Service
public class ProductService {

	@Reference(validation = "true", async = true)
	private ProductRpc productRpc;

	public QryProductByIdRespBody qryById(Long id) {
		return productRpc.qryById(id);
	}

	public List<QryProductByIdRespBody> search(@NotNull ProductSearchReqBody req) {
		return productRpc.search(req);
	}

}