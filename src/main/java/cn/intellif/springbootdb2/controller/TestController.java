package cn.intellif.springbootdb2.controller;

import cn.intellif.springbootdb2.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {
    @Autowired
    private ITestService testService;

    @RequestMapping("/test")
    public Object test(){
        testService.test();
        return "test";
    }

    @RequestMapping("/test2")
    @Transactional
    public Object test2(){
        return "test2";
    }
}
