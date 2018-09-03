package cn.intellif.springbootdb2.controller;

import cn.intellif.springbootdb2.service.IPersonService;
import cn.intellif.springbootdb2.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {
    @Autowired
    private ITestService testService;
    @Autowired
    private IPersonService personService;

    @RequestMapping("/test")
    public Object test(){
        personService.test();
        testService.test();
        return "test";
    }
}
