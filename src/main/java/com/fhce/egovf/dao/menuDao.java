package com.fhce.egovf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fhce.egovf.model.menuModel;

public interface menuDao extends JpaRepository<menuModel, Long>{

}

