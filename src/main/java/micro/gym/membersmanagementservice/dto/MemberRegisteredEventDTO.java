package micro.gym.membersmanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberRegisteredEventDTO implements Serializable {
    private String memberId;
    private String memberName;
    private String email;
}
