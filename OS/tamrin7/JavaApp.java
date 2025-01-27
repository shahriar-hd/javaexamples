package tamrin7;
/* Writer & Reader with MSG
 * @author Shahriar
 * @date Nov 25, 2024
 */
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
public class JavaApp {
    private static final int BUFFER_SIZE = 5;
    private static final BlockingQueue<String> buffer = new LinkedBlockingQueue<>(BUFFER_SIZE);
    private static final Semaphore mutex = new Semaphore(1);
    private static final Semaphore writeSemaphore = new Semaphore(1);
    private static final Semaphore readSemaphore = new Semaphore(0);
    private static int readCount = 0;

    public static void main(String[] args) {
        Thread writerThread = new Thread(() -> {
            for (int i = 1; i < 5; i++) {
                String message = "Message " + i;
                writer(message);
            }
        });

        Thread[] readerThreads = new Thread[3];
        for (int i = 0; i < readerThreads.length; i++) {
            final int readerId = i;
            readerThreads[i] = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    String message = reader(readerId);
                    if (message != null) {
                        System.out.println("Reader " + readerId + " read: " + message);
                    }
                }
            });
        }

        writerThread.start();
        for (Thread readerThread : readerThreads) {
            readerThread.start();
        }
    }

    public static void writer(String message) {
        try {
            writeSemaphore.acquire();
            mutex.acquire();
            buffer.put(message);
            System.out.println("Writer wrote: " + message);
            mutex.release();
            readSemaphore.release(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
          writeSemaphore.release();
        }
    }

    public static String reader(int readerId) {
        try {
            readSemaphore.acquire();
            mutex.acquire();
            String message = buffer.poll();
            if (message != null) {
                readCount++;
            }
            if (readCount == 1) {
                writeSemaphore.acquire();
            }
            mutex.release();

            if (message != null) {
                return message;
            } else {
                return null;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            try {
                mutex.acquire();
                readCount--;
                if (readCount == 0) {
                    writeSemaphore.release();
                }
                mutex.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void write_section() {
        System.out.println("Writing...");
    }

    public static void Read_setion() {
        System.out.println("Reading...");
    }
}