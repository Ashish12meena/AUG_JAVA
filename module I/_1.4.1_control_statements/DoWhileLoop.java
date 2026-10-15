public class DoWhileLoop {
    public static void main(String[] args) {
        int i = 1;

        do {
            System.out.println(i);
            i++;
        } while (i <= 5);

        // condition is false, but the block still runs once
        int j = 10;
        do {
            System.out.println("j = " + j);
        } while (j < 5);
    }
}
