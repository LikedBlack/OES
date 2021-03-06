package priv.barrow.oes.portlet.model;

public class Teacher {
    private long userId;
    private long number;
    private String firstName;
    private String lastName;
    private String email;

    public Teacher(){}

    public Teacher(long userId, long number, String firstName, String lastName, String email) {
        this.userId = userId;
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
