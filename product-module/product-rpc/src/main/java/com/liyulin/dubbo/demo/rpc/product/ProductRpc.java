package com.liyulin.dubbo.demo.rpc.product;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.liyulin.dubbo.demo.rpc.product.response.QryProductByIdRespBody;

@Validated
public interface ProductRpc {

	QryProductByIdRespBody qryById(@NotNull Long id);
	
}