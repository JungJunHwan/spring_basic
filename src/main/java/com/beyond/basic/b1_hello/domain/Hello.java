package com.beyond.basic.b1_hello.domain;

import lombok.*;

//@Getter
@NoArgsConstructor //기본 생성자
@AllArgsConstructor // 모든 매개변수 있는 생성자
@Data // : Getter, Setter, toString 메서드까지 포함하는 어노테이션
public class Hello {
    private String name;
//    @Setter //email 변수에 관한 setter만 설정 가능
    private String email;
}
