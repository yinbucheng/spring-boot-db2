package cn.intellif.springbootdb2.service;

import cn.intellif.springbootdb2.tx.Transactional2;

public interface ITestService {
    @Transactional2
    void test();
}
