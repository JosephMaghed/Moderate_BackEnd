package tech.getarrays.empoyeemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import tech.getarrays.empoyeemanager.exception.UserNotFoundException;
import tech.getarrays.empoyeemanager.model.JobRole;
import tech.getarrays.empoyeemanager.model.JobRole;
import tech.getarrays.empoyeemanager.repo.JobRoleRepo;
import tech.getarrays.empoyeemanager.repo.TeamRepo;

import java.util.List;

public class JobRoleService {
    private  final JobRoleRepo jobRoleRepo;

    //Constructor
    @Autowired

    public JobRoleService(JobRoleRepo jobRoleRepo) {
        this.jobRoleRepo = jobRoleRepo;
    }

    public JobRole addJobRole(JobRole jobRole){
        return  jobRoleRepo.save(jobRole);
    }

    public List<JobRole> findAllJobRoles(){
        return jobRoleRepo.findAll();
    }

    public JobRole findJobRoleById(Long id){
        return jobRoleRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not fond"));}

    public JobRole updateJobRole(JobRole JobRole){
        return  jobRoleRepo.save(JobRole);
    }

    public void deleteJobRole(Long id){
        jobRoleRepo.deleteById(id);
    }
}
