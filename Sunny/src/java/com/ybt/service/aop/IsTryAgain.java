package com.ybt.service.aop;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 *自定义注解 IsTryAgain  接口说明
 */
@Target({ElementType.TYPE,ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IsTryAgain{
	String description()  default "";
	String function()  default "";
}
