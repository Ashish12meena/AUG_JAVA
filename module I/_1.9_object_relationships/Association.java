class Learner {
    String name;

    Learner(String name) {
        this.name = name;
    }
}

class Teacher {
    String name;

    Teacher(String name) {
        this.name = name;
    }

    void teach(Learner s) {     // Teacher uses a Learner
        System.out.println(name + " teaches " + s.name);
    }
}

public class Association {
    public static void main(String[] args) {
        Teacher t1 = new Teacher("Mr. Verma");
        Learner s1 = new Learner("Ritika");
        Learner s2 = new Learner("Disha");

        t1.teach(s1);
        t1.teach(s2);
    }
}
