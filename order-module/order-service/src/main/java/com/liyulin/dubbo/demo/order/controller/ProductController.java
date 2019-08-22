package com.liyulin.dubbo.demo.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liyulin.dubbo.demo.order.service.ProductService;
import com.liyulin.dubbo.demo.rpc.product.response.QryProductByIdRespBody;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public QryProductByIdRespBody qryById(Long id) {
		return productService.qryById(id);
	}
	
}