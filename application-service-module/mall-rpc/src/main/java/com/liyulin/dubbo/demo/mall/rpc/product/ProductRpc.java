package com.liyulin.dubbo.demo.mall.rpc.product;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.liyulin.dubbo.demo.mall.rpc.product.request.ProductSearchReqBody;
import com.liyulin.dubbo.demo.mall.rpc.product.response.QryProductByIdRespBody;

@Validated
public interface ProductRpc {

	QryProductByIdRespBody qryById(@NotNull Long id);
	
	List<QryProductByIdRespBody> search(@NotNull @Valid ProductSearchReqBody req);
	
}