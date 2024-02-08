package com.workintech.s17g2.rest;

import com.workintech.s17g2.dto.DeveloperResponse;
import com.workintech.s17g2.model.Developer;
import com.workintech.s17g2.model.DeveloperFactory;
import com.workintech.s17g2.tax.Taxable;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/developers")
public class DeveloperController {
    private Map<Integer, Developer> developers;
    private Taxable taxable;

    @Autowired
    public DeveloperController(Taxable taxable){
        this.taxable=taxable;
    }

    @PostConstruct
    public void init(){
        developers=new HashMap<>();
    }
    @PostMapping
    public DeveloperResponse addDeveloper(@RequestBody Developer developer){
        Developer createdDeveloper= DeveloperFactory.createDeveloper(developer,taxable);
        if(Objects.nonNull(createdDeveloper)){
            developers.put(developer.getId(),createdDeveloper);
        }
        return new DeveloperResponse(createdDeveloper.getId(),createdDeveloper.getName(),createdDeveloper.getSalary(),createdDeveloper.getExperience());
    }

   @GetMapping
    public List<Developer> getDevelopers(){
        return new ArrayList<>(developers.values());
    }
    @GetMapping("/{id}")
    public DeveloperResponse findDeveloperById(@PathVariable("id") Integer id){
        Developer developer=developers.get(id);
        if(Objects.isNull(developer)){
            return new DeveloperResponse(id,null,null,null);
        }
        return new DeveloperResponse(developer.getId(),developer.getName(),developer.getSalary(),developer.getExperience());
    }
    @PutMapping("/{id}")
    public DeveloperResponse updateDeveloper(@PathVariable Integer id,@RequestBody Developer developer){
        if(Objects.isNull(developer)){
            return new DeveloperResponse(null,null,null,null);
        }
        Developer existingDeveloper=developers.get(id);
        if(Objects.isNull(existingDeveloper)){
            return new DeveloperResponse(id,null,null,null);
        }
        Developer updatedDeveloper=DeveloperFactory.createDeveloper(developer,taxable);
        developers.put(id,updatedDeveloper);
        return new DeveloperResponse(id,updatedDeveloper.getName(),updatedDeveloper.getSalary(),updatedDeveloper.getExperience());

    }
    @DeleteMapping("/{id}")
    public DeveloperResponse deleteDeveloper(@PathVariable Integer id){
        Developer deletedDeveloper=developers.get(id);
        if(Objects.isNull(deletedDeveloper)){
            return new DeveloperResponse(id,null, null, null);
        }
        developers.remove(id);
        return new DeveloperResponse(deletedDeveloper.getId(),deletedDeveloper.getName(), deletedDeveloper.getSalary(), deletedDeveloper.getExperience());
    }
}
