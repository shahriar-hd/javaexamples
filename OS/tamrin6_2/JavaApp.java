package tamrin6_2;
/* Phiosopher Dinner
 * @author Shahriar
 * @date Nov 25, 2024                                                                                                           
 */
public class JavaApp {
    public static void main(String[] args) {
        int N = 5;
        Philosopher philosopher = new Philosopher(N);
        Thread[] t = new Thread[N];

        for (int i = 0; i < N; i++) {
            final int philosopherId = i; // Important: Make i final for lambda
            t[i] = new Thread(() -> {
                while (true) {
                    System.out.println("Philosopher " + philosopherId + " is thinking...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();// Restore interrupt status
                        return;
                    }
                    philosopher.take_forks(philosopherId);
                    System.out.println("Philosopher " + philosopherId + " is eating...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();// Restore interrupt status
                        return;
                    }
                    philosopher.put_forks(philosopherId);
                }
            });
            t[i].start();
        }
    }
}

class Philosopher {
    int N;
    int[] state;
    Object[] S;
    final int thinking = 0;
    final int hungry = 1;
    final int eating = 2;

    Philosopher(int N) {
        this.N = N;
        state = new int[N];
        S = new Object[N];
        for (int i = 0; i < N; i++) {
            state[i] = thinking;
            S[i] = new Object();
        }
    }

    synchronized void take_forks(int i) {
        state[i] = hungry;
        // Always try to acquire the left fork first
        test(left(i));
        if (state[i] != eating) {
            test(right(i)); // Only try for right fork if left is acquired
            if (state[i] != eating) {
                try {
                    S[i].wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }

    synchronized void put_forks(int i) {
        state[i] = thinking;
        test(left(i));
        test(right(i));
    }

    synchronized void test(int i) {
        if (state[i] == hungry && state[left(i)] != eating && state[right(i)] != eating && state[i] != eating) {
            state[i] = eating;
            S[i].notify();
        }
    }

    int left(int i) {
        return (i + N - 1) % N;
    }

    int right(int i) {
        return (i + 1) % N;
    }
}