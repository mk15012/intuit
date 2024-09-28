package com.example.intuit.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    private String name;
    private String email;
    private String userType;

}