package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.dtos.*;
import com.beyond.basic.b2_board.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

//RestController : 모든 메서드에 @ResponseBody가 붙음
@RestController
@RequestMapping("/member/rest")
public class MemberRestController {
//    DI
    private final MemberService memberService;
    public MemberRestController(MemberService memberService){
        this.memberService = memberService;
    }

//    회원 목록 조회
    @GetMapping("/list")
    public ResponseEntity<?> memberList(){
        List<MemberListRes> memberListResList = memberService.findAll();
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value()
                , "members are found", memberListResList), HttpStatus.OK);
    }

//    회원 상세 조회 (pathVariable)
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> memberDetail(@PathVariable Long id){
        MemberDetailDto dto = memberService.findById(id);
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value()
                , "member is found", dto), HttpStatus.OK);
    }

//    회원 가입
    @PostMapping("/create")
    public ResponseEntity<?> memberCreatePost(@RequestBody MemberCreateDto memberCreateDto){
//        예외 처리 : 비밀번호 8자리 미만, 이미 존재하는 email
        Member member = memberService.save2(memberCreateDto);
        return new ResponseEntity<>(new CommonDto(HttpStatus.CREATED.value()
                , "member created success", member.getId()), HttpStatus.CREATED);
    }

//    비밀번호 변경
//    get:조회, post:등록, patch:부분수정, put:대체, delete:삭제
//    axios.patch
    @PatchMapping("/update/pw")
    public ResponseEntity<?> updatePw(@RequestBody MemberUpdatePwDto memberUpdatePwDto){
        memberService.updatePassword(memberUpdatePwDto);
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value()
                , "password update success", memberUpdatePwDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id){
        memberService.delete(id);
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value()
                , "member delete success", "member delete success"), HttpStatus.OK);
    }
}
