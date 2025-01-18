package com.beyond.basic.b2_board.domain;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// 기본적으로 엔티티는 상속이 불가능하여 MappedSuperclass 붙여야 엔티티와의 상속관계가 성립 가능
@MappedSuperclass
@Getter
public class BaseTimeEntity {
//    java에서 캐멀케이스 사용시 db에는 created_time으로 컬럼명 변환됨
    @CreationTimestamp
    private LocalDateTime createdTime;
    @UpdateTimestamp
    private LocalDateTime updateTime;
}
