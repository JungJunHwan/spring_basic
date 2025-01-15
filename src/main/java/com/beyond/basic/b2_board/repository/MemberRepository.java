package com.beyond.basic.b2_board.repository;


import com.beyond.basic.b2_board.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//SpringDataJpa가 되기 위해서는 JpaRepository를 상속해야 하고 상속시 엔티티 명과 pk타입을 명시
//JpaRepository를 상속함으로서 JpaRepository의 주요기능(각종 CRUD 기능 사전 구현) 상속
public interface MemberRepository extends JpaRepository<Member, Long> {
//    save, saveAll, findById는 사전에 구현
//    그외에 다른 컬럼으로 조회할때는 findBy + 컬럼명 형식으로 선언만하면 실행 시점에 구현
    Optional<Member> findByEmail(String email);
}
