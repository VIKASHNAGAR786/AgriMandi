package com.example.AgriMandi.entity;



import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
//public class Role {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false, unique = true)
//    private String name;
//
////    @ManyToMany(mappedBy = "roles")
////    private List<User> users;
//    
//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users = new HashSet<>();
//    
//   
//}
