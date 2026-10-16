public class PassByValyByObject {
    public static void updateArray(int a[]){
        a[0] = 100;
    }
    public static void main(String[] args) {
        int marks[] =  {90, 80, 70, 60, 50};


        updateArray(marks);
        System.out.println("Value of a outside method : " + marks[0]);
    

      
    }
 
}
