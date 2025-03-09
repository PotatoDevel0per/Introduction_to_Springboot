package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    //Java ver 8 이상부터 들어간 기능
    //만약에 가져온 값이 null이라면, null을 반환하는 대신
    //Optional이라는 형태로 감싸서 반환하는 방법
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
