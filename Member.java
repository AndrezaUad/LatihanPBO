public class Member extends Person {
    public Member(String name, String id) {
        super(name, id);
    }

    public void displayInfo() {
        System.out.println("Name: " + getName() + ", ID: " + getId());
    }
}
