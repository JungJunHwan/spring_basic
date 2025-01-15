package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.dtos.MemberCreateDto;
import com.beyond.basic.b2_board.dtos.MemberDetailDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;
import com.beyond.basic.b2_board.dtos.MemberUpdatePwDto;
import com.beyond.basic.b2_board.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//모든 메서드에 @ResponseBody가 붙음
@RequestMapping("/member/rest")
public class MemberRestController {
//    DI
    private final MemberService memberService;
    public MemberRestController(MemberService memberService){
        this.memberService = memberService;
    }

//    회원 목록 조회
    @GetMapping("/list")
    public List<MemberListRes> memberList(){
        return memberService.findAll();
    }

//    회원 상세 조회 (pathVariable)
    @GetMapping("/detail/{id}")
    public MemberDetailDto memberDetail(@PathVariable Long id){
//        name, email, password
        return memberService.findById(id);
    }

    //    회원 가입
    @PostMapping("/create")
    public String memberCreatePost(@RequestBody MemberCreateDto memberCreateDto){
//    예외 처리 : 비밀번호 8자리 미만, 이미 존재하는 email
        memberService.save(memberCreateDto);
//        새로운 화면 리턴이 아닌 url 재호출을 통해 redirect
        return "OK";
    }

//    비밀번호 변경
//    get:조회, post:등록, patch:부분수정, put:대체, delete:삭제
//    axios.patch
    @PatchMapping("/update/pw")
    public String updatePw(@RequestBody MemberUpdatePwDto memberUpdatePwDto){
        memberService.updatePassword(memberUpdatePwDto);
        return "OK";
    }
}
