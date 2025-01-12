package com.beyond.basic.b2_board.service;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.dtos.MemberCreateDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;
import com.beyond.basic.b2_board.repository.MemberMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    MemberMemoryRepository memberMemoryRepository;

    public List<MemberListRes> findAll(){
        List<Member> members = memberMemoryRepository.findAll();
        List<MemberListRes> memberListRes = new ArrayList<>();
        for(Member m : members){
            memberListRes.add(new MemberListRes(m.getId(), m.getName(), m.getEmail()));
        }
        return memberListRes;
    }

    public void save(MemberCreateDto memberCreateDto){
        memberMemoryRepository.save(new Member(MemberMemoryRepository.id, memberCreateDto.getName()
                , memberCreateDto.getEmail(), memberCreateDto.getPassword()));
    }
}
