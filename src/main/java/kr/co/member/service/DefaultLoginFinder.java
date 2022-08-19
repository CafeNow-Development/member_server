package kr.co.member.service;

import com.google.gson.Gson;
import kr.co.member.apec.custom.MemberLoginSpecification;
import kr.co.member.domain.LoginRole;
import kr.co.member.domain.MemberRole;
import kr.co.member.external.kakao.KakaoLogin;
import kr.co.member.repository.MemberRepository;
import kr.co.member.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static kr.co.member.domain.LoginRole.로컬;
import static kr.co.member.domain.LoginRole.카카오;
import static kr.co.member.domain.MemberRole.사장님;

@Component
public class DefaultLoginFinder implements LoginFinder{

    @Value("${spring.social.kakao.url.profile}")
    private String kakaoProfileUrl;
    private final Map<LoginRole, Login> loginMap = new HashMap<>();

    public DefaultLoginFinder(MemberRepository memberRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        loginMap.put(로컬, new LoaclLogin(memberRepository, passwordEncoder, jwtTokenProvider));
        loginMap.put(카카오, new KakaoLogin(memberRepository, kakaoProfileUrl, new RestTemplate(), new Gson()));
    }

    @Override
    public Login findBy(MemberRole memberType) {
        Login login;

        if (new MemberLoginSpecification().isSatisfiedBy(memberType)) {
            login = loginMap.get(카카오);
        } else {
            login = loginMap.get(로컬);
        }

        return login;
    }
}
