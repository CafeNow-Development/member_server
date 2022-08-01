package kr.co.member.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class KakaoRequestResult {

    String msg;
    int code;
    String email;

}
