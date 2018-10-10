package cn.intellif.springbootdb2.service.impl;

import cn.intellif.springbootdb2.dao.PersonDao;
import cn.intellif.springbootdb2.entity.PersonEntity;
import cn.intellif.springbootdb2.service.IPersonService;
import cn.intellif.springbootdb2.tx.Transactional2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImpl implements IPersonService {
    @Autowired
    private PersonDao personDao;

    @Transactional2
//    @Transactional
    @Override
    public void test() {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setIdCard("123456");
        personEntity.setName("good3333");
        personDao.save(personEntity);
    }
}
