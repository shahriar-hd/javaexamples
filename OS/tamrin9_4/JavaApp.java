/* Clock Algorithm Page Replacement 
 * @author Shahriar
 * @date Jan 1, 2025                                                                                                           
 */
package tamrin9_4;
import java.util.ArrayList;
import java.util.List;

public class JavaApp {
    public static int pageFaults(int[] pages, int capacity) {
        int pageFaultCount = 0;
        List<Page> frames = new ArrayList<>(capacity);
        int clockHand = 0;
        for (int i = 0; i < capacity; i++) {
            frames.add(new Page(-1));
        }
        for (int page : pages) {
            boolean pageHit = false;
            for (Page frame : frames) {
                if (frame.number == page) {
                    pageHit = true;
                    frame.referenceBit = 1;
                    break;
                }
            }
            if (!pageHit) {
                pageFaultCount++;
                while (true) {
                    Page currentPage = frames.get(clockHand);
                    if (currentPage.referenceBit == 0) {
                        currentPage.number = page;
                        currentPage.referenceBit = 1;
                        clockHand = (clockHand + 1) % capacity;
                        break;
                    } else {
                        currentPage.referenceBit = 0;
                        clockHand = (clockHand + 1) % capacity;
                    }
                }
            }
        }
        return pageFaultCount;
    }

    private static class Page {
        int number;
        int referenceBit;

        public Page(int number) {
            this.number = number;
            this.referenceBit = 0;
        }
    }

    public static void main(String[] args) {
        int[] pages = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1};
        int capacity = 8;
        int faults = pageFaults(pages, capacity);
        System.out.println("Page Fault Count Clock Algorithm: " + faults);
    }
}