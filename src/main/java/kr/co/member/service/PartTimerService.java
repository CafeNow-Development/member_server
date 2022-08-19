package kr.co.member.service;

import kr.co.member.domain.Member;
import kr.co.member.domain.RegisterPartTimer;
import kr.co.member.domain.RequestResult;
import kr.co.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

import static kr.co.member.domain.LoginRole.로컬;
import static kr.co.member.domain.MemberRole.알바생;

@Service
@RequiredArgsConstructor
public class PartTimerService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public RequestResult register(RegisterPartTimer registerPartTimer) {
        Optional<Member> byEmailAndMemberType = memberRepository.findByEmailAndMemberType(registerPartTimer.getEmail(), 알바생);
        if(byEmailAndMemberType.isPresent()) {
            return RequestResult.of("이메일 중복 입니다.", 400, null);
        }
        memberRepository.save(
                Member.builder()
                        .email(registerPartTimer.getEmail())
                        .name(registerPartTimer.getNickName())
                        .password(passwordEncoder.encode(registerPartTimer.getPassWord()))
                        .loginType(로컬)
                        .memberType(Collections.singletonList(알바생))
                        .build()
        );

        return RequestResult.of("성공하였습니다.", 200, registerPartTimer.getEmail());
    }
}
