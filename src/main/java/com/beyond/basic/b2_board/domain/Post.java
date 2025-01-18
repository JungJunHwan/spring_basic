package com.beyond.basic.b2_board.domain;

import com.beyond.basic.b2_board.dtos.MemberListRes;
import com.beyond.basic.b2_board.dtos.PostDetailDto;
import com.beyond.basic.b2_board.dtos.PostListRes;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@ToString
@Entity
@AllArgsConstructor
//Builder 어노테이션을 사용하여, 빌더 패턴으로 엔티티의 생성자 구성
//빌더 패턴의 장점 : 매개변수의 순서와 개수를 유연하게 세팅할 수 있다
@Builder
public class Post extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String contents;
    private Long memberId;

    public PostListRes listFromEntity(){
        return new PostListRes(this.id, this.title);
    }

    public PostDetailDto detailFromEntity(){
        return new PostDetailDto(this.id, this.title, this.contents, this.memberId);
    }
}
