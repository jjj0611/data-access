package org.kiworkshop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor // ibatis 사용을 위한 기본 생성자 및 setter
@Getter
@AllArgsConstructor
public class Member {
    private Long id;
    private String name;
    private Double point;

    public void changeName(String name) {
        this.name = name;
    }
}

