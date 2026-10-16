public class Student {

    String id;
    String name;
    String department;
    int marks[] = new int[5];

    Student(String id , String name){
        this.id = id;
        this.name = name;
    }
   
    Student(){
      this("S003", "Shreya");
    }
}
