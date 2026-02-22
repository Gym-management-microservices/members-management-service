package micro.gym.membersmanagementservice.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor

public class Email {
    private String email_value;

    public Email() {
    }


    public String getEmail_value() {
        return email_value;
    }

    public void setEmail_value(String email_value) {
        this.email_value = email_value;
    }
}
