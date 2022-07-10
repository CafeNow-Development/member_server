package kr.co.member.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KakaoProfile {
    private String email;

    private String nickName;

    private String profile_image_url;

    private String thumbnail_image_url;

    private Boolean is_email_valid;

    private Boolean is_email_verified;
}
