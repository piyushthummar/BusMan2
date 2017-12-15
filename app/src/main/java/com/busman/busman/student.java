package com.busman.busman;

public class student {
    String id,name,stop,fees;

    public student(){

    }

    public student(String id, String name, String stop, String fees) {
        this.id = id;
        this.name = name;
        this.stop = stop;
        this.fees = fees;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStop() {
        return stop;
    }

    public String getFees() {
        return fees;
    }
}
