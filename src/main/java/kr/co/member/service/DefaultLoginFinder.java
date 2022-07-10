package kr.co.member.service;

import com.google.gson.Gson;
import kr.co.member.domain.LoginRole;
import kr.co.member.external.kakao.KakaoLogin;
import kr.co.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static kr.co.member.domain.LoginRole.카카오;

@Component
public class DefaultLoginFinder implements LoginFinder{

    @Value("${spring.social.kakao.url.profile}")
    private String kakaoProfileUrl;
    private final Map<LoginRole, Login> loginMap = new HashMap<>();

    public DefaultLoginFinder(MemberRepository memberRepository) {
        loginMap.put(카카오, new KakaoLogin(memberRepository, kakaoProfileUrl, new RestTemplate(), new Gson()));
    }

    @Override
    public Login findBy(LoginRole type) {
        Login login = loginMap.get(type);

        if (login == null) {
            throw new IllegalStateException();
        }

        return login;
    }
}
