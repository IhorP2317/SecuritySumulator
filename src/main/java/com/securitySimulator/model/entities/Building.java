package com.securitySimulator.model.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Buildings")
public class Building {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Getter
    @Setter
    String coordinates;
    @Getter
    @Setter
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Apartment> apartments;

}
