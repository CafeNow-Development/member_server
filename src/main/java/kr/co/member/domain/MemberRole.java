package kr.co.member.domain;

import kr.co.member.util.EnumModel;

public enum MemberRole implements EnumModel {
    사장님("OWNER"),
    알바생("PART_TIMER")
    ;

    private final String value;

    MemberRole(String value) {
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
