package account;

public class User {
    private String firstName;
    private String lastName;
    private String jmbg;

    public User() {
    }

    public User(String firstName, String lastName, String jmbg) {
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

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        if(jmbg.length() != 13)
            System.out.println("JMBG need to have 13 numbers!");
        else
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
