package atm;

public class User {
    private String firstName;
    private String lastName;
    private int jmbg;

    public User() {
    }

    public User(String firstName, String lastName, int jmbg) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbg = jmbg;
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

    public int getJmbg() {
        return jmbg;
    }

    public void setJmbg(int jmbg) {
        this.jmbg = jmbg;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", jmbg=" + jmbg +
                '}';
    }
}
