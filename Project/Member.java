package Project;
public class Member {

    private int memberId;
    private String name;
    private String email;

    // 2. Constructor
    public Member(int memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    // 3. Getters
    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // 4. Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // 5. Member Validation
    public boolean validateMember() {

        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        if (email == null || !email.contains("@")) {
            return false;
        }

        return true;
    }

    // 6. Display Member Details
    public void displayMember() {
        System.out.println("Member ID : " + memberId);
        System.out.println("Name      : " + name);
        System.out.println("Email     : " + email);
    }

    // 7. toString Method
    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}