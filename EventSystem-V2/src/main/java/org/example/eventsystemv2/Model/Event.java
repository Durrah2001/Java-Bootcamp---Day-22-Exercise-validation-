package org.example.eventsystemv2.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Event {

    @NotEmpty(message = "ID can't be empty!")
    @Size(min= 3, message = "ID length must be more than \"2\" digits.")
    private String ID;
    //////////////////////

    @NotEmpty(message ="Description can't be empty!")
    @Size(min= 16, message = "Description length must be more than \"15\" letters.")
    private String description;
    //////////////////////

    @NotNull(message = "Capacity can't be null!")
    @Positive(message = "Capacity must be a number only!") //Integer values will be number automatically
    @Min(26)
    private int capacity;    //Data type is integer, since the capacity will apply mathematical operations on it
    /////////////////////

    @JsonFormat
    private Date startDate;

    @JsonFormat
    private Date endDate;

    //////////////////////








}
