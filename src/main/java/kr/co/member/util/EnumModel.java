package kr.co.member.util;

public interface EnumModel {

    default EnumValue getEnumValue(){
        return new EnumValue(getKey(), getValue());
    }

    String getKey();

    String getValue();
}