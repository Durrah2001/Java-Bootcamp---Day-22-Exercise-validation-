package org.example.validationex.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {


    @NotEmpty(message = "ID can't be empty!")
    @Size(min= 3, message = "ID length must be more than \"2\" digits.")
    private String id;
    //////////////////////////

    @NotEmpty(message = "Title can't be empty!")
    @Size(min= 9, message = "Title length must be more than \"8\" letters.")
    private String title;
    //////////////////////////

    @NotEmpty(message = "Description can't be empty!")
    @Size(min = 15, message = "Description length must be more than \"15\" letters.")
    private String description;
    //////////////////////////

    @NotEmpty(message = "Status can't be empty!")
    @Pattern(regexp = "Not Started|In Progress|Completed", message = "Status must be either: \"Not Started\" or \n\"In Progress\" or \n\"Completed\"")
    private String status;
    //////////////////////////

    @NotEmpty(message = "Company name can't be empty!")
    @Size(min= 6, message = "Company name length must be more than \"6\" letters.")
    private String companyName;






}
