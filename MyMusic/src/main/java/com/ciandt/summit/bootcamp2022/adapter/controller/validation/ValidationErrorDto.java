package com.ciandt.summit.bootcamp2022.adapter.controller.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ValidationErrorDto {

  private String field;
  private String error;

}
