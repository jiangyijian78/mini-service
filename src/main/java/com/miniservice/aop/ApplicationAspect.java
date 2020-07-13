package com.miniservice.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Order(0)
public class ApplicationAspect {
	Logger log = LoggerFactory.getLogger(ApplicationAspect.class);

	@Pointcut("execution(public * com.miniservice.controller.*.*(..))")
	public void controllerLayer() {
		// no implementation needed
	}

	@Pointcut("execution(public * com.miniservice.service.*.*(..))")
	public void serviceLayer() {
		// no implementation needed
	}

	@Pointcut("execution(public * com.miniservice.domain.dto.*.*(..))")
	public void repositoryLayer() {
		// no implementation needed
	}

	@Pointcut("execution(public * com.miniservice.support..*.*(..))")
	public void supportLayer() {
		// no implementation needed
	}

	@Before("controllerLayer() || serviceLayer() || repositoryLayer() || supportLayer()")
	public void logEnterLayers(JoinPoint joinPoint) {
		log.info("Enter {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
	}

	@After("controllerLayer() || serviceLayer() || repositoryLayer() || supportLayer()")
	public void logExitLayers(JoinPoint joinPoint) {
		log.info("Exit {}.{}() with arguments = {}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
	}

	@AfterThrowing(pointcut = "controllerLayer() || serviceLayer() || repositoryLayer() || supportLayer()", throwing = "t")
	public void logException(JoinPoint joinPoint, Throwable t) {
		log.error("Exception caught when executing: {}.{} with arguments = {}, Exception = {}",
				joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
				Arrays.toString(joinPoint.getArgs()), t.getMessage());
		log.error(t.getMessage());
	}

	@AfterReturning(pointcut = "controllerLayer() || serviceLayer() || repositoryLayer() || supportLayer()", returning = "retValue")
	public void logReturning(JoinPoint joinPoint, Object retValue) {
		log.info("Returning from executing: {}.{} with arguments = {}, Return = {}",
				joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
				Arrays.toString(joinPoint.getArgs()), retValue);
	}

	@Around("repositoryLayer()")
	public Object logTime(ProceedingJoinPoint point) throws Throwable {
		Long start = System.currentTimeMillis();
		Object retVal = point.proceed();
		Long end = System.currentTimeMillis();
		log.info("{}.{} use time in mills: {}", point.getSignature().getDeclaringTypeName(),
				point.getSignature().getName(), end - start);
		return retVal;
	}

}
