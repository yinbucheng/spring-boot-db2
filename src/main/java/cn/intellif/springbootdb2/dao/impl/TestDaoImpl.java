package cn.intellif.springbootdb2.dao.impl;

import cn.intellif.springbootdb2.dao.TestDao;
import cn.intellif.springbootdb2.entity.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestDaoImpl implements TestDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(TestEntity testEntity) {
        return jdbcTemplate.update("insert test2.t_test(name) values(?)",testEntity.getName());
    }

    @Override
    public int update(TestEntity testEntity) {
        return jdbcTemplate.update("update test2.t_test set name = ? where id = ?",testEntity.getName(),testEntity.getId());
    }

    @Override
    public TestEntity findOne(Long id) {
        return null;
    }
}
