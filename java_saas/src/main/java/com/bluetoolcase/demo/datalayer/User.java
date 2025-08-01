package com.bluetoolcase.demo.datalayer;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    
    @Id
    private String id;
  
    private String name;
    private String email;

    public User(){

    }

    public User(String id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(final String email) {
        this.email = email;
    }
}

