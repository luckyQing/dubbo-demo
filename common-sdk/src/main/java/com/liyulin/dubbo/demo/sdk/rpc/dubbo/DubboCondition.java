package com.liyulin.dubbo.demo.sdk.rpc.dubbo;

import java.util.Set;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.classreading.MethodMetadataReadingVisitor;

import com.liyulin.dubbo.demo.sdk.util.ReflectionUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DubboCondition implements Condition{

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		// 1、获取使用@FeignClient的interface的Class
		MethodMetadataReadingVisitor classMetadata = (MethodMetadataReadingVisitor) metadata;
		String interfaceClassName = classMetadata.getReturnTypeName();
		Class<?> interfaceClass = null;
		try {
			interfaceClass = Class.forName(interfaceClassName);
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage(), e);
		}

		// 2、获取interface对应的所有实现类
		Set<?> subTypes = ReflectionUtil.getSubTypesOf(interfaceClass);

		// 3、判断是否存在RPC interface的实现类，且实现类上有Controller、RestController注解
		if (subTypes == null || subTypes.isEmpty()) {
			return true;
		}
		// 遍历bean
		for (Object subType : subTypes) {
			Class<?> subTypeClass = (Class<?>) subType;
			boolean isRpcImplementClass = isRpcImplementClass(subTypeClass);
			if (isRpcImplementClass) {
				return false;
			}
		}

		return true;
	}

	private boolean isRpcImplementClass(Class<?> clazz) {
		return (AnnotationUtils.findAnnotation(clazz, Service.class) != null);
	}


}