package com.liyulin.dubbo.demo.mall.rpc.product.response;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class QryProductByIdRespBody implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Long price;
}