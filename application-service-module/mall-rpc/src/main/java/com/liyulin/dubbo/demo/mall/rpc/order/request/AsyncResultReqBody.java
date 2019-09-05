package com.liyulin.dubbo.demo.mall.rpc.order.request;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.liyulin.dubbo.demo.mall.rpc.product.request.ProductSearchReqBody;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class AsyncResultReqBody implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotNull
	private Long id;
	
	@NotNull
	@Valid
	private ProductSearchReqBody search;

}