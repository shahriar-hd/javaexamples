# Java Algorithm Implementations

This repository contains Java implementations of various algorithms related to operating system concepts, concurrency, and memory management.

## 1. Introduction and Abstract

This project explores fundamental operating system concepts through practical Java implementations. It covers topics such as memory management, process/thread synchronization, and paging algorithms. The implementations aim to provide a clear understanding of how these concepts work in practice. Specifically, the project delves into paging replacement algorithms (FIFO, LRU, MFU, Clock), process synchronization using Peterson's and Bakery algorithms, classic concurrency problems like Producer-Consumer and Dining Philosophers solved with monitors, and inter-process communication using message passing in the Readers-Writers problem. Additionally, it contains two extra examples in C++: one demonstrates serial monitoring with FreeRTOS on an STM32F4 microcontroller, and the other implements a bitmap algorithm for finding holes in memory.

## 2. Clone Repository

To clone this repository, use the following command:

```bash
git clone https://github.com/shahriar-hd/javaexamples.git
```

## 3. Algorithm Descriptions
This repository includes implementations of the following algorithms:

### Tamrin 4: Peterson's Algorithm for N Processes
Peterson's algorithm is a classic solution to the critical section problem for two processes. This implementation extends the algorithm to handle N processes, ensuring mutual exclusion and preventing race conditions.

### Tamrin 5: Bakery Algorithm
The Bakery algorithm is another solution to the critical section problem for N processes. It uses a "ticket" system to grant access to the critical section in a fair and orderly manner.

### Tamrin 6-1: Producer-Consumer with Monitor Class
This implementation demonstrates the producer-consumer problem, a classic concurrency problem where producers generate data and consumers consume it. A monitor class is used to synchronize access to the shared buffer.

### Tamrin 6-2: Dining Philosophers with Monitor Class
The dining philosophers problem is a classic example of resource contention and deadlock. This implementation uses a monitor class to manage access to the shared resources (forks) and prevent deadlock.

### Tamrin 7: Readers-Writers with Message Passing
The readers-writers problem deals with managing access to a shared resource by multiple readers and writers. This implementation uses a message-passing mechanism for synchronization between readers and writers, allowing multiple readers to access the resource concurrently but ensuring exclusive access for writers.

### Extra FreeRTOS (C++): FreeRTOS
This example demonstrates serial monitoring using FreeRTOS on an STM32F4 series microcontroller. It showcases real-time operating system concepts in an embedded environment. Note: This code is written in C++ and not Java.

### Extra CountZero (C++): Bitmap algorithm
This example implements a bitmap algorithm to efficiently find holes (contiguous blocks of zero bits) in memory. This is a useful technique for memory allocation and management. Note: This code is written in C++ and not Java.

### Tamrin 9: Paging Replacement Algorithms
This section implements various paging replacement algorithms used in operating system memory management:

- Tamrin 9-1: FIFO (First-In, First-Out): Replaces the oldest page in memory.
- Tamrin 9-2: LRU (Least Recently Used): Replaces the page that has not been used for the longest time.
- Tamrin 9-3: MFU (Most Frequently Used): Replaces the page that has been used most frequently.
- Tamrin 9-4: Clock: A more efficient approximation of LRU that uses a circular buffer and a reference bit.

## 4. Running the Java Implementations
Navigate to the project directory:

```bash
cd OS
```
Compile the Java code:

```bash
javac tamrin*/*.java # If your java files are in a src directory
```
## 5. License and Contact
This project is licensed under the [MIT License](https://github.com/shahriar-hd/javaexamples/blob/main/LICENSE).</br>
For any questions or inquiries, please feel free to contact me at [shahriar.hd@outlook.com](mailto:shahriar.hd@outlook.com).
