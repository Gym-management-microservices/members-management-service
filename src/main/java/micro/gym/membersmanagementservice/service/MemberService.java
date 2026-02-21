package micro.gym.membersmanagementservice.service;

import micro.gym.membersmanagementservice.model.Member;
import micro.gym.membersmanagementservice.model.MemberId;
import micro.gym.membersmanagementservice.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Member findByMemberId(MemberId memberId) {
        return memberRepository.findByMemberId(memberId);
    }

    public void deleteByMemberId(MemberId memberId) {
        memberRepository.deleteByMemberId(memberId);
    }

    public Member registerMember(Member member) {
        return memberRepository.save(member);
    }

    public boolean isActiveMember(MemberId memberId) {
        Member member = findByMemberId(memberId);
        if (member != null) {
           if(member.getSubscription().isActive())return true;
        }
        return false;
    }

}

