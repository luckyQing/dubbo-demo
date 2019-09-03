package com.liyulin.dubbo.demo.rpc.product;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.liyulin.dubbo.demo.rpc.product.request.ProductSearchReqBody;
import com.liyulin.dubbo.demo.rpc.product.response.QryProductByIdRespBody;

@Validated
public interface ProductRpc {

	QryProductByIdRespBody qryById(@NotNull Long id);
	
	List<QryProductByIdRespBody> search(@NotNull @Valid ProductSearchReqBody req);
	
}