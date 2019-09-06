package com.liyulin.dubbo.demo.mall.product.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.validation.annotation.Validated;

import com.liyulin.dubbo.demo.mall.rpc.product.AsyncProductRpc;
import com.liyulin.dubbo.demo.mall.rpc.product.request.ProductSearchReqBody;
import com.liyulin.dubbo.demo.mall.rpc.product.response.QryProductByIdRespBody;

@Service(interfaceClass = AsyncProductRpc.class, validation = "true")
@Validated
public class AsyncProductRpcService implements AsyncProductRpc, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public QryProductByIdRespBody qryById(@NotNull Long id) {
		return QryProductByIdRespBody.builder().id(id).name("test" + id).price(123L).build();
	}

	@Override
	public List<QryProductByIdRespBody> search(@NotNull @Valid ProductSearchReqBody req) {
		List<QryProductByIdRespBody> products = new ArrayList<>();
		for (long id = 1; id < 5; id++) {
			products.add(QryProductByIdRespBody.builder()
					.id(id)
					.name(req.getName() + id)
					.price(req.getPrice())
					.build());
		}
		return products;
	}

	@Override
	public CompletableFuture<QryProductByIdRespBody> asyncQryById(@NotNull Long id) {
		return CompletableFuture.supplyAsync(()->{
			return QryProductByIdRespBody.builder().id(id).name("test" + id).price(123L).build();
		});
	}

	@Override
	public CompletableFuture<List<QryProductByIdRespBody>> asyncSearch(@NotNull @Valid ProductSearchReqBody req) {
		return CompletableFuture.supplyAsync(()->{
			List<QryProductByIdRespBody> products = new ArrayList<>();
			for (long id = 1; id < 5; id++) {
				products.add(QryProductByIdRespBody.builder()
						.id(id)
						.name(req.getName() + id)
						.price(req.getPrice())
						.build());
			}
			return products;
		});
	}

}