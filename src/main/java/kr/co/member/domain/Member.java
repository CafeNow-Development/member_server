package kr.co.member.domain;

import lombok.*;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor_ = @PersistenceConstructor)
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String email;

    private String name;

    private LoginRole loginType;

    private MemberRole memberType;

    private String profile_image_url;

    private String thumbnail_image_url;

    private Boolean is_email_valid;

    private Boolean is_email_verified;
}
