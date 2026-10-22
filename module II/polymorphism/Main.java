package polymorphism;

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

}

class Student extends Person {

    Student(String name, int age) {
        super(name, age);
    }

    void study() {
        System.out.println(name + " is studying");
    }
}

class Teacher extends Person {

    Teacher(String name, int age) {
        super(name, age);
    }

    void teach() {
        System.out.println(name + " is teaching");
    }
}

public class Main {
    public static void main(String[] args) {
        Person s1 = new Student("sakhshi", 20);
        // s1.study();

        Person t1 = new Teacher("Mr Verma", 40);
        // t1.teach();
        
    }

}
