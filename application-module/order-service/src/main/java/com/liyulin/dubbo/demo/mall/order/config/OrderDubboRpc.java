package com.liyulin.dubbo.demo.mall.order.config;

import org.apache.dubbo.config.spring.ReferenceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import com.liyulin.dubbo.demo.mall.rpc.product.AsyncProductRpc;
import com.liyulin.dubbo.demo.mall.rpc.product.ProductRpc;
import com.liyulin.dubbo.demo.sdk.rpc.dubbo.DubboCondition;

@Configuration
public class OrderDubboRpc {

	@Bean
	@Conditional(DubboCondition.class)
	public ReferenceBean<ProductRpc> productRpc(){
		ReferenceBean<ProductRpc> productRpc = new ReferenceBean<ProductRpc>();
		productRpc.setValidation("true");
		return productRpc;
	}
	
	@Bean
	@Conditional(DubboCondition.class)
	public ReferenceBean<AsyncProductRpc> asyncProductRpc(){
		ReferenceBean<AsyncProductRpc> asyncProductRpc = new ReferenceBean<AsyncProductRpc>();
		asyncProductRpc.setValidation("true");
		asyncProductRpc.setAsync(true);
		return asyncProductRpc;
	}

}