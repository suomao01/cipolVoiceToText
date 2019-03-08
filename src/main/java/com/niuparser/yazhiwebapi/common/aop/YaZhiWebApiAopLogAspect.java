/*package com.niuparser.yazhiwebapi.common.aop;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.javassist.Modifier;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.niuparser.yazhiwebapi.util.com.JSONChangeUtil;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;


@Aspect
@Component
public class YaZhiWebApiAopLogAspect {

	protected static Logger logger = LoggerFactory.getLogger(YaZhiWebApiAopLogAspect.class);
	

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void webLog() {
    	
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        System.out.println( "进入doBefore切面");
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        
        String classType = joinPoint.getTarget().getClass().getName();
        Class<?> clazz = Class.forName(classType);
        String clazzName = clazz.getName();
        String methodName = joinPoint.getSignature().getName(); //获取方法名称
        Object[] args = joinPoint.getArgs();//参数
        Map<String, Object> nameAndArgs = getFieldsName(this.getClass(), clazzName, methodName, args);//获取被切参数名称及参数值
        if(nameAndArgs != null){
        	//logger.info("paramsJson : " + JSONChangeUtil.objToJson(nameAndArgs));
        }

    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
    	if(ret != null){
    		logger.info("RESPONSE : " + JSONChangeUtil.objToJson(ret));
    	}else{
    		logger.info("RESPONSE is null");
    	}
        
    }
    
    *//**
     * 通过反射机制 获取被切参数名以及参数值
     *
     * @param cls
     * @param clazzName
     * @param methodName
     * @param args
     * @return
     * @throws NotFoundException
     *//*
    private Map<String, Object> getFieldsName(Class cls, String clazzName, String methodName, Object[] args) throws NotFoundException {
        Map<String, Object> map = new HashMap<String, Object>();

        ClassPool pool = ClassPool.getDefault();
        //ClassClassPath classPath = new ClassClassPath(this.getClass());
        ClassClassPath classPath = new ClassClassPath(cls);
        pool.insertClassPath(classPath);

        CtClass cc = pool.get(clazzName);
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            // exception
        }
        // String[] paramNames = new String[cm.getParameterTypes().length];
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < cm.getParameterTypes().length; i++) {
            map.put(attr.variableName(i + pos), args[i]);//paramNames即参数名
        }
        return map;
    }
}
*/