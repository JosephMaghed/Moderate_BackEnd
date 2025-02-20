package tech.getarrays.empoyeemanager.Controller;
import java.lang.Long;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.empoyeemanager.model.Authority;
import tech.getarrays.empoyeemanager.model.Employee;
import tech.getarrays.empoyeemanager.model.School;
import tech.getarrays.empoyeemanager.service.AuthorityService;
import tech.getarrays.empoyeemanager.service.EmployeeService;
import tech.getarrays.empoyeemanager.service.SchoolService;

import java.util.List;

@RestController
@RequestMapping("/team")
public class AuthorityController {
    public AuthorityController(AuthorityService authorityService, EmployeeService employeeService, SchoolService schoolService) {
        this.authorityService = authorityService;
        this.employeeService = employeeService;
        this.schoolService = schoolService;
    }
    private final AuthorityService authorityService;
    private final EmployeeService employeeService;
    private final SchoolService schoolService;



    @PostMapping("/add")
    public ResponseEntity<Authority> addAuthority(@RequestBody Authority authority){
        Authority newAuthority;
        //Retrieve EmployeeId & add it to authority entity as a foreign key
        if(authority.getEmployee().getId()!=null){
            Employee existingEmployee = employeeService.findEmployeeById(authority.getEmployee().getId());
            authority.setEmployee(existingEmployee);
        }
        //Retrieve SchoolId & add it to authority entity as a foreign key
        if(authority.getSchool().getId()!=null){
            School existingSchool = schoolService.findSchoolById(authority.getSchool().getId());
            authority.setSchool(existingSchool);
        }
        newAuthority = authorityService.addAuthority(authority);
        return new ResponseEntity<>(newAuthority, HttpStatus.CREATED);

    }
    @GetMapping("/all")
    public ResponseEntity<List<Authority>> getAllTeams(){
        List<Authority> autorities = authorityService.findAllAuthorities();
        return new ResponseEntity<>(autorities,HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity <Authority> getTeamById(@PathVariable("id")Long id){
       Authority teams= authorityService.findAuthorityById(id);
        return new ResponseEntity<>(teams,HttpStatus.OK);
    }
    @PutMapping("/update/{id}")

    public ResponseEntity<Authority> updateEmployee(@RequestBody Authority authority, @PathVariable("id")Long id){
        Authority existingAuthority = authorityService.findAuthorityById(id);

        if(authority.getTeamLeaderId()!=null) {
            existingAuthority.setTeamLeaderId(authority.getTeamLeaderId());
        }
        if(authority.getTeamRole()!=null) {
            existingAuthority.setTeamRole(authority.getTeamRole());
        }
        if(authority.getEmployee().getId()!=null){
            Employee existingEmployee = employeeService.findEmployeeById(authority.getEmployee().getId());
            authority.setEmployee(existingEmployee);
        }
        if(authority.getSchool().getId()!=null){
            School existingSchool = schoolService.findSchoolById(authority.getSchool().getId());
            authority.setSchool(existingSchool);
        }



        Authority updateAuthority = authorityService.updateAuthority(existingAuthority);
        return new ResponseEntity<>(updateAuthority, HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteTeam(@PathVariable("id")Long id){
       authorityService.deleteAuthority(id);
        return new ResponseEntity<>( HttpStatus.OK);

    }
}
