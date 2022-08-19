package kr.co.member.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.member.domain.LoginRole;
import kr.co.member.domain.LoginTarget;
import kr.co.member.domain.MemberRole;
import kr.co.member.domain.RequestResult;
import kr.co.member.service.DefaultLoginFinder;
import kr.co.member.service.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final DefaultLoginFinder loginFinder;

    @ApiOperation(value = "로그인", notes = "회원 로그인을 한다.")
    @PostMapping("/member/login")
    public RequestResult login(@RequestBody LoginTarget loginTarget) {
        Login login = loginFinder.findBy(loginTarget.getMemberRole());
        return login.execute(loginTarget);
    }
}
