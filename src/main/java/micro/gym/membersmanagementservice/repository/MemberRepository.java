package micro.gym.membersmanagementservice.repository;

import micro.gym.membersmanagementservice.model.Member;
import micro.gym.membersmanagementservice.model.MemberId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, MemberId> {

    Member findByEmail(String email);

}