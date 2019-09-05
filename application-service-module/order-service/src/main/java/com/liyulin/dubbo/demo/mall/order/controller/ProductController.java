package com.liyulin.dubbo.demo.mall.order.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liyulin.dubbo.demo.mall.order.service.ProductService;
import com.liyulin.dubbo.demo.mall.rpc.product.request.ProductSearchReqBody;
import com.liyulin.dubbo.demo.mall.rpc.product.response.QryProductByIdRespBody;

@RestController
@RequestMapping("order/product")
@Validated
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("qryById")
	public QryProductByIdRespBody qryById(@NotNull Long id) throws InterruptedException, ExecutionException {
		return productService.qryById(id);
	}

	@PostMapping("search")
	public List<QryProductByIdRespBody> search(@RequestBody @NotNull @Valid ProductSearchReqBody req) {
		return productService.search(req);
	}

}