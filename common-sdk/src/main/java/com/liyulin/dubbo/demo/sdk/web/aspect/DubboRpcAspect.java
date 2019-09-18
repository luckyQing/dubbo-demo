package com.liyulin.dubbo.demo.sdk.web.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.liyulin.dubbo.demo.sdk.web.aspect.pojo.LogAspectDO;

import lombok.extern.slf4j.Slf4j;

/**
 * @desc dubbo rpc接口日志切面
 * @author liyulin
 * @date 2019/09/15
 */
@Aspect
@Component
@Slf4j
public class DubboRpcAspect {
	
	@Pointcut("@within(org.apache.dubbo.config.annotation.Service)")
	public void dobboRpcPointCut() {
	}

	@Around("dobboRpcPointCut()")
	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
		// 请求前
		LogAspectDO logDO = new LogAspectDO();
		logDO.setReqStartTime(new Date());
		
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		logDO.setReqParams(joinPoint.getArgs());

		String classMethod = method.getDeclaringClass().getTypeName() + "." + method.getName();
		logDO.setClassMethod(classMethod);

		// 处理请求
		Object result = joinPoint.proceed();
		// 正常请求后
		logDO.setReqEndTime(new Date());
		logDO.setReqDealTime(getReqDealTime(logDO));
		logDO.setRespData(result);

		log.info("rpc.logDO=>{}", JSON.toJSONString(logDO));
		return result;
	}

	private final int getReqDealTime(LogAspectDO logDO) {
		return (int) (logDO.getReqEndTime().getTime() - logDO.getReqStartTime().getTime());
	}

}