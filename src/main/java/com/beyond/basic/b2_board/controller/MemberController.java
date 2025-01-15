package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.dtos.MemberCreateDto;
import com.beyond.basic.b2_board.dtos.MemberDetailDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;
import com.beyond.basic.b2_board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/member")
//@RequiredArgsConstructor
public class MemberController {
////    의존성 주입(DI) 방법 1. Autowired 어노테이션 사용 : 필드주입
//    @Autowired
//    private MemberService memberService;

//    의존성 주입 방법 2. 생성자 주입 방식 (가장 많이 사용)
//    장점1) final을 통해 상수로 사용 가능 (재할당 불가 -> 안정성 향상)
//    장점2) 다형성 구현 가능
//    장점3) 순환 참조를 컴파일타임에 확인 가능
    private final MemberService memberService;
//    싱글톤 객체로 만들어지는 시점에 아래 생성자가 호출. 생성자가 하나밖에 없을때에는 Autowired 생략 가능
//    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

////    의존성 주입 방법 3. RequiredArgsConstructor 어노테이션 사용 방법
////    @RequiredArgsConstructor : 반드시 초기화 되어야 하는 필드(final 키워드 등)를 대상으로 생성자를 자동으로 만들어주는 어노테이션
//    private final MemberService memberService;

//    홈 화면
    @GetMapping("")
    public String memberHome(){
        return "member/home";
    }

//    회원 목록 조회
    @GetMapping("/list")
    public String memberList(Model model){
        List<MemberListRes> memberListResList = memberService.findAll();
        model.addAttribute("memberList", memberListResList);
        return "member/member-list";
    }

//    회원 상세 조회 (pathVariable)
    @GetMapping("/detail/{id}")
    public String memberDetail(@PathVariable Long id, Model model){
        try {
//        name, email, password
            MemberDetailDto dto = memberService.findById(id);
            model.addAttribute("member", dto);
            return "member/detail";
        }catch (NoSuchElementException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/member-error";
        }catch (RuntimeException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/member-error";
        }
    }

//    회원 가입
    @GetMapping("/create")
    public String memberCreate(){
        return "member/create";
    }

    @PostMapping("/create")
    public String memberCreatePost(MemberCreateDto memberCreateDto, Model model){
//    예외 처리 : 비밀번호 8자리 미만, 이미 존재하는 email
        try {
            memberService.save(memberCreateDto);
//        새로운 화면 리턴이 아닌 url 재호출을 통해 redirect
            return "redirect:/member/list";
        }catch(IllegalArgumentException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/member-error";
        }
    }
}
