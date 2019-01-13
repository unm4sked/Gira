package com.example.projectboard.service;

import com.example.projectboard.domain.Logger;
import com.example.projectboard.domain.ProjectTask;
import com.example.projectboard.repository.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask saveOrUpdateProjectTask(ProjectTask projectTask) {
        if(projectTask.getStatus()==null || projectTask.getStatus()==""){
            projectTask.setStatus("To_DO");
        }
        Logger.log(1,"Save Project Task ID:"+projectTask.getId());

        return projectTaskRepository.save(projectTask);
    }

    public Iterable<ProjectTask> findAll(){
        Logger.log(1,"Get all items");
        return projectTaskRepository.findAll();
    }

    public ProjectTask findById(Long id){

        Logger.log(1,"Find item by ID:"+id);
        return projectTaskRepository.getById(id);
    }

    public void delete(Long id){
        ProjectTask projectTask = findById(id);
        Logger.log(1,"Delete items ID:"+id);
        projectTaskRepository.delete((projectTask));
    }
}
