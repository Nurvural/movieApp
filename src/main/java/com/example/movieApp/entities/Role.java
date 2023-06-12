package com.example.movieApp.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="roles")
@Data
public class Role
{
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="roleType")
    private String roleType;
    
    @ManyToMany(cascade = {CascadeType.MERGE},
            mappedBy = "roles")
    private List<User> users;
    

}