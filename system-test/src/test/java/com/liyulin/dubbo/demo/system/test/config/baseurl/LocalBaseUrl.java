package com.liyulin.dubbo.demo.system.test.config.baseurl;

public class LocalBaseUrl extends AbstractEnvBaseUrl {

	@Override
	public String getMallOrder() {
		return "http:127.0.0.1:10001";
	}

	@Override
	public String getMallProduct() {
		return "http:127.0.0.1:10002";
	}

}