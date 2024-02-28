package com.inheritance.car;
import exception.CustomException;
import utils.UtilMethods;

public class Car {
    private int yearOfMake;
    private String engineNumber;
    private String type;
    
    private static final int highestYearOfMaking = 2024;

    public Car(String string){
        System.out.println(string);
    }

    public Car(){

    }
    

    public int getYearOfMake() {
        return yearOfMake;
    }

    public void setYearOfMake(int yearOfMake) throws CustomException{
    	   UtilMethods.isPositionValid(yearOfMake,highestYearOfMaking,"Year of must be less than 2024 or must not be negative");
        this.yearOfMake = yearOfMake;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) throws CustomException{
    	   UtilMethods.isNull(engineNumber,"Engine Number Cannot be NUll");
        this.engineNumber = engineNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) throws CustomException{
        UtilMethods.isNull(type,"Type Cannot be NUll");
        this.type = type;
    }

    public void maintenance() {
        System.out.println("Car Under maintenance");
    }
}

