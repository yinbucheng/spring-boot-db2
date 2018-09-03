package cn.intellif.springbootdb2.service.impl;

import cn.intellif.springbootdb2.dao.TestDao;
import cn.intellif.springbootdb2.entity.TestEntity;
import cn.intellif.springbootdb2.service.ITestService;
import cn.intellif.springbootdb2.tx.Transactional2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements ITestService {
    @Autowired
    private TestDao testDao;

    @Override
    @Transactional2
    public void test() {
        TestEntity testEntity = new TestEntity();
        testEntity.setName("yucui123");
        testDao.save(testEntity);
//        int i = 9/0;
        TestEntity testEntity1 = new TestEntity();
        testEntity1.setName("yinchong222");
        testDao.save(testEntity);
    }
}
