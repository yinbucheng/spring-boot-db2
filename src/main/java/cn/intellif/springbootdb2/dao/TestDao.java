package cn.intellif.springbootdb2.dao;

import cn.intellif.springbootdb2.entity.TestEntity;

public interface TestDao {
    int save(TestEntity testEntity);
    int update(TestEntity testEntity);
    TestEntity findOne(Long id);
}
