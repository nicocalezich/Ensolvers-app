package com.pruebas.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pruebas.models.Task;

public interface TaskDAO extends JpaRepository<Task,Integer>  {
	
}

