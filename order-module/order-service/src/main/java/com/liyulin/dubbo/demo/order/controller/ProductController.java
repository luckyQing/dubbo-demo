package com.liyulin.dubbo.demo.order.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liyulin.dubbo.demo.order.service.ProductService;
import com.liyulin.dubbo.demo.rpc.product.request.ProductSearchReqBody;
import com.liyulin.dubbo.demo.rpc.product.response.QryProductByIdRespBody;

@RestController
@Validated
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public QryProductByIdRespBody qryById(@NotNull Long id) {
		return productService.qryById(id);
	}

	@PostMapping
	public List<QryProductByIdRespBody> search(@NotNull @Valid ProductSearchReqBody req) {
		return productService.search(req);
	}

}