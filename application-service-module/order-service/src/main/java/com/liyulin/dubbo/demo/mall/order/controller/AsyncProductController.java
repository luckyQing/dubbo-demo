package com.liyulin.dubbo.demo.mall.order.controller;

import java.util.concurrent.ExecutionException;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liyulin.dubbo.demo.mall.order.service.AsyncProductService;
import com.liyulin.dubbo.demo.mall.rpc.order.request.AsyncResultReqBody;
import com.liyulin.dubbo.demo.mall.rpc.order.response.AsyncResultRespBody;

@RestController
@RequestMapping("order/asyncProduct")
@Validated
public class AsyncProductController {

	@Autowired
	private AsyncProductService asyncProductService;

	@PostMapping("async")
	public AsyncResultRespBody async(@RequestBody @NotNull AsyncResultReqBody req) throws InterruptedException, ExecutionException {
		return asyncProductService.async(req);
	}

}