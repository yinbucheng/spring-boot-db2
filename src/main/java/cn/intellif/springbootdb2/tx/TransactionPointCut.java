package cn.intellif.springbootdb2.tx;


import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

public class TransactionPointCut implements Pointcut {
    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> aClass) {
                if(aClass.getAnnotation(Service.class)!=null)
                    return true;
                return false;
            }
        };
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return new MethodMatcher() {
            @Override
            public boolean matches(Method method, @Nullable Class<?> aClass) {
                return canApply(aClass,method);
            }

            @Override
            public boolean isRuntime() {
                return true;
            }

            @Override
            public boolean matches(Method method, @Nullable Class<?> aClass, Object... objects) {
                return canApply(aClass,method);
            }
        };
    }

    private boolean canApply(Class clazz,Method method){
        Transactional2 transactional2 = method.getAnnotation(Transactional2.class);
        if(transactional2==null){
            Class aClass = clazz.getInterfaces()[0];
            try {
                if(sourceMethod(method))
                    return false;
                transactional2 = aClass.getMethod(method.getName(), method.getParameterTypes()).getAnnotation(Transactional2.class);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
        if(transactional2==null)
            return false;
        return true;
    }

    //如果为Object对象中的方法忽略
    private boolean sourceMethod(Method method){
        String methodName = method.getName();
        Method[] methods = Object.class.getDeclaredMethods();
        for(Method method1:methods){
            if(method1.getName().equals(methodName))
                return true;
        }
        return false;
    }
}
