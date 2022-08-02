package kr.co.member.repository;

import kr.co.member.domain.LoginRole;
import kr.co.member.domain.Member;
import kr.co.member.domain.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmailAndLoginType(String email, LoginRole type);
    Optional<Member> findByEmailAndMemberType(String email, MemberRole type);

}
