package cn.intellif.springbootdb2.tx;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;
import org.springframework.stereotype.Component;

@Component
public class TransactionManagerAdvisor extends AbstractBeanFactoryPointcutAdvisor {


    public TransactionManagerAdvisor() {
        setAdvice(new TransactionIntercptor());
    }

    @Override
    public Pointcut getPointcut() {
        return new TransactionPointCut();
    }
}
