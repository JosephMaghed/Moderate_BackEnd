package tech.getarrays.empoyeemanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
//Use @Entity to make sure that this class gets mapped to any database
@Entity
@Data
public class Employee implements Serializable {
@Id //Primary Key
@SequenceGenerator(name="employee_sequence",
sequenceName = "employee_sequence",
allocationSize = 1)
@GeneratedValue(strategy= GenerationType.SEQUENCE,
generator="employee_sequence")//How to generate value
@Column(nullable = false,updatable = false)
    private Long id;

    @NotBlank(message = "Name can not be empty")
    private String name;

    @NotBlank(message = "Email must not be empty")
    @Email(message = "Email must e in the correct format")
    private String email;

    @NotBlank(message = "Job title must not be empty")
    private String jobTitle;

    @NotBlank(message = "Please enter your phone number ")
    @Size(min = 10, message = "Please enter a valid phone number")
    private String phone;

    private String imageUrl;



    @Column(nullable = false,updatable = false)
    private String employeeCode;
    //Connect to Job table
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "JobId")  // Name of the foreign key column
    private JobRole jobRole;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "SchoolId")  // Name of the foreign key column

    School school;


}
