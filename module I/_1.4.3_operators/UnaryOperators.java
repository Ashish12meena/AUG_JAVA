public class UnaryOperators {
    public static void main(String[] args) {
        int x = 5;
        int y;

        y = ++x;    // increase first, then use
        System.out.println("x = " + x + ", y = " + y);

        x = 5;
        y = x++;    // use first, then increase
        System.out.println("x = " + x + ", y = " + y);

        x = 5;
        y = --x;
        System.out.println("x = " + x + ", y = " + y);

        x = 5;
        y = x--;
        System.out.println("x = " + x + ", y = " + y);

        int num = 8;
        System.out.println(-num);

        boolean passed = true;
        System.out.println(!passed);
    }
}
