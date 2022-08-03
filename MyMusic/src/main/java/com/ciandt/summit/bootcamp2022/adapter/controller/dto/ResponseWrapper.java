package com.ciandt.summit.bootcamp2022.adapter.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseWrapper<T> {

  private T data;

  public ResponseWrapper(T data) {
    this.data = data;
  }
}
