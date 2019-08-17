package com.liyulin.dubbo.demo.rpc.product;

import com.liyulin.dubbo.demo.rpc.product.response.QryProductByIdRespBody;

public interface ProductRpc {

	QryProductByIdRespBody qryById(Long id);
	
}