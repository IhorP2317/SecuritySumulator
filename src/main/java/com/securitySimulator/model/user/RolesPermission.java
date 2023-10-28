package com.securitySimulator.model.user;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table
public class RolesPermission implements Serializable {
    static final Long serialVersionUID = 572257273198993868L;

    @Id
    @GeneratedValue
     Integer id;

    @Column
     String permission;

    @Column
     String roleName;
}

