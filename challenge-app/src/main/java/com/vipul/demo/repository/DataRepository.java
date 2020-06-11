package com.vipul.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vipul.demo.entity.DataEntity;

@Repository
public interface DataRepository extends JpaRepository<DataEntity, Long> {

	DataEntity findById(long id);
}
