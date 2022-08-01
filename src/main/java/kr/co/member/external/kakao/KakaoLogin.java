package kr.co.member.external.kakao;

import com.google.gson.Gson;
import kr.co.member.domain.KakaoProfile;
import kr.co.member.domain.KakaoRequestResult;
import kr.co.member.domain.LoginTarget;
import kr.co.member.domain.Member;
import kr.co.member.repository.MemberRepository;
import kr.co.member.service.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;

import java.util.Optional;

import static kr.co.member.domain.LoginRole.카카오;

@RequiredArgsConstructor
public class KakaoLogin implements Login {

    private final MemberRepository memberRepository;
    private final String kakaoProfileUrl;
    private final RestTemplate restTemplate;
    private final Gson gson;

    @Override
    public KakaoRequestResult execute(LoginTarget target) {
        if (target.getAccessToken() == null) {
            System.out.println("저리가");
        }

        HttpHeaders headers = getHttpHeaders();
        headers.set("Authorization", "Bearer " + target.getAccessToken());

        // Set http entity
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, headers);

        try {
            // Request profile
            ResponseEntity<String> response = restTemplate.postForEntity(kakaoProfileUrl, request, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                KakaoProfile kakaoProfile = gson.fromJson(response.getBody(), KakaoProfile.class);

                Optional<Member> byEmailAndLoginType = memberRepository.findByEmailAndLoginType(kakaoProfile.getEmail(), 카카오);
                if (byEmailAndLoginType.isEmpty()) {
                    Member saveMember = memberRepository.save(target.createKakaoMember(kakaoProfile, 카카오));
                    return KakaoRequestResult.of("성공하였습니다.", 200, saveMember.getEmail());
                }

                return KakaoRequestResult.of("성공하였습니다.", 200, byEmailAndLoginType.get().getEmail());
            }
        } catch (Exception e) {
            return KakaoRequestResult.of("실패하였습니다.", 400, null);
        }

        return KakaoRequestResult.of("실패하였습니다.", 400, null);
    }

    @Override
    public String getType() {
        return 카카오.getValue();
    }

    @NotNull
    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }
}
