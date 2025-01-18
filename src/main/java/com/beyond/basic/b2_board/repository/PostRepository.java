package com.beyond.basic.b2_board.repository;

import com.beyond.basic.b2_board.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
