package kr.co.member.service;

import kr.co.member.domain.LoginRole;
import kr.co.member.domain.LoginTarget;

public interface LoginFinder {
    Login findBy(LoginRole type);
}
