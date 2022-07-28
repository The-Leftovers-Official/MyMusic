package com.ciandt.summit.bootcamp2022.http.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenRequestDto {

    private CreateTokenRequestDataDto data;
}
