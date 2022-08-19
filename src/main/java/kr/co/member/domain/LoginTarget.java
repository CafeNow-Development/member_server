package kr.co.member.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
public class LoginTarget {

    private String accessToken;
    private MemberRole memberRole;
    private String email;
    private String passWord;

    public Member createKakaoMember(KakaoProfile profile, LoginRole loginRole) {
        return Member.builder()
                .email(profile.getEmail())
                .name(profile.getNickName())
                .loginType(loginRole)
                .memberType(Collections.singletonList(memberRole))
                .profile_image_url(profile.getProfile_image_url())
                .thumbnail_image_url(profile.getThumbnail_image_url())
                .is_email_valid(profile.getIs_email_valid())
                .is_email_verified(profile.getIs_email_verified())
                .build();
    }

}
