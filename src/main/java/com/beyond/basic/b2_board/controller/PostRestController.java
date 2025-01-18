package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.domain.Post;
import com.beyond.basic.b2_board.dtos.CommonDto;
import com.beyond.basic.b2_board.dtos.PostCreateDto;
import com.beyond.basic.b2_board.dtos.PostDetailDto;
import com.beyond.basic.b2_board.dtos.PostListRes;
import com.beyond.basic.b2_board.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostRestController {
//    DI
    private final PostService postService;
    public PostRestController(PostService postService){
        this.postService = postService;
    }

//    등록
    @PostMapping("/create")
//    Valid와 NotEmpty 등 검증 어노테이션이 한 쌍
    public ResponseEntity<?> postCreate(@Valid @RequestBody PostCreateDto postCreateDto){
        Post post = postService.save(postCreateDto);
        return new ResponseEntity<>(new CommonDto(HttpStatus.CREATED.value()
                , "post created success", post.getId()), HttpStatus.CREATED);
    }

//    목록 조회
    @GetMapping("/list")
    public ResponseEntity<?> postList(){
        List<PostListRes> postListResList = postService.findAll();
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value()
                , "posts are found", postListResList), HttpStatus.OK);
    }

//    상세 조회
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> postDetail(@PathVariable Long id){
        PostDetailDto postDetailDto = postService.findById(id);
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value()
                , "member is found", postDetailDto), HttpStatus.OK);
    }
}
