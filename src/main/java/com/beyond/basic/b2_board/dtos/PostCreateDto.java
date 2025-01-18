package com.beyond.basic.b2_board.dtos;

import com.beyond.basic.b2_board.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostCreateDto {
    @NotEmpty
    private String title;
    private String contents;
    private Long memberId;

    public Post toEntity(){
//        빌더 패턴은 엔티티명.builder().사이에 원하는 변수 세팅.build();
        return Post.builder().title(this.title).contents(this.contents).memberId(this.memberId).build();
    }
}
