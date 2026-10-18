public class StringClass {
    public static void main(String[] args) {
        String str1 = "Hello";
        String str2 = "World";
        String str3 = str1 + " " + str2; // Concatenation
        System.out.println(str3);

        // String s = new String("Hello World");
        String s = new String("Hello World");
        
        System.out.println(s);


        boolean isEqual = str3.equals(s); // Comparison
        System.out.println("Are the strings equal? " + isEqual);

        boolean isEquals = str3==s; // Comparison
        System.out.println("Are the strings equal? " + isEquals);


    }
}
