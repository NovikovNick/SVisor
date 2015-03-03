package ru.nick.aop;

import java.util.Formatter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Класс отвечает за логирование всего приложения
 * 
 * @author NovikovNick
 *
 */
@Aspect
public class LoggingAspect {

    // private Logger LOG = Logger.getLogger(getClass());

    private int counter = 0;
    private StringBuilder last = new StringBuilder();

    @Before("execution(* ru.nick.bo.impl.*.*(..))")
    public void logBoImpl(JoinPoint jp) {
        print(jp);
    }

    @Before("execution(* ru.nick.dao.impl.*.*(..))")
    public void logDaoImpl(JoinPoint jp) {
        print(jp);
    }

    @Before("execution(* ru.nick.managedbean.*.*(..))")
    public void logBean(JoinPoint jp) {
        print(jp);
    }

    @Before("execution(* ru.nick.managedbean.converter.*.*(..))")
    public void logBeanConverter(JoinPoint jp) {
        print(jp);
    }

    @Before("execution(* ru.nick.model.*.*(..))")
    public void logModel(JoinPoint jp) {
        print(jp);
    }

    @Before("execution(* ru.nick.security.*.*(..))")
    public void logSecurity(JoinPoint jp) {
        print(jp);
    }

    @Before("execution(* ru.nick.util.*.*(..))")
    public void logUtil(JoinPoint jp) {
        print(jp);
    }

    /**
     * @param jp
     */
    private void print(JoinPoint jp) {
        StringBuilder res = new StringBuilder();
        putName(jp, res);
        putArgs(jp, res);
        print(res);
    }

    /**
     * @param jp
     * @param res
     */
    private void putName(JoinPoint jp, StringBuilder res) {
        Formatter f = new Formatter();
        res.append(f.format("%20s %40s", jp.getTarget().getClass().getSimpleName(),
                jp.getSignature().toShortString()).toString());
        f.close();
    }

    /**
     * @param res
     */
    private void print(StringBuilder res) {
        if (res.toString().equals(last.toString())) {
            counter++;
        } else {
            System.out.print("[" + counter + "] \n" + res.toString());
            counter = 0;
            last = res;
        }
    }

    /**
     * @param jp
     * @param res
     */
    private void putArgs(JoinPoint jp, StringBuilder res) {
        Object[] obj = jp.getArgs();
        if (obj.length > 0) {
            res.append("\n                                         args:");
            for (int i = 0; i < obj.length; i++) {
                res.append("\n                                             " + obj[i]);
            }
        }
    }

}
