package kr.co.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
public class RegisterPartTimer {

    private String email;
    private String passWord;
    private String nickName;

}
