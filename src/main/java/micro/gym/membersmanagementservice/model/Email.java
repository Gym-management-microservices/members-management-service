package micro.gym.membersmanagementservice.model;

public class Email {
    private String email_value;

    public Email() {
    }

    public Email(String email_value) {
        this.email_value = email_value;
    }

    public String getEmail_value() {
        return email_value;
    }

    public void setEmail_value(String email_value) {
        this.email_value = email_value;
    }
}
