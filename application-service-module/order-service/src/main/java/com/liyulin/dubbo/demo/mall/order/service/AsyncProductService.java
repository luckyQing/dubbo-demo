package com.liyulin.dubbo.demo.mall.order.service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Service;

import com.liyulin.dubbo.demo.mall.rpc.order.request.AsyncResultReqBody;
import com.liyulin.dubbo.demo.mall.rpc.order.response.AsyncResultRespBody;
import com.liyulin.dubbo.demo.mall.rpc.product.AsyncProductRpc;
import com.liyulin.dubbo.demo.mall.rpc.product.response.QryProductByIdRespBody;

/**
 * dubbo 异步调用实例
 * 
 * @author liyulin
 * @date 2019-09-05
 */
@Service
public class AsyncProductService {

	@Reference(validation = "true" , async = true )
	private AsyncProductRpc asyncProductRpc;

	public AsyncResultRespBody async(AsyncResultReqBody req) throws InterruptedException, ExecutionException {
		asyncProductRpc.qryById(req.getId());
		Future<QryProductByIdRespBody> productFuture = RpcContext.getContext().getFuture();
		
		asyncProductRpc.search(req.getSearch());
		Future<List<QryProductByIdRespBody>> productsFuture = RpcContext.getContext().getFuture();

		return AsyncResultRespBody.builder().product(productFuture.get()).products(productsFuture.get()).build();
	}

}