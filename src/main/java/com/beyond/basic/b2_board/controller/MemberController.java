package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.dtos.MemberCreateDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;
import com.beyond.basic.b2_board.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {
//    의존성 주입(DI) 방법 1. Autowired 어노테이션 사용 : 필드주입
    @Autowired
    private MemberService memberService;



//    홈 화면
    @GetMapping("")
    public String memberHome(){
        return "member/home";
    }

//    회원 목록 조회
    @GetMapping("/list")
    public String memberList(Model model){
        System.out.println(22);
        List<MemberListRes> memberListResList = memberService.findAll();
        model.addAttribute("memberList", memberListResList);
        return "member/member-list";
    }

//    회원 상세 조회 (pathVariable)
    @GetMapping("/detail/{id}")
    public String memberDetail(@PathVariable Long id, Model model){
        model.addAttribute("id", id);
        System.out.println(id);
        return "member/detail";
    }

//    회원 가입
    @GetMapping("/create")
    public String memberCreate(){
        return "member/create";
    }

    @PostMapping("/create")
    public String memberCreatePost(MemberCreateDto memberCreateDto){
        memberService.save(memberCreateDto);
//        새로운 화면 리턴이 아닌 url 재호출을 통해 redirect
        return "redirect:/member/list";
    }
}
