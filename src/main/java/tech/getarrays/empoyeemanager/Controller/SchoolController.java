package tech.getarrays.empoyeemanager.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.empoyeemanager.model.School;
import tech.getarrays.empoyeemanager.service.SchoolService;
import tech.getarrays.empoyeemanager.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/team")
public class SchoolController {
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }
    private final SchoolService schoolService;


    @PostMapping("/add")
    public ResponseEntity<School> addTeam(@RequestBody School school){
        School newschool;
        //Retrieve TeamId & add it to employee entity as a foreign key
       
        newschool = schoolService.addSchool(school);
        return new ResponseEntity<>(newschool, HttpStatus.CREATED);

    }
    @GetMapping("/all")
    public ResponseEntity<List<School>> getAllTeams(){
        List<School> schools = schoolService.findAllSchools();
        return new ResponseEntity<>(schools,HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity <School> getTeamById(@PathVariable("id")Long id){
       School schools= schoolService.findSchoolById(id);
        return new ResponseEntity<>(schools,HttpStatus.OK);
    }
    @PutMapping("/update/{id}")

    public ResponseEntity<School> updateEmployee(@RequestBody School school, @PathVariable("id")Long id){
        School existingschool = schoolService.findSchoolById(id);

        if(school.getName()!=null) {
            existingschool.setName(school.getName());
        }




        School updateschool = schoolService.updateSchool(existingschool);
        return new ResponseEntity<>(updateschool, HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteTeam(@PathVariable("id")Long id){
       schoolService.deleteSchool(id);
        return new ResponseEntity<>( HttpStatus.OK);

    }
}
