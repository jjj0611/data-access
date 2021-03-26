package org.kiworkshop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Member {
    private Long id;
    private String name;
    private Double point;
}
