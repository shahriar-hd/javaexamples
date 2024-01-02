import java.*;
/*
 * Automorphic Number
 */
public class Automorphic {
    public static void main(String[] args) {
        Scanner Get = new Scanner(System.in);
        int value = 0;
        System.out.print("Enter your number : ");
        int n = Get.nextInt(),digit;
        int x = n;
        int a = x * x;
        for (digit = 1; x >= 10; digit++)
            x /= 10;
        System.out.println(digit);
        System.out.println(n % (Math.pow(10, digit)));
        System.out.println(a);
        if (n == (a % (Math.pow(10, digit))))
            System.out.println("True");
        else
            System.out.println("False");
    }
}
