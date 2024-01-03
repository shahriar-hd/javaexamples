import java.*;
//Fibonancci series :
public class Fibonancci {
    public static void main(String[] args) {
        Scanner Get = new Scanner(System.in);
        //Fibonancci series :
        System.out.print("Enter n : ");
        int n = Get.nextInt(), a = 1, b = 1, c = a + b;
        if(n <= 0)
            System.out.println("-1");
        else
        {
            if(n >= 1)
                System.out.print("1\t");
            if(n >= 2)
                System.out.print("1\t");
            for(int i = 3; i <= n; i++)
            {
                System.out.print(c + "\t");
                a = b;
                b = c;
                c = a + b;
            }
        }
        //Fibonancci n number :
        int fibo = (int)((1 / Math.sqrt(5)) * (Math.pow(((1 + Math.sqrt(5)) / 2), n) - Math.pow(((1 - Math.sqrt(5)) / 2), n)));
        System.out.println("\nFinonancci n : " + fibo);
    }
}
