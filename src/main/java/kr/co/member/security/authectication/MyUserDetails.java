package kr.co.member.security.authectication;

import kr.co.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import static kr.co.member.domain.MemberRole.사장님;
import static kr.co.member.domain.MemberRole.알바생;

@Service
@RequiredArgsConstructor
public class MyUserDetails implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        return memberRepository.findByEmailAndMemberType(email, 알바생).orElseThrow();
    }

    public UserDetails CustomLoadUserByUsername(String email) {
        return memberRepository.findByEmailAndMemberType(email, 사장님).orElseThrow();
    }
}