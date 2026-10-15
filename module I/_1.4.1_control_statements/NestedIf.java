public class NestedIf {
    public static void main(String[] args) {
        int a = 15;
        int b = 40;
        int c = 25;

        if (a > b) {
            if (a > c) {
                System.out.println("Greatest number is " + a);
            } else {
                System.out.println("Greatest number is " + c);
            }
        } else {
            if (b > c) {
                System.out.println("Greatest number is " + b);
            } else {
                System.out.println("Greatest number is " + c);
            }
        }
    }
}
