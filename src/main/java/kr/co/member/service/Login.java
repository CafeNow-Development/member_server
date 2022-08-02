package kr.co.member.service;

import kr.co.member.domain.LoginTarget;
import kr.co.member.domain.RequestResult;

public interface Login {
    RequestResult execute(LoginTarget target);
    String getType();
}
