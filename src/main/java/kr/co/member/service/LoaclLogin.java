package kr.co.member.service;

import kr.co.member.domain.Member;
import kr.co.member.domain.RequestResult;
import kr.co.member.domain.LoginTarget;
import kr.co.member.repository.MemberRepository;
import kr.co.member.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static kr.co.member.domain.LoginRole.로컬;
@RequiredArgsConstructor
public class LoaclLogin implements Login {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public RequestResult execute(LoginTarget target) {
        String accessToken = null;
        Optional<Member> member = memberRepository.findByEmailAndMemberType(target.getEmail(), target.getMemberRole());
        if(member.isPresent()) {
            boolean checkPassword = passwordEncoder.matches(target.getPassWord(), member.get().getPassword());
            if(!checkPassword) {
                return RequestResult.of("비밀번호가 다릅니다.", 400, null);
            }
            accessToken = jwtTokenProvider.createToken(member.get());
        } else {
            return RequestResult.of("알바생 회원가입을 진행해주세요.", 400, null);
        }

        return RequestResult.of("성공하였습니다.", 200, accessToken);
    }

    @Override
    public String getType() {
        return 로컬.getValue();
    }
}
