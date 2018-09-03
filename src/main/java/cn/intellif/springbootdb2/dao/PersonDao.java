package cn.intellif.springbootdb2.dao;

import cn.intellif.springbootdb2.entity.PersonEntity;

public interface PersonDao {
    int save(PersonEntity person);
    int update(PersonEntity person);
}
