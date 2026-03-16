package micro.gym.membersmanagementservice.service;

import micro.gym.membersmanagementservice.model.DatosEntrenamiento;
import micro.gym.membersmanagementservice.model.Member;
import micro.gym.membersmanagementservice.model.MemberId;
import micro.gym.membersmanagementservice.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntrenamientoProducer entrenamientoProducer;

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

    public Member registerMember(Member member)  {
        return memberRepository.save(member);
    }

    public boolean isActiveMember(MemberId memberId) {
        Member member = findByMemberId(memberId);
        if (member != null) {
           if(member.getSubscription().isActive())return true;
        }
        return false;
    }

    public void inscribir(DatosEntrenamiento datos) {
        Member member = memberRepository.findById(datos.getMemberId())
                .orElseThrow(() -> new RuntimeException("Miembro no encontrado: " + datos.getMemberId()));
        entrenamientoProducer.enviarDatos(datos);
    }
}

