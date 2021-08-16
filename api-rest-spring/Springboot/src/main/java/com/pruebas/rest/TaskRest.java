package com.pruebas.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pruebas.dao.TaskDAO;
import com.pruebas.models.Task;

@CrossOrigin(origins = "http://localhost:8081/")
@RestController
@RequestMapping("tasks")
public class TaskRest {

	@Autowired
	private TaskDAO taskDAO;
	
	@PostMapping("/guardar")
	public void guardar(@RequestBody Task task) {
		taskDAO.save(task);
	}
	
	@GetMapping("/listar") //@PathVariable("id") Integer id
	public List<Task> listarFolders(){
		return taskDAO.findAll();
	}
	
	
	@DeleteMapping("/eliminar/{id}")
	public void deleteByFolder(@PathVariable("id") Integer id) {
		taskDAO.deleteById(id);
	}

	
	
	
	
	
}