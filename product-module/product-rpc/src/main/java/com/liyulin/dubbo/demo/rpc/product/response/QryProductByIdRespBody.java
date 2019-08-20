package com.liyulin.dubbo.demo.rpc.product.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class QryProductByIdRespBody {
	private Long id;
	private String name;
	private Long price;
}