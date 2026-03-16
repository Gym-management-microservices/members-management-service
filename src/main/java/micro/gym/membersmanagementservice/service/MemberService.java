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

    @Autowired
    private MemberRegisteredProducer memberRegisteredProducer;

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Member findByMemberId(MemberId memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    public void deleteByMemberId(MemberId memberId) {
        memberRepository.deleteById(memberId);
    }

    public Member registerMember(Member member) {
        Member saved = memberRepository.save(member);
        memberRegisteredProducer.publishMemberRegistered(saved);
        return saved;
    }

    public boolean isActiveMember(MemberId memberId) {
        Member member = findByMemberId(memberId);
        if (member != null) {
           if(member.getSubscription().isActive())return true;
        }
        return false;
    }

}

