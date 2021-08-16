package com.pruebas.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="task")
public class Task {
	
	@Id
	private Integer id;
	
	@Column
	private Integer id_folder;
	
	@Column
	private Boolean is_done;
	
	@Column
	private String task;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_folder() {
		return id_folder;
	}

	public void setId_folder(Integer id_folder) {
		this.id_folder = id_folder;
	}

	public Boolean getIs_done() {
		return is_done;
	}

	public void setIs_done(Boolean is_done) {
		this.is_done = is_done;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	

}
