package org.dev.control;

public class UnitControl {
    private String unit;

    private UnitControl(){}

    private static UnitControl instance;

    public static UnitControl getInstance(){
        if(instance==null){
            instance = new UnitControl();
        }
        return instance;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
