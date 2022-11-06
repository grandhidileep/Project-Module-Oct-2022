package com.scaler.firstproj.controllers;

import com.scaler.firstproj.data.Task;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/tasks")
public class TasksController {


    /*
    ASSIGNMENT 02:

    Building a "Task Manager" API that does the following
    1. Create a task (with following attributes)
        - Title
        - Due Date
        - Status (Pending, Completed)
    2. Update a task
    3. Delete a task
    4. List all tasks
     */

    ArrayList<Task> tasks;

    public TasksController() {
        this.tasks = new ArrayList<>();

        // sample data for testing
        this.tasks.add(new Task("Task 1", new Date(), false));
        this.tasks.add(new Task("Task 2", new Date(), false));
    }

    @GetMapping("")
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable("id") Integer id) {
        return tasks.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Task createTask(@RequestBody Task newTask){
        tasks.add(newTask);
        return newTask;
    }

    @DeleteMapping("/{id}")
    public Task deleteTask(@PathVariable("id") Integer id){
        int index = id;
        return tasks.remove(index);
    }

    @PatchMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Task updateTask(@RequestBody Task updatedTask,@PathVariable("id") Integer id){
        Task oldTask = tasks.get(id);
        if(updatedTask.getTitle() != null)
            oldTask.setTitle(updatedTask.getTitle());
        if(updatedTask.getCompleted() != null)
            oldTask.setCompleted(updatedTask.getCompleted());
        if(updatedTask.getDueDate() != null)
            oldTask.setDueDate(updatedTask.getDueDate());
        tasks.set(id,oldTask);
        return oldTask;
    }
}
