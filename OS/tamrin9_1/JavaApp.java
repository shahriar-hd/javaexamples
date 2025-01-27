/* FIFO Page Replacement 
 * @author Shahriar
 * @date Jan 1, 2025                                                                                                           
 */
package tamrin9_1;
import java.util.LinkedList;
import java.util.Queue;

public class JavaApp {
    public static int pageFaults(int[] pages, int capacity) {
        Queue<Integer> memory = new LinkedList<>();
        java.util.Set<Integer> pageSet = new java.util.HashSet<>();
        int pageFaultCount = 0;
        for (int page : pages) {
            if (!pageSet.contains(page)) {
                pageFaultCount++;
                if (memory.size() == capacity) {
                    int oldestPage = memory.poll();
                    pageSet.remove(oldestPage);
                }
                memory.offer(page);
                pageSet.add(page);
            }
        }
        return pageFaultCount;
    }

    public static void main(String[] args) {
        int[] pages = {1, 3, 0, 3, 5, 6, 3, 7, 8, 2, 1, 4, 9, 1};
        int capacity = 5;
        int faults = pageFaults(pages, capacity);
        System.out.println("Page Fault Count: " + faults);
    }
}