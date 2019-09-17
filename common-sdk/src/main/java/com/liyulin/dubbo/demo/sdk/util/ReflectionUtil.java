package com.liyulin.dubbo.demo.sdk.util;

import java.util.Set;

import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.springframework.context.annotation.Condition;

import com.liyulin.dubbo.demo.sdk.config.PackageConfig;

import lombok.experimental.UtilityClass;

/**
 * {@link Condition}工具类
 *
 * @author liyulin
 * @date 2019-04-27
 */
@UtilityClass
public class ReflectionUtil extends ReflectionUtils {

	private static Reflections reflections = null;
	static {
		reflections = new Reflections(PackageConfig.PACKAGE_PREFIX);
	}

	/**
	 * 根据父类类型获取所有子类类型
	 * 
	 * @param type
	 * @return
	 */
	public static <T> Set<Class<? extends T>> getSubTypesOf(final Class<T> type) {
		return reflections.getSubTypesOf(type);
	}

}