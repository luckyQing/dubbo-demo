package com.liyulin.dubbo.demo.rpc.product.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class ProductSearchReqBody implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String name;
	private Long price;

}