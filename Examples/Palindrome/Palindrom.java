import java.util.Scanner;;
/*
 * Palindrome number
 */
public class Palindrome {
    public static void main(String[] args) {
        Scanner Get = new Scanner(System.in);
        int value = 0;
        System.out.print("Enter your number : ");
        int x = Get.nextInt();
        int n = x, m, rev = 0;
        while(n > 0)
        {
            m = n % 10;
            rev = rev * 10 + m;
            n /= 10;
        }
        if (rev == x)
            System.out.println("True");
        else
            System.out.println("False");
    }
}