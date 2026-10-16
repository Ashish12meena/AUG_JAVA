public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("S001", "Shreya shukla");
        Student s2 = new Student("S002", "Soumya ");
        Student s3 =  new Student();

       


        System.out.println("Student 1 : " + s1.id);
        System.out.println("Student 2 : " + s2);
        System.out.println("Student 3 : " + s3.id);

        s3 = null;

        System.out.println("Student 3 : " + s3.id);


    }
}
