package kr.co.member.service;

import kr.co.member.domain.LoginRole;
import kr.co.member.domain.LoginTarget;
import kr.co.member.external.kakao.KakaoRequestResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLogin {

    private final DefaultLoginFinder defaultLoginFinder;

    public KakaoRequestResult execute(LoginRole type, LoginTarget loginTarget) {
        Login login = defaultLoginFinder.findBy(type);
        return login.execute(loginTarget);
    }
}
