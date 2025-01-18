package com.beyond.basic.b2_board.service;

import com.beyond.basic.b2_board.domain.Post;
import com.beyond.basic.b2_board.dtos.PostCreateDto;
import com.beyond.basic.b2_board.dtos.PostDetailDto;
import com.beyond.basic.b2_board.dtos.PostListRes;
import com.beyond.basic.b2_board.repository.MemberRepository;
import com.beyond.basic.b2_board.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostService {
    //    DI
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    public PostService(PostRepository postRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    public Post save(PostCreateDto postCreateDto){
        if(memberRepository.findById(postCreateDto.getMemberId()).isEmpty()){
            throw new IllegalArgumentException("없는 회원입니다");
        }
        return postRepository.save(postCreateDto.toEntity());
    }

    public List<PostListRes> findAll(){
        return postRepository.findAll().stream().map(p->p.listFromEntity()).collect(Collectors.toList());
    }

    public PostDetailDto findById(Long id){
        return postRepository.findById(id).orElseThrow(()->new EntityNotFoundException("없는 글 번호입니다.")).detailFromEntity();
    }
}
