/* LRU Page Replacement 
 * @author Shahriar
 * @date Jan 1, 2025                                                                                                           
 */
package tamrin9_2;
import java.util.Arrays;

public class JavaApp {
    public static int pageFaults(int[] pages, int capacity) {
        int pageFaultCount = 0;
        int[][] lruMatrix = new int[capacity][capacity];
        int[] frames = new int[capacity];
        Arrays.fill(frames, -1);
        for (int page : pages) {
            boolean pageHit = false;
            int frameIndex = -1;
            for (int i = 0; i < capacity; i++) {
                if (frames[i] == page) {
                    pageHit = true;
                    frameIndex = i;
                    break;
                }
            }
            if (pageHit)
                updateLRUMatrix(lruMatrix, frameIndex, capacity);
            else {
                pageFaultCount++;
                int lruIndex = findLRUIndex(lruMatrix, capacity);
                frames[lruIndex] = page;
                resetLRUMatrixRowAndColumn(lruMatrix, lruIndex, capacity);
                updateLRUMatrix(lruMatrix, lruIndex, capacity);
            }
        }
        return pageFaultCount;
    }

    private static void updateLRUMatrix(int[][] matrix, int index, int capacity) {
        for (int i = 0; i < capacity; i++) {
            if (i != index) {
                matrix[index][i] = 1;
                matrix[i][index] = 0;
            }
        }
    }

    private static void resetLRUMatrixRowAndColumn(int[][] matrix, int index, int capacity) {
        for (int i = 0; i < capacity; i++) {
            matrix[index][i] = 0;
            matrix[i][index] = 0;
        }
    }

    private static int findLRUIndex(int[][] matrix, int capacity) {
        for (int i = 0; i < capacity; i++) {
            boolean isLRU = true;
            for (int j = 0; j < capacity; j++) {
                if (matrix[j][i] == 1) {
                    isLRU = false;
                    break;
                }
            }
            if (isLRU) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] pages = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1};
        int capacity = 8;
        int faults = pageFaults(pages, capacity);
        System.out.println("Page Fault Count LRU: " + faults);
    }
}