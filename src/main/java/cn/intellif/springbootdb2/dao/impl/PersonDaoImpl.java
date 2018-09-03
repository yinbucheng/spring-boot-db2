package cn.intellif.springbootdb2.dao.impl;

import cn.intellif.springbootdb2.dao.PersonDao;
import cn.intellif.springbootdb2.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDaoImpl implements PersonDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(PersonEntity person) {
        return jdbcTemplate.update("insert into test2.t_person(name,id_card) values(?,?) ",person.getName(),person.getId());
    }

    @Override
    public int update(PersonEntity person) {
        return jdbcTemplate.update("update test2.t_person set name =?,id_card = ? where id =?",person.getName(),person.getIdCard(),person.getId());
    }
}
