package kr.co.member.service;

import kr.co.member.domain.LoginRole;
import kr.co.member.domain.LoginTarget;
import kr.co.member.domain.MemberRole;

public interface LoginFinder {
    Login findBy(MemberRole memberType);
}
