package com.inheritance.car;
import exception.CustomException;
import utils.UtilMethods;

public class XUV extends Car {
   
    private int seats;
    private int airBag;
    private String model;
    private String color;
    
    private static final int defaultSeatSize = 5;
    private static final int defaultAirbagSize = 6;

    public XUV() {
        super("Calling overloaded constructor from XUV default constructor");
    }


    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) throws CustomException{
    	   UtilMethods.isPositionValid(seats,defaultSeatSize,"Seat size must be greater than zero and must be less than 6");
        this.seats = seats;
    }

    public int getAirBag() {
        return airBag;
    }

    public void setAirBag(int airBag) throws CustomException{
    	   UtilMethods.isPositionValid(airBag,defaultAirbagSize,"Airbag size must be greater than zero and must be less than 7");
        this.airBag = airBag;
    }

    public String getModel() {
        return model;
    }
    
    public void setModel(String model) throws CustomException{
    	    UtilMethods.isNull(model,"Model Cannot be NUll");
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) throws CustomException{
         UtilMethods.isNull(color,"Color Cannot be NUll");
        this.color = color;
    }
}

