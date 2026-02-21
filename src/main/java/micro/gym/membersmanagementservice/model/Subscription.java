package micro.gym.membersmanagementservice.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Subscription {

    private LocalDate startDate;
    private LocalDate endDate;


    public Subscription() {
    }

    public Subscription(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isActive() {
        LocalDate today = LocalDate.now();
        return (startDate.isBefore(today) || startDate.isEqual(today)) && (endDate.isAfter(today) || endDate.isEqual(today));
    }
}
