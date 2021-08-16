package com.pruebas.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pruebas.dao.FolderDAO;
import com.pruebas.models.Folder;

@CrossOrigin(origins = "http://localhost:8081/")
@RestController
@RequestMapping("folders")
public class FolderRest {

	@Autowired
	private FolderDAO folderDAO;
	
	@PostMapping("/guardar")
	public void guardar(@RequestBody Folder folder) {
		folderDAO.save(folder);
	}
	
	@GetMapping("/listar")
	public List<Folder> listarFolders(){
		return folderDAO.findAll();
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		folderDAO.deleteById(id);
	}
	
	
	
}
