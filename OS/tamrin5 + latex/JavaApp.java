package tamrin5;
/* Peterson Algorithm
 * @author Shahriar
 * @date Nov 25, 2024
 */
public class JavaApp {
    public static void main(String[] args) {
        int numProcesses = 5;
        BakersAlgorithm bakers = new BakersAlgorithm(numProcesses);
        Thread[] threads = new Thread[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            int processId = i;
            threads[i] = new Thread(() -> {
                while (true) {
                    bakers.enterRegion(processId);
                    System.out.println("Process " + processId + " is in critical region.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    bakers.leaveRegion(processId);
                    System.out.println("Process " + processId + " is in non-critical reigon.");
                }
            });
            threads[i].start();
        }
    }
}
class BakersAlgorithm {
    private int N;
    private int[] number;
    private boolean[] choosing;
    public BakersAlgorithm(int N) {
        this.N = N;
        number = new int[N];
        choosing = new boolean[N];
    }
    public void enterRegion(int process) {
        int max = 0;
        choosing[process] = true;
        number[process] = 1 + maxFor(number);
        choosing[process] = false;
        for (int j = 0; j < N; j++) {
            while (choosing[j]);
            while (number[j] != 0 && (number[j] < number[process] || (number[j] == number[process] && j < process)));
        }
    }
    public void leaveRegion(int process) {
        number[process] = 0;
    }
    private int maxFor(int[] array) {
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            max = Math.max(max, array[i]);
        }
        return max;
    }
}
