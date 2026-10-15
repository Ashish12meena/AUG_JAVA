public class Main {
  public static void main(String[] args) {
        
        Student student1 = new Student("Ritika", 20);
        Student student2 = new Student("Disha", 22);

        student1.getInfo();
        student2.getInfo();

        student1.setCollegeName("XYZ College");   // static: changes for student2 also
        student1.setName("ABC");

        System.out.println();

        student1.getInfo();
        student2.getInfo();
    }
}
