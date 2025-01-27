package tamrin6_1;
/* Producer & Consumer Algorithm
 * @author Shahriar
 * @date Nov 25, 2024
 */
public class JavaApp {
    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer(5);
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                pc.enter(i);
                System.out.println("Producer: " + i);
            }
        });
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                int item = pc.remove();
                System.out.println("Consumer: " + item);
            }
        });
        producer.start();
        consumer.start();
    }
}
class ProducerConsumer {
    private int[] buffer;
    private int count;
    private int in;
    private int out;
    public ProducerConsumer(int size) {
        buffer = new int[size];
        count = 0;
        in = 0;
        out = 0;
    }
    public synchronized void enter(int item) {
        while (count == buffer.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        buffer[in] = item;
        in = (in + 1) % buffer.length;
        count++;
        notifyAll();
    }

    public synchronized int remove() {
        while (count == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int item = buffer[out];
        out = (out + 1) % buffer.length;
        count--;
        notifyAll();
        return item;
    }
}
