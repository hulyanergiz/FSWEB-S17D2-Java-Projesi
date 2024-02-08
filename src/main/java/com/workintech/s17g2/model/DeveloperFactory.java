package com.workintech.s17g2.model;

import com.workintech.s17g2.tax.Taxable;

public class DeveloperFactory {

    public static Developer createDeveloper(Developer developer, Taxable taxable){
        Developer createdDeveloper=null;
        if(developer.getExperience().equals(Experience.JUNIOR)){
            createdDeveloper=new JuniorDeveloper(developer.getId(), developer.getName(), developer.getSalary()- developer.getSalary()* taxable.getSimpleTaxRate());
        }else if(developer.getExperience().equals(Experience.MID)){
            createdDeveloper=new MidDeveloper(developer.getId(), developer.getName(), developer.getSalary()- developer.getSalary()* taxable.getMiddleTaxRate());
        } else if(developer.getExperience().equals(Experience.SENIOR)){
            createdDeveloper=new MidDeveloper(developer.getId(), developer.getName(), developer.getSalary()- developer.getSalary()* taxable.getMiddleTaxRate());
        }
        return createdDeveloper;
    }
}
