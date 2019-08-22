package com.liyulin.dubbo.demo.product.service;

import org.apache.dubbo.config.annotation.Service;

import com.liyulin.dubbo.demo.rpc.product.ProductRpc;
import com.liyulin.dubbo.demo.rpc.product.response.QryProductByIdRespBody;

@Service(interfaceClass = ProductRpc.class)
public class ProductRpcService implements ProductRpc {

	@Override
	public QryProductByIdRespBody qryById(Long id) {
		return QryProductByIdRespBody.builder().id(id).name("test" + id).price(123L).build();
	}

}