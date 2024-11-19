package org.example.validationex.Controller;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.example.validationex.ApiResponse.ApiResponse;
import org.example.validationex.Model.Project;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v2/project-tracker")
public class ProjectController {

    ArrayList<Project> projects = new ArrayList<Project>();


    @GetMapping(path = "/display")
    public ResponseEntity displayProjects() {

        return ResponseEntity.status(200).body(projects);
    }

    @PostMapping("/add")
    public ResponseEntity addProject(@RequestBody @Valid Project project, Errors errors) {

       if(errors.hasErrors()) {
         String msg = errors.getFieldError().getDefaultMessage();
         return ResponseEntity.status(400).body(msg);
           //400 -- error from user
       }

       projects.add(project);
       return ResponseEntity.status(200).body(new ApiResponse("Project added successfully!"));

    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateProject(@PathVariable int index, @RequestBody @Valid Project project, Errors errors){

        if(errors.hasErrors()){
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }

        projects.set(index, project);
        return ResponseEntity.status(200).body(new ApiResponse("Project updated successfully!"));

    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteProject(@PathVariable int index){

        projects.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("Project deleted successfully!"));
    }

    //End CRUD endpoints


    @PutMapping(path = "/changeStatus/{ID}")
    public ResponseEntity changeStatus(@PathVariable String ID) {

        for (Project project : projects) {

            if (project.getId().equalsIgnoreCase(ID)) {

                if(project.getStatus().equalsIgnoreCase("Not Started"))
                project.setStatus("In Progress");
                
                else 
                    project.setStatus("Completed");
            }

            
        } //End for
        return ResponseEntity.status(200).body(new ApiResponse("Project status updated successfully!"));
    }



    @GetMapping("/search/{title}")
    public ResponseEntity searchByTitle(@PathVariable String title){

        for(Project project : projects){
            if(project.getTitle().equalsIgnoreCase(title))
                return ResponseEntity.status(200).body(new ApiResponse("Project with this title has been found!"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Project with this title not found!"));
    }

    @GetMapping("/searchComp/{compName}")
    public ResponseEntity displayByComp(@PathVariable String compName){

        ArrayList<Project> projectsByComp = new ArrayList<>();

        for(Project project : projects){
            if(project.getCompanyName().equalsIgnoreCase(compName)) {
                projectsByComp.add(project);
                return ResponseEntity.status(200).body(projectsByComp);
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("This company doesn't have any project yet!"));

    }























} //End controller
