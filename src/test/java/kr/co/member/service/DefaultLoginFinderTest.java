package kr.co.member.service;

import kr.co.member.repository.MemberRepository;
import kr.co.member.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static kr.co.member.domain.LoginRole.구글;
import static kr.co.member.domain.LoginRole.카카오;
import static kr.co.member.domain.MemberRole.사장님;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DefaultLoginFinderTest {

    DefaultLoginFinder sut;

    @Mock
    MemberRepository memberRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    void setUp() {
        sut = new DefaultLoginFinder(memberRepository, passwordEncoder, jwtTokenProvider);
    }

    @Test
    void findByLoginType() {
        Login login = sut.findBy(사장님);
        assertThat(login.getType()).isEqualTo("KAKAO");
    }
}