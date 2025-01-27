/* count Consecutive Zeros
 * @author Shahriar
 * @date Jan 1, 2025                                                                                                           
 */
#include <iostream>
#include <vector>
using namespace std;

vector<int> countZeros(uint8_t* arr, int len) {
    vector<int> counts;
    if (arr == nullptr || len <= 0) {
        return counts;
    }
    int currentCount = 0;
    for (int i = 0; i < len; ++i) {
        uint8_t byte = arr[i];
        for (int j = 7; j >= 0; --j) {
            if ((byte & (1 << j)) == 0)
                currentCount++;
            else if (currentCount > 0) {
                counts.push_back(currentCount);
                currentCount = 0;
            }
        }
    }
    if (currentCount > 0) {
        counts.push_back(currentCount);
    }
    return counts;
}

int main() {
    uint8_t arr[5];
    arr[0] = 0b10011100;
    arr[1] = 0b10000111;
    arr[2] = 0b01000110;
    arr[3] = 0b00000001;
    arr[4] = 0b11111011;
    vector<int> zeroCounts = countZeros(arr, 5);
    cout << "Zeros: ";
    for (int count : zeroCounts) {
        cout << count << ", ";
    }
    cout << endl;
    return 0;
}