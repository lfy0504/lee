package com.isabella.lee.aop;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class ServiceAop {
    @Pointcut("execution(* com.isabella.lee..*.service..*(..))")
    public void serviceAop(){}

    private final static Logger logger = LogManager.getLogger(ServiceAop.class);


    @Around("serviceAop()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //记录开始时间
        long beginTime = System.currentTimeMillis();
        Object requestPath;
        String ip;
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        if (sra != null) {
            HttpServletRequest request = sra.getRequest();
            // 获取请求地址
            requestPath = request.getRequestURI();
            ip = getIpAddr(request);
        } else {
            ip = "127.0.0.1";
            requestPath = "UnitTest";
        }

        //格式换开始时间
        String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //参数
        Object paramObj = joinPoint.getArgs();
        String param = StringUtils.isEmpty(paramObj) ? "无参数" : JSON.toJSONString(paramObj);
        //返回结果
        Object resultObj = joinPoint.proceed();
        String result = StringUtils.isEmpty(resultObj) ? "无返回值" : JSON.toJSONString(resultObj);
        //获取切点方法对象
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //获取类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取方法名
        String methodName = method.getName();

        logger.info("----------------------------------------------------------------------------------------------------------------\n");
        logger.info("[访问时间]>>>>>" + optTime);
        logger.info("[访问 IP]>>>>>" + ip);
        logger.info("[访问路由]>>>>>" + requestPath);
        logger.info("[访问方法]>>>>>" + className.concat(".").concat(methodName).concat("()"));
        logger.info("[传入参数]>>>>>" + param);
        logger.info("[返回参数]>>>>>" + result);
        logger.info("[耗费时间]>>>>>" + (System.currentTimeMillis() - beginTime) + " ms");
        logger.info("----------------------------------------------------------------------------------------------------------------\n");
        return resultObj;
    }


    @AfterThrowing(value = "serviceAop()",throwing = "ex")
    public void doRecoveryActions(JoinPoint joinPoint, Throwable ex) {
        long beginTime = System.currentTimeMillis();
        //格式换开始时间
        String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        // 获取请求地址
        Object requestPath = request.getRequestURI();
        String ip = getIpAddr(request);

        //参数
        Object paramObj = joinPoint.getArgs();
        String param = StringUtils.isEmpty(paramObj) ? "无参数" : JSON.toJSONString(paramObj);
        //获取切点方法对象
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //获取类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取方法名
        String methodName = method.getName();

        logger.error("\n");
        logger.error("----------------------------------------------------------------------------------------------------------------\n");
        logger.error("[异常时间]>>>>>" + optTime);
        logger.error("[访问 IP]>>>>>" + ip);
        logger.error("[访问路由]>>>>>" + requestPath);
        logger.error("[异常类型]>>>>>" + ex.getClass().getName());
        logger.error("[异常方法]>>>>>" + className.concat(".").concat(methodName).concat("()"));
        logger.error("[传入参数]>>>>>" + param);
        logger.error("[耗费时间]>>>>>" + (System.currentTimeMillis() - beginTime) + " ms");
        logger.error("----------------------------------------------------------------------------------------------------------------\n");
    }

    /**
     * 获取IP地址
     */
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
}
