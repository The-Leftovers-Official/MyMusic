package com.ciandt.summit.bootcamp2022.controller.dto;

import lombok.Data;

@Data
public class ResponseWrapper<T> {

  private T data;

  public ResponseWrapper(T data) {
    this.data = data;
  }
}
