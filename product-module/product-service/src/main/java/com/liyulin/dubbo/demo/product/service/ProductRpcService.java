package com.liyulin.dubbo.demo.product.service;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.validation.annotation.Validated;

import com.liyulin.dubbo.demo.rpc.product.ProductRpc;
import com.liyulin.dubbo.demo.rpc.product.response.QryProductByIdRespBody;

@Service(interfaceClass = ProductRpc.class, validation = "true")
@Validated
public class ProductRpcService implements ProductRpc, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public QryProductByIdRespBody qryById(@NotNull Long id) {
		return QryProductByIdRespBody.builder().id(id).name("test" + id).price(123L).build();
	}

}