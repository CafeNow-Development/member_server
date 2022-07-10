package kr.co.member.external.kakao;

import lombok.Value;

@Value(staticConstructor = "of")
public class KakaoRequestResult {

    String msg;
    int code;
    String email;

}
