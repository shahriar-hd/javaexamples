import java.*;
/*
 * ArmStrong Numbers
 */
public class ArmStrong {
    public static void main(String[] args) {
        Scanner Get = new Scanner(System.in);
        int digit, sum;
        System.out.print("Enter your number : ");
        int n = Get.nextInt();
        int x = n;
        for(digit = 0; x > 0; digit++)
            x /= 10; //count number of digit
        x = n;
        for(int i = 1; i <= digit; i++)
        {
            int m = x % 10;
            x /= 10;
            sum += Math.pow(m, digit);
        }
        if(n == sum)
            System.out.println(n + " is Armstrong number.");
        else
            System.out.println(n + " is not Armstrong number.");
    }
}
