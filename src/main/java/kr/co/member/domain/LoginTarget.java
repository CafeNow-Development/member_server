package kr.co.member.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static kr.co.member.domain.MemberRole.사장님;
import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
public class LoginTarget {

    private String accessToken;

    public Member createKakaoMember(KakaoProfile profile, LoginRole loginRole) {
        return Member.builder()
                .email(profile.getEmail())
                .name(profile.getNickName())
                .loginType(loginRole)
                .memberType(사장님)
                .profile_image_url(profile.getProfile_image_url())
                .thumbnail_image_url(profile.getThumbnail_image_url())
                .is_email_valid(profile.getIs_email_valid())
                .is_email_verified(profile.getIs_email_verified())
                .build();
    }
}
