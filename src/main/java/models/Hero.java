package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Hero {
    private final LocalDateTime createdAt;
    private String name;
    private int age;
    private String specialPower;
    private String weakness;
    private static ArrayList<Hero> instances = new ArrayList<>();
    private boolean published;
    private int id;

    public Hero(String name, int age, String specialPower, String weakness){
        this.name = name;
        this.age = age;
        this.specialPower = specialPower;
        this.weakness = weakness;
        this.published = false;
        this.createdAt = LocalDateTime.now();
        instances.add(this); //Also new. Can you figure out what I do and how I work?
        this.id = instances.size();
    }

    public String getName(){
        return name;
    }
    public int getAge(){ return age; }
    public String getSpecialPower(){
        return specialPower;
    }
    public String getWeakness(){
        return weakness;
    }

    public static ArrayList<Hero> getAll(){
        return instances;
    }

    public static void clearAllHero(){
        instances.clear();//clear as a method is part of the ArrayList class.
    }

    public boolean getPublished() {
        return this.published;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getId() {
        return id;
    }
    public static Hero findById(int id){
        return instances.get(id-1);
    }

    public void update(String name, int age, String specialPower, String weakness) {
        this.name = name;
        this.age = age;
        this.specialPower = specialPower;
        this.weakness = weakness;
    }
    public void deleteHero(){
        instances.remove(id-1);
    }
}
