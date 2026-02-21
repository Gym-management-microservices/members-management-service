package micro.gym.membersmanagementservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor (force = true) // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all fields as arguments
public class MemberId {

    private final String member_value;

    public String getMember_value() {
        return member_value;
    }
}
