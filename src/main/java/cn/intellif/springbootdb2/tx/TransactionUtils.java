package cn.intellif.springbootdb2.tx;

import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *事务管理工具类这里主要利用DataSourceUtils获取连接绑定连接到当前线程及释放连接
 */
public abstract class TransactionUtils {
   private static   ThreadLocal<String> uniqueKey = new ThreadLocal<>();

    public static void beginTransaction(String key) throws SQLException {
        if(uniqueKey.get()==null){
            uniqueKey.set(key);
            Connection connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            ConnectionHolder conHolder = new ConnectionHolder(connection);
            conHolder.setSynchronizedWithTransaction(true);
            TransactionSynchronizationManager.bindResource(getDataSource(),conHolder);
        }
    }

    public static void rollbackTransaction(String key) throws SQLException {
        if(currentKey().equals(key)) {
            Connection connection = DataSourceUtils.getConnection(getDataSource());
            connection.rollback();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>事务执行回滚开始:"+key);
        }
    }

    public static void commitTransaction(String key) throws SQLException {
        if(currentKey().equals(key)) {
            Connection connection = DataSourceUtils.getConnection(getDataSource());
            connection.commit();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>事务执行提交:"+key);
        }
    }

    public static void releaseTransaction(String key) throws SQLException {
        if(currentKey().equals(key)) {
            Connection connection = DataSourceUtils.getConnection(getDataSource());
            DataSourceUtils.releaseConnection(connection,getDataSource());
            if(connection!=null&&!connection.isClosed()){
                connection.close();
            }
            TransactionSynchronizationManager.unbindResource(getDataSource());
            uniqueKey.remove();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>事务执行释放:"+key);
        }
    }

    private static String currentKey(){
        return uniqueKey.get();
    }


    private static DataSource getDataSource(){
        return SpringConfigBean.getDataSource();
    }

}
