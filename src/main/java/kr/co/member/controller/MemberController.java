package kr.co.member.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.member.domain.*;
import kr.co.member.service.DefaultLoginFinder;
import kr.co.member.service.Login;
import kr.co.member.service.PartTimerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final DefaultLoginFinder loginFinder;
    private final PartTimerService partTimerService;

    @ApiOperation(value = "로그인", notes = "회원 로그인을 한다.")
    @PostMapping("/member/login")
    public RequestResult login(@RequestBody LoginTarget loginTarget) {
        Login login = loginFinder.findBy(loginTarget.getMemberRole());
        return login.execute(loginTarget);
    }

    @ApiOperation(value = "회원가입", notes = "알바생을 등록 한다.")
    @PostMapping("/part-timer/register")
    public RequestResult partTimerRegister(@RequestBody RegisterPartTimer registerPartTimer) {
        return partTimerService.register(registerPartTimer);
    }
}
