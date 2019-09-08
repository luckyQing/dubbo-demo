package com.liyulin.dubbo.demo.mall.order.service;

import java.util.concurrent.Future;

import org.apache.dubbo.rpc.RpcContext;

public abstract class AbstractService {
	
	public <T> Future<T> getDubboRpcFuture(){
		return RpcContext.getContext().getFuture();
	}
	
}