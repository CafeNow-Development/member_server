package kr.co.member.service;

import kr.co.member.domain.RequestResult;
import kr.co.member.domain.LoginTarget;

import static kr.co.member.domain.LoginRole.로컬;

public class LoaclLogin implements Login {
    @Override
    public RequestResult execute(LoginTarget target) {
        return null;
    }

    @Override
    public String getType() {
        return 로컬.getValue();
    }
}
