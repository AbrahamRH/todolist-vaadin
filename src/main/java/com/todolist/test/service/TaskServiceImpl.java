package com.todolist.test.service;

import com.todolist.test.Entity.Task;
import com.todolist.test.persistance.TaskRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    @Transactional
    public Task save(Task task) {
        try{
            logger.info("Saving task: " + task.getName());
            return taskRepository.save(task);
        } catch (Exception e) {
            String tranName = TransactionAspectSupport.currentTransactionStatus().getTransactionName();
            logger.error("Error processing transaction {} due to {}",tranName, e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task getTask(Long id) {
        return taskRepository.findTaskById(id);
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task findByName(String name){
        return taskRepository.findTaskByName(name);
    }

}
