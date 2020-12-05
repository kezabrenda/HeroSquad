package models;

import java.util.ArrayList;

public class Hero {
    private String name;
    private int age;
    private Character gender;
    private String specialPower;
    private String weakness;
    private static ArrayList<Hero> instances = new ArrayList<>();

    public Hero(String name, int age, Character gender, String specialPower, String weakness){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.specialPower = specialPower;
        this.weakness = weakness;
    }

    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public Character getGender() {
        return gender;
    }
    public String getSpecialPower(){
        return specialPower;
    }
    public String getWeakness(){
        return weakness;
    }

    public static ArrayList<Hero> getAll(){
        return instances;
    }
}
