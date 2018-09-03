package cn.intellif.springbootdb2.tx;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class SpringConfigBean implements ApplicationContextAware{
  private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringConfigBean.applicationContext = applicationContext;
    }

    public static DataSource getDataSource(){
        return applicationContext.getBean(DataSource.class);
    }
}
