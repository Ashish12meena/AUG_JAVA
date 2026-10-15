public class Student {
    static String collegeName;   // same for all students, so one shared copy instead of one per object
    String name;
    int age;

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void setCollegeName(String collegeName) {
        Student.collegeName = collegeName;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void getInfo() {
        System.out.println("College Name: " + collegeName);
        System.out.println("Student Name: " + name);
        System.out.println("Student Age: " + age);
    }
}
