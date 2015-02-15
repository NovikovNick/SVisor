package ru.nick.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {

	private Logger LOG = Logger.getLogger(getClass());

	//@Before("execution(* ru.nick.dao.*.*(..))") public void logDao(JoinPoint jp) {	print(jp);	}
	@Before("execution(* ru.nick.dao.impl.*.*(..))") public void logDaoImpl(JoinPoint jp) {	print(jp);	}
	@Before("execution(* ru.nick.managedbean.*.*(..))") public void logBean(JoinPoint jp) {	print(jp);	}
	@Before("execution(* ru.nick.managedbean.converter.*.*(..))") public void logBeanConverter(JoinPoint jp) {	print(jp);	}
	@Before("execution(* ru.nick.model.*.*(..))") public void logModel(JoinPoint jp) {	print(jp);	}
	@Before("execution(* ru.nick.security.*.*(..))") public void logSecurity(JoinPoint jp) {	print(jp);	}
	@Before("execution(* ru.nick.util.*.*(..))") public void logUtil(JoinPoint jp) {	print(jp);	}
	


	/**
	 * @param jp
	 */
	private void print(JoinPoint jp) {
		//System.out.println("Sys.out-> " + jp.toShortString());
		System.out.println("SVisor: " + jp.getSignature());

		Object[] obj = jp.getArgs();
		if (obj.length > 0) {
			System.out.println("    args:");
			for (int i = 0; i < obj.length; i++) {
				System.out.println("        :" + obj[i]);
			}
		}
		

	}

}
