
package org.gar.contoller;

import org.springframework.web.bind.annotation.RestController;
import org.gar.entites.Task;
import org.gar.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/tasks")

@RestController
public class TaskController {
	
	@Autowired
	private TaskRepository tr;
	
	@GetMapping("/get-all")
	public List<Task> getAllTasks() {
		return tr.findAll();
	}
	
	@GetMapping("/get/{id}")
	public Task getTask (@PathVariable(value="id") Long id) {
		
		Task task = tr.findById(id).orElse(null);
		return task;
	}
	
	@PostMapping("/create")
	public Task addTask(@RequestBody Task task) {
		
		return tr.save(task);
	}
	
	

}
