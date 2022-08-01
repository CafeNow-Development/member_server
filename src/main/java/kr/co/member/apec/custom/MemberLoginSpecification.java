package kr.co.member.apec.custom;

import kr.co.member.apec.AbstractSpecification;
import kr.co.member.domain.MemberRole;
import org.springframework.stereotype.Component;

import static kr.co.member.domain.MemberRole.사장님;

@Component
public class MemberLoginSpecification extends AbstractSpecification<MemberRole> {
    @Override
    public boolean isSatisfiedBy(MemberRole candidate) {
        return candidate.equals(사장님);
    }
}
