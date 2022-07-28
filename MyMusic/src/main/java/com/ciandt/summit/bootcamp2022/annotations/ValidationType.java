package com.ciandt.summit.bootcamp2022.annotations;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ValidationType {
    ANNONYMOUS("Online");

    String value;

}
