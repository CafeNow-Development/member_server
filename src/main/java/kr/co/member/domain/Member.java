package kr.co.member.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor_ = @PersistenceConstructor)
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(length = 100)
    private String password;

    private String name;

    @Enumerated(STRING)
    private LoginRole loginType;

    @Enumerated(STRING) @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "role_admin", joinColumns = @JoinColumn(name = "adminIdx"))
    private List<MemberRole> memberType;

    @Column(length = 500)
    private String profile_image_url;

    @Column(length = 500)
    private String thumbnail_image_url;

    /*
        이메일 유효 여부
        turn : 유효한 이메일
        false : 이메일이 다른 카카오계정에 사용돼 만료
     */
    private Boolean is_email_valid;

    /*
        이메일 인증 여부
        turn : 인증된 이메일
        false : 인증되지 않은 이메일
     */
    private Boolean is_email_verified;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> rolesConvertString = this.memberType.stream().map(Enum::name).collect(Collectors.toList());
        return rolesConvertString.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public String getMemberType() {
        return getAuthorities().iterator().next().toString();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
