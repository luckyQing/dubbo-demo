package com.liyulin.dubbo.demo.mall.order.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
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
public class AsyncProductService extends AbstractService {

	@Reference(validation = "true", async = true)
	private AsyncProductRpc asyncProductRpc;

	/**
	 * 通过{@link RpcContext}的方式异步调用
	 * 
	 * @param req
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public AsyncResultRespBody asyncByRpcContext(AsyncResultReqBody req)
			throws InterruptedException, ExecutionException {
		asyncProductRpc.qryById(req.getId());
		Future<QryProductByIdRespBody> productFuture = super.getDubboRpcFuture();

		asyncProductRpc.search(req.getSearch());
		Future<List<QryProductByIdRespBody>> productsFuture = super.getDubboRpcFuture();

		QryProductByIdRespBody qryProductByIdRespBody = productFuture.get();
		List<QryProductByIdRespBody> xx=productsFuture.get();
		
		return AsyncResultRespBody.builder()
				.product(qryProductByIdRespBody)
				.products(xx)
				.build();
	}

	/**
	 * 通过{@link CompletableFuture}的方式异步调用
	 * 
	 * @param req
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public AsyncResultRespBody asyncByCompletableFuture(AsyncResultReqBody req)
			throws InterruptedException, ExecutionException {
		CompletableFuture<QryProductByIdRespBody> productCompletableFuture = asyncProductRpc.asyncQryById(req.getId());
		
		CompletableFuture<List<QryProductByIdRespBody>> productsCompletableFuture = asyncProductRpc
				.asyncSearch(req.getSearch());

		return AsyncResultRespBody.builder().product(productCompletableFuture.get())
				.products(productsCompletableFuture.get()).build();
	}

}