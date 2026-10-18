final class IdCard {
    private final int rollNo;
    private final String name;
    private final String college;

    public IdCard(int rollNo, String name, String college) {
        this.rollNo = rollNo;
        this.name = name;
        this.college = college;
    }

    public int getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }
    public String setName() {
        return name;
    }

    public String getCollege() {
        return college;
    }
}

public class ImmutableClass {
    public static void main(String[] args) {
        IdCard card = new IdCard(101, "Ritika", "VGU College");

        // card.name = "Disha";   // ERROR: private and final
        // no setName() method, so the card can never change

        System.out.println(card.getRollNo() + " " + card.getName() + " " + card.getCollege());
    }
}
