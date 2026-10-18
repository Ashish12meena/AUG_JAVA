class StudentInfo {
    String name;
    int age;
    boolean passed;
    
    // no constructor written, so the compiler adds: StudentInfo() { }
}

public class DefaultConstructor {
    public static void main(String[] args) {
        StudentInfo s1 = new StudentInfo();

        System.out.println(s1.name);     // null
        System.out.println(s1.age);      // 0
        System.out.println(s1.passed);   // false
    }
}
