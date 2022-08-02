package kr.co.member.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class RequestResult {

    String msg;
    int code;
    String email;

}
