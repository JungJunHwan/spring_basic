package com.beyond.basic.b2_board.service;

import com.beyond.basic.b2_board.dtos.MemberCreateDto;
import com.beyond.basic.b2_board.dtos.MemberDetailDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;
import com.beyond.basic.b2_board.repository.MemberJdbcRepository;
import com.beyond.basic.b2_board.repository.MemberMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
//아래 어노테이션을 통해 모든 메서드에 트랜잭션을 적용하고 만약 예외(unchecked)가 발생시 롤백 처리 자동화
@Transactional
public class MemberService {
    @Autowired
    MemberJdbcRepository memberRepository;

    public List<MemberListRes> findAll(){
//        List<MemberListRes> memberListRes = new ArrayList<>();
//        for(Member m : members){
//            memberListRes.add(m.listFromEntity());
//        }
//        return memberListRes;

//        위의 코드를 아래의 코드로 축소 가능
        return memberRepository.findAll().stream().map(m->m.listFromEntity()).collect(Collectors.toList());
    }

    public void save(MemberCreateDto memberCreateDto) throws IllegalArgumentException{
        if(memberCreateDto.getPassword().length() < 8) {
            throw new IllegalArgumentException("비밀번호 길이가 짧습니다");
        }
        else if (memberRepository.findByEmail(memberCreateDto.getEmail()).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 이메일입니다");
        }
        memberRepository.save(memberCreateDto.toEntity());
    }

    public MemberDetailDto findById(Long id) throws NoSuchElementException, RuntimeException{ //checked Exception이 아니지만 명시하는것이 좋음
//        Optional<Member> optionalMember = memberRepository.findById(id);
//        Member member = optionalMember.orElseThrow(()->new NoSuchElementException("없는 ID입니다"));
//        return member.detailFromEntity();

//        위의 코드를 아래의 코드로 축소 가능
        return memberRepository.findById(id).orElseThrow(()->new NoSuchElementException("없는 ID입니다")).detailFromEntity();
    }
}
