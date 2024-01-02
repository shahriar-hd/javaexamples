import java.*;
import java.util.Scanner;
/*
 * Tech Number
 */
public class TechNumber {
    public static void main(String[] args) {
        Scanner Get = new Scanner(System.in);
        int n, digit, sum;
        do
        {
            System.out.print("Enter your number : ");
            n = Get.nextInt();
            int x = n;
            for(digit = 0; x > 0; digit++)
                x /= 10;
        } while((digit % 2) == 1);
        sum = (int)(n / Math.pow(10, (digit / 2)));
        sum += n % Math.pow(10, (digit / 2));
        if(Math.pow(sum, 2) == n)
            System.out.println("True");
        else
            System.out.println("False");
    }
}
