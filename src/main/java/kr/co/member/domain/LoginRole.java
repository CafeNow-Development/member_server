package kr.co.member.domain;

import kr.co.member.util.EnumModel;

public enum LoginRole implements EnumModel {
    카카오("KAKAO"),
    구글("GOOGLE"),
    로컬("LOCAL")
    ;

    private final String value;

    LoginRole(String value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return this.name();
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
