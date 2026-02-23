package micro.gym.membersmanagementservice.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor (force = true) // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all fields as arguments
public class MemberId implements Serializable {

    private final String member_value;

}
