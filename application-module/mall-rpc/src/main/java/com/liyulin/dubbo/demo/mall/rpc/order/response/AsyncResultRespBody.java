package com.liyulin.dubbo.demo.mall.rpc.order.response;

import java.io.Serializable;
import java.util.List;

import com.liyulin.dubbo.demo.mall.rpc.product.response.QryProductByIdRespBody;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class AsyncResultRespBody implements Serializable {

	private static final long serialVersionUID = 1L;

	private QryProductByIdRespBody product;
	private List<QryProductByIdRespBody> products;

}