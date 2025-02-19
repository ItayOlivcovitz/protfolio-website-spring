package com.growhire.site.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Visitor {

    @Id
    private String ip; // IP as Primary Key

    @JsonFormat(pattern = "dd/MM/yy /HH:mm") // Match the frontend format
    private LocalDateTime date; // Date and time of the visit

    private String platform; // Platform information
}
