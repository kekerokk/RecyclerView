package com.eiro.recyclerview;

public class CarItem {
    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }
    public CarItem(String carnumber, String carname)
    {
        carNumber = carnumber;
        carName = carname;
    }
    public CarItem(){};

    String carNumber;
    String carName;
};
