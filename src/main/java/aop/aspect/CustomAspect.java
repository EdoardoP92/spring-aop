package aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomAspect {
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomAspect.class);
	
	@Before("execution(* aop.service.FooService+.voidAspectPoc(..))")
	public void beforeFooServiceVoidPoc(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		LOG.info("before method called: {}", methodName);
	}
	
	@After("execution(* aop.service.FooService+.voidAspectPoc(..))")
	public void afterFooServiceVoidPoc(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		LOG.info("after method called: {}", methodName);
	}
	
	/*
	 * This type of advice is executed only if the target method executed successfully 
	 * and does not end by throwing an exception.
	 */
	@AfterReturning("execution(* aop.service.FooService+.voidAspectPoc(..))")
	public void afterReturningFooServiceVoidPoc(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		LOG.info("after returning method called: {}", methodName);
	}
	
	/*
	 * This advice is executed only when when the target method ends by throwing an exception.
	 */
	@AfterThrowing("execution(* aop.service.FooService+.voidAspectPoc(..))")
	public void afterThrowingFooServiceVoidPoc(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		LOG.info("after throwing method called: {}", methodName);
	}
	
	/*
	 * The around advice is the most powerful type of advice, 
	 * because it encapsulates the target method and has control over its execution, 
	 * meaning that the advice decides whether the target method is called, and if so, when.
	*/
	@Around("execution(* aop.service.FooService+.voidAspectPoc(..))")
	public void aroundFooServiceVoidPoc(ProceedingJoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		try {
			joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		LOG.info("around method called: {}", methodName);
	}
}
