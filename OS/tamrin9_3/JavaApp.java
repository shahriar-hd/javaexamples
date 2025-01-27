/* MFU Page Replacement 
 * @author Shahriar
 * @date Jan 1, 2025                                                                                                           
 */
package tamrin9_3;
import java.util.HashMap;
import java.util.Map;

public class JavaApp {
    public static int pageFaults(int[] pages, int capacity) {
        int pageFaultCount = 0;
        Map<Integer, Integer> frameUsage = new HashMap<>();
        java.util.Set<Integer> pageSet = new java.util.HashSet<>();
        for (int page : pages) {
            if (pageSet.contains(page)) {
                frameUsage.put(page, frameUsage.get(page) + 1);
            } else {
                pageFaultCount++;
                if (frameUsage.size() == capacity) {
                    int mfuPage = findMFUPage(frameUsage);
                    frameUsage.remove(mfuPage);
                    pageSet.remove(mfuPage);
                }
                frameUsage.put(page, 1);
                pageSet.add(page);
            }
        }
        return pageFaultCount;
    }

    private static int findMFUPage(Map<Integer, Integer> frameUsage) {
        int mfuPage = -1;
        int maxUsage = -1;
        for (Map.Entry<Integer, Integer> entry : frameUsage.entrySet()) {
            if (entry.getValue() > maxUsage) {
                maxUsage = entry.getValue();
                mfuPage = entry.getKey();
            }
        }
        return mfuPage;
    }

    public static void main(String[] args) {
        int[] pages = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1};
        int capacity = 8;
        int faults = pageFaults(pages, capacity);
        System.out.println("Page Fault Count MFU: " + faults);
    }
}