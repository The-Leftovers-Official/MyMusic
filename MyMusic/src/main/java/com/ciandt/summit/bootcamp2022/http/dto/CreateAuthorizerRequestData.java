package com.ciandt.summit.bootcamp2022.http.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAuthorizerRequestData {

    private String name;

    private String token;

}
