package com.zgy.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年12月27日 下午11:22:54
*/
@Aspect
@Component
public class MethodExecutionTime {
	private final static Logger logger = Logger.getLogger(MethodExecutionTime.class);
	//private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MethodExecutionTime.class);
	@Around("execution(* com.zgy.data.dao.Impl.EntityManagerDaoImpl.*(..))")
	public Object methodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{
		long startTime = System.currentTimeMillis();
		/*Object[] args = joinPoint.getArgs();
		if (args.length>0) {
			for(Object arg : args) {
				logger.info("Arg:"+arg.getClass());
			}
		}*/
	    Object object = joinPoint.proceed();
		long endTime = System.currentTimeMillis();
		logger.info(joinPoint.getTarget()+"->"+joinPoint.getSignature().getName()+":"+String.valueOf(endTime-startTime)+"ms");
		//logger.info(joinPoint.getTarget()+"---->>>>"+joinPoint.getSignature().getName()+"使用的秒数为:"+(endTime - startTime)/1000);
		
		return object;
	}
}
