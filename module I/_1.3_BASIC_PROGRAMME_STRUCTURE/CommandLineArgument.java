public class CommandLineArgument {
    public static void main(String[] args) {  //main method is static because jvm can use without creating object of class
        System.out.println("Hello World");
        System.out.println("Number of arguments: " + args.length);
        for (int i = 0; i < args.length; i++) {
            System.out.println("Argument " + i + ": " + args[i]);
        }
        
    }
}
