package com.securitySimulator.model.DTO;


import com.securitySimulator.model.enums.ViolationType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ViolationDTO {
    Integer Id;
    ViolationType violationType;
}
