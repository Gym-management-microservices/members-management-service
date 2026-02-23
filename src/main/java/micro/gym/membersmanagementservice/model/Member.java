package micro.gym.membersmanagementservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Member {

    @EmbeddedId
    private MemberId id;

    @Column(name = "name")
    private String name;

    @Embedded
    private Email email;

    @Embedded
    private RegistrationDate registrationDate;

    @Embedded
    private Subscription subscription;

    public void changeEmail(Email email) {
        this.email = email;
    }

    public void changeRegistrationDate(RegistrationDate registrationDate) {
        this.registrationDate = registrationDate;
    }


}
