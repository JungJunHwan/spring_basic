package com.beyond.basic.b2_board.dtos;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.repository.MemberMemoryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberCreateDto {
    private String name;
    private String email;
    private String password;

    public Member toEntity(){
        return new Member(this.name, this.email, this.password);
    }
}

