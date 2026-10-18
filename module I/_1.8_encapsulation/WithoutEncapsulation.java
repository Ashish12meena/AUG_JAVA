class Result {
    String name;
    int marks;
}

public class WithoutEncapsulation {
    public static void main(String[] args) {
        Result r1 = new Result();
        r1.name = "Ritika";
        r1.marks = -50;     // wrong value, but Java accepts it

        System.out.println(r1.name + " got " + r1.marks);
    }
}
