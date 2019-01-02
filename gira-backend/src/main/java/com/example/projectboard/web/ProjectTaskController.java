package com.example.projectboard.web;

import com.example.projectboard.domain.ProjectTask;
import com.example.projectboard.service.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/board")
@CrossOrigin
public class ProjectTaskController {

    @Autowired
    private ProjectTaskService projectTaskService;

    @PostMapping("")
    public ResponseEntity<?> addPTToBoard(@Valid @RequestBody ProjectTask projectTask, BindingResult result){
        if(result.hasErrors()){
            Map<String,String> errorMap = new HashMap<>();

            for(FieldError error: result.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            return new ResponseEntity<Map<String, String>>(errorMap,HttpStatus.BAD_REQUEST);
        }
        ProjectTask newPT = projectTaskService.saveOrUpdateProjectTask(projectTask);
        return new ResponseEntity<ProjectTask>(newPT, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public Iterable<ProjectTask> getAllPTs(){
        return projectTaskService.findAll();
    }
    @GetMapping("/{ptId}")
    public ResponseEntity<?> getPTById(@PathVariable Long ptId){
        ProjectTask projectTask = projectTaskService.findById((ptId));
        // TODO: NOTFOUND status when id is not in db
        if(projectTask==null)
            return new ResponseEntity<String>("Error, element is not in collecctions", HttpStatus.NOT_FOUND);
        return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
    }

    @DeleteMapping("/{ptId}")
    public ResponseEntity<?> deleteProjectTask(@PathVariable Long ptId){
        projectTaskService.delete(ptId);

        return new ResponseEntity<String>("Project Task deleted",HttpStatus.OK);
    }
}
