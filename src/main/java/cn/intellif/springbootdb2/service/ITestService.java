package cn.intellif.springbootdb2.service;

import cn.intellif.springbootdb2.tx.Transactional2;
import org.springframework.transaction.annotation.Transactional;

public interface ITestService {
    @Transactional2
//    @Transactional
    void test();
}
