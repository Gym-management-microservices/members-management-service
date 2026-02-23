package micro.gym.membersmanagementservice;


import micro.gym.membersmanagementservice.model.Email;
import micro.gym.membersmanagementservice.model.MemberId;
import micro.gym.membersmanagementservice.model.RegistrationDate;
import micro.gym.membersmanagementservice.model.Subscription;
import micro.gym.membersmanagementservice.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void run(String... args) {

        MemberId memberId = new MemberId("1");
        String name = "Lina";
        Email email = new Email("lina.andrade0411@gmail.com");
        RegistrationDate registrationDate = new RegistrationDate(LocalDate.of(2025,02,12));
        Subscription subscription = new Subscription(LocalDate.of(2025,02,12), LocalDate.of(2025,03,12));

        memberRepository.save(micro.gym.membersmanagementservice.model.Member.builder()
                .id(memberId)
                .name(name)
                .email(email)
                .registrationDate(registrationDate)
                .subscription(subscription)
                .build());

        System.out.println("Data loaded successfully!");
    }
}
