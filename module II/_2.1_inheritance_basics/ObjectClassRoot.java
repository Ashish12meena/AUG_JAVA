import java.util.Objects;

class Visitor {            // same as: class Visitor extends Object
    String name;
    int age;

    Visitor(String name,int age) {
        this.name = name;
        this.age = 40;
    }

    //  @Override
    // public int hashCode() {
    //     return Objects.hash(name, age);
    // }
}

public class ObjectClassRoot {
    public static void main(String[] args) {
        Visitor v1 = new Visitor("Ritika",20);

        // these methods come from the Object class
        System.out.println(v1.toString());
        System.out.println(v1.hashCode());
        System.out.println(v1.getClass().getName());

        Object o = v1;     // every object is an Object
        System.out.println(o instanceof Object);
    }
}
