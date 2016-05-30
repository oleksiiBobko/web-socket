package com.bobko.chat.task.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bobko.chat.repository.TaskAppRepository;
import com.bobko.chat.task.domain.Task;

@Repository
public abstract class TaskRepository extends TaskAppRepository<String, Task> {
	@Autowired
	private BeanFactory factory;
	
	@PostConstruct
	public void init() {
		this.addObserver(this.factory.getBean(TaskRepositoryObserver.class));
	}
}
