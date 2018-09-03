package cn.intellif.springbootdb2.tx;

import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 这里涉及到的类TransactionSynchronizationManager 这里会将Datasource做完key使用
 * ConnectionHolder
 * 这里有个问题如何去掉重复执行
 */
public abstract class DataSourcesUtils {
   private static   ThreadLocal<String> uniqueKey = new ThreadLocal<>();

    public static void beginTransaction(String key) throws SQLException {
        DataSource dataSource = getDataSource();
        ConnectionHolder connectionHolder = (ConnectionHolder) TransactionSynchronizationManager.getResource(dataSource);
        if(connectionHolder==null){
           Connection connection = dataSource.getConnection();
           connection.setAutoCommit(false);
           connectionHolder = new ConnectionHolder(connection,true);
           connectionHolder.setSynchronizedWithTransaction(true);
           TransactionSynchronizationManager.bindResource(dataSource,connectionHolder);
           uniqueKey.set(key);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>创建事务:"+key);
        }
    }

    public static void rollbackTransaction(String key) throws SQLException {
        if(currentKey().equals(key)) {
            DataSource dataSource = getDataSource();
            ConnectionHolder connectionHolder = (ConnectionHolder) TransactionSynchronizationManager.getResource(dataSource);
            connectionHolder.getConnection().rollback();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>事务执行回滚开始:"+key);
        }
    }

    public static void commitTransaction(String key) throws SQLException {
        if(currentKey().equals(key)) {
            DataSource dataSource =getDataSource();
            ConnectionHolder connectionHolder = (ConnectionHolder) TransactionSynchronizationManager.getResource(dataSource);
            connectionHolder.getConnection().commit();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>事务执行提交:"+key);
        }
    }

    public static void releaseTransaction(String key) throws SQLException {
        if(currentKey().equals(key)) {
            DataSource dataSource = getDataSource();
            ConnectionHolder connectionHolder = (ConnectionHolder) TransactionSynchronizationManager.getResource(dataSource);
            connectionHolder.getConnection().close();
            TransactionSynchronizationManager.clear();
            uniqueKey.remove();
            //释放resouces
            TransactionSynchronizationManager.unbindResourceIfPossible(dataSource);
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
