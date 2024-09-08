package com.backend.sample.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.sample.demo.entity.log.LogEntity;

@Repository
public interface LogRepository extends CrudRepository<LogEntity, Long> {
    List<LogEntity> findAll();
}
