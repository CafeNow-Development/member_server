package kr.co.member.service;

import kr.co.member.domain.LoginTarget;
import kr.co.member.domain.KakaoRequestResult;

public interface Login {
    KakaoRequestResult execute(LoginTarget target);
    String getType();
}
