package com.security.practisesecurityjwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer",uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class Customer {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private  String name;

    private String email;
    private String password;


}
