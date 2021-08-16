package com.liyulin.dubbo.demo.sdk.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

@Activate(group = {CommonConstants.PROVIDER, CommonConstants.CONSUMER})
@Slf4j
public class DubboFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        log.debug("method={}", invocation.getMethodName());
        return invoker.invoke(invocation);
    }

}