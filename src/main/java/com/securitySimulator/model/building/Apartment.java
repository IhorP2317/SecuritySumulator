package com.securitySimulator.model.building;

import com.securitySimulator.model.normative.Normative;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Apartments")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column
    String address;

    @ManyToOne
    @JoinColumn(name = "building_id")
    Building building;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    @JoinColumn(name = "apartment_id")
    ArrayList<Floor> floors;


    @OneToOne(cascade = CascadeType.ALL) // One-to-one relationship with Normative
    @JoinColumn(name = "normative_id")
    Normative normative;
    //ArrayList<Client> clients;
}
