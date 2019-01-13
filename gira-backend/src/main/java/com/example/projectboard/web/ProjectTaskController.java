package com.example.projectboard.web;

import com.example.projectboard.domain.Logger;
import com.example.projectboard.domain.ProjectTask;
import com.example.projectboard.domain.Raport;
import com.example.projectboard.service.ProjectTaskService;
import com.example.projectboard.service.RaportService;
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

    private Raport raport = new Raport();
    private RaportService raportService = new RaportService();


    @PostMapping("")
    public ResponseEntity<?> addPTToBoard(@Valid @RequestBody ProjectTask projectTask, BindingResult result){
        if(result.hasErrors()){
            Map<String,String> errorMap = new HashMap<>();

            for(FieldError error: result.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            Logger.log(4,errorMap.toString());
            return new ResponseEntity<Map<String, String>>(errorMap,HttpStatus.BAD_REQUEST);
        }
        Logger.log(2,"POST request");

        raportService.wrapLogicSaveRaport(raport,projectTask);
        Logger.log(2,"RAPORT: "+raport.getRaport());

        ProjectTask newPT = projectTaskService.saveOrUpdateProjectTask(projectTask);
        return new ResponseEntity<ProjectTask>(newPT, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public Iterable<ProjectTask> getAllPTs(){
        Logger.log(2,"GET request");
        return projectTaskService.findAll();
    }
    @GetMapping("/{ptId}")
    public ResponseEntity<?> getPTById(@PathVariable Long ptId){
        ProjectTask projectTask = projectTaskService.findById((ptId));
        Logger.log(2,"GET request");
        if(projectTask==null)
            return new ResponseEntity<String>("Error, element is not in collecctions", HttpStatus.NOT_FOUND);
        return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
    }

    @GetMapping("/raport")
    public ResponseEntity<?> generateRaport(){
        Raport rs = raportService.getRaport();
        if(rs == null){
            Raport r = new Raport();
            return  new ResponseEntity<Raport>(r,HttpStatus.OK);
        }
        return  new ResponseEntity<Raport>(rs,HttpStatus.OK);
    }

    @DeleteMapping("/{ptId}")
    public ResponseEntity<?> deleteProjectTask(@PathVariable Long ptId){

        ProjectTask projectTask = projectTaskService.findById((ptId));
        raportService.wrapLogicDecRaport(raport,projectTask);

        projectTaskService.delete(ptId);
        Logger.log(2,"DELETE request");
        return new ResponseEntity<String>("Project Task deleted",HttpStatus.OK);
    }
}
