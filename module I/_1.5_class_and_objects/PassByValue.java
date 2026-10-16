public class PassByValue {
    public static void updateByFive(int a){
        a = a + 5;
        System.out.println("Value of a inside method : " + a);
    }
    public static void main(String[] args) {
        int a = 10;


        updateByFive(a);
        System.out.println("Value of a outside method : " + a);
    

      
    }
}
