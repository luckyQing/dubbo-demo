package com.liyulin.dubbo.demo.system.test.config;

import com.liyulin.dubbo.demo.system.test.config.baseurl.AbstractEnvBaseUrl;
import com.liyulin.dubbo.demo.system.test.config.baseurl.LocalBaseUrl;

public class SystemTestConfig {
	public static final AbstractEnvBaseUrl ENV_BASE_URL = new LocalBaseUrl();
}