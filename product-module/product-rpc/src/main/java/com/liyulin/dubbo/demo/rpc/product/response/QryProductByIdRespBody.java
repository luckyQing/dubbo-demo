package com.liyulin.dubbo.demo.rpc.product.response;

import lombok.Data;

@Data
public class QryProductByIdRespBody {
	private Long id;
	private String name;
	private Long price;
}