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
import com.liyulin.dubbo.demo.sdk.web.aspect.dto.LogAspectDto;

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
		LogAspectDto logDto = new LogAspectDto();
		logDto.setReqStartTime(new Date());
		
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		logDto.setReqParams(joinPoint.getArgs());

		String classMethod = method.getDeclaringClass().getTypeName() + "." + method.getName();
		logDto.setClassMethod(classMethod);

		// 处理请求
		Object result = joinPoint.proceed();
		// 正常请求后
		logDto.setReqEndTime(new Date());
		logDto.setReqDealTime(getReqDealTime(logDto));
		logDto.setRespData(result);

		log.info("api.logDto.info=>{}", JSON.toJSONString(logDto));
		return result;
	}

	private final int getReqDealTime(LogAspectDto logDto) {
		return (int) (logDto.getReqEndTime().getTime() - logDto.getReqStartTime().getTime());
	}

}