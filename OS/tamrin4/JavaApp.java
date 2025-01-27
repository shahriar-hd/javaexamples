package tamrin4;
/* Peterson Algorithm
 * @author Shahriar
 * @date Nov 25, 2024
 */
public class JavaApp {
    public static void main(String[] args) {
        int numProcesses = 5;
        PetersonAlgorithm peterson = new PetersonAlgorithm(numProcesses);
        Thread[] threads = new Thread[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            int processId = i;
            threads[i] = new Thread(() -> {
                while (true) {
                    peterson.enterRegion(processId);
                    System.out.println("Process " + processId + " is in critical region.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    peterson.leaveRegion(processId);
                    System.out.println("Process " + processId + " is in not-critical region.");
                }
            });
            threads[i].start();
        }
    }
}

class PetersonAlgorithm {
    private int N;
    private boolean[] flag;
    private int[] turn;
    public PetersonAlgorithm(int N) {
        this.N = N;
        flag = new boolean[N];
        turn = new int[N];
        for (int i = 0; i < N; i++) {
            flag[i] = false;
            turn[i] = 0;
        }
    }
    public void enterRegion(int process) {
        for (int j = 0; j < N; j++) {
            if (j != process) {
                turn[j] = process;
                while (flag[j] && turn[j] == process);
            }
        }
        flag[process] = true;
    }
    public void leaveRegion(int process) {
        flag[process] = false;
    }
}