package com.pruebas.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pruebas.models.Folder;

public interface FolderDAO extends JpaRepository<Folder,Integer>  {

}
