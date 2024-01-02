import java.util.Scanner;
/*
 * Peterson number
 */
public class Peterson {
    public static int fact(int a)
    {
        if (a <= 1)
            return 1;
        else
            return a * fact(a - 1);
    }
    public static void main(String[] args) {
        Scanner Get = new Scanner(System.in);
        int value = 0;
        System.out.print("Enter your numnber : ");
        int n = Get.nextInt(), res = 0;
        int x = n;
        while (x > 0)
        {
            int m = x % 10;
            res += fact(m);
            x /= 10;
        }
        if (res == n)
            System.out.println("True");
        else
            System.out.println("False");
    }
}
