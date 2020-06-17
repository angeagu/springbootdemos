package com.example.springbootdemos.basicrest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDTO {

    private String id;
    private String fullName;
    private Date creation;

}
