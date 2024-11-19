package com.Vetta.voiture_service.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.net.ProtocolFamily;

@Document(value = "voiture")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Voiture {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;


}
