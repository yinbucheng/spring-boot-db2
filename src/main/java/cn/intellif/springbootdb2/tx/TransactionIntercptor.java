package cn.intellif.springbootdb2.tx;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

public class TransactionIntercptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object value = null;
        String key = createKey(methodInvocation.getThis().getClass(),methodInvocation.getMethod());
        try {
            TransactionUtils.beginTransaction(key);
            methodInvocation.proceed();
            //提交事务
            TransactionUtils.commitTransaction(key);
        }catch (Exception e){
            //回滚事务
            TransactionUtils.rollbackTransaction(key);
            throw new RuntimeException(e);
        }finally {
            //释放资源
            TransactionUtils.releaseTransaction(key);
        }
        return value;
    }

    private String createKey(Class clazz,Method method){
        StringBuffer sb = new StringBuffer(clazz.getName()).append("-").append(method.getName());
        Class[] types = method.getParameterTypes();
        if(types!=null&&types.length>0){
            for(Class type:types){
                sb.append(type.getName()).append("-");
            }
        }
        return sb.toString();
    }
}
