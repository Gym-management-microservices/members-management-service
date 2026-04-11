package micro.gym.membersmanagementservice.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class ClassScheduleEventDTO implements Serializable {

    private String classId;
    private String className;
    private String trainerId;
    private LocalDate newScheduleStart;
    private LocalDate newScheduleEnd;
}
