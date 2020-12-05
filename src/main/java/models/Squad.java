package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Squad {
    private int maxSize;
    private String name;
    private String cause;
    private static ArrayList<Squad> instances = new ArrayList<>();
    private boolean published;
    private final LocalDateTime createdAt;
    private final int id;

    public Squad(int maxSize, String name, String cause) {
        this.maxSize = maxSize;
        this.name = name;
        this.cause = cause;
        this.published = false;
        this.createdAt = LocalDateTime.now();
        instances.add(this);
        this.id = instances.size();
   }

   public int getMaxSize() {
       return maxSize;
   }
   public String getName() {
       return name;
   }
   public String getCause() {
       return cause;
   }

   public static ArrayList<Squad> getAll(){
       return instances;
   }
   public static void clearAllSquad(){
       instances.clear();
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
    public static Squad findById(int id){
        return instances.get(id-1);
    }

    public void update(int maxSize, String name, String cause) {
        this.maxSize = maxSize;
        this.name = name;
        this.cause = cause;
    }
    public void deleteSquad(){
        instances.remove(id-1);
    }

}
