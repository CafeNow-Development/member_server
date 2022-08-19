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

//    public Member createLocalMember(LoginRole loginRole) {
//        return Member.builder()
//                .email(this.email)
//                .name(this.nickName)
//                .password(this.passWord)
//                .loginType(loginRole)
//                .memberType(Collections.singletonList(사장님))
//                .profile_image_url(null)
//                .thumbnail_image_url(null)
//                .is_email_valid(null)
//                .is_email_verified(null)
//                .build();
//    }

}
