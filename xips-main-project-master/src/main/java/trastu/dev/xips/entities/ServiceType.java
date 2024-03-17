package trastu.dev.xips.entities;

import lombok.val;

public enum ServiceType {

    EDUCATION("Education"),
    ENTERTAINMENT("Entertainment"),
    CHILDCARE("Childcare"),
    MOBILITY("Mobility"),
    REPAIRS("Repairs"),
    CLEANING("Cleaning"),
    COMPANIONSHIP("Companionship");

    private final String value;

    ServiceType(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

}
