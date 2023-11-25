package org.example;

import java.util.concurrent.CyclicBarrier;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Cells1 {
  static long threadsStarted;
  static long threadsStopped;
  public static AtomicInteger[] crystalCells;
  public static CyclicBarrier cyclicBarrier;
  public static int N; //кількість клітинок
  public static int K; //кількість атомів домішок
  public static double p; //вірогідність переходу ліворуч

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Введіть N: ");
    N = scanner.nextInt();
    System.out.print("Введіть K: ");
    K = scanner.nextInt();
    System.out.print("Введіть p: ");
    p = scanner.nextDouble();
    scanner.close();

    crystalCells = new AtomicInteger[N];
    crystalCells[0] = new AtomicInteger(K);
    for (int i = 1; i < crystalCells.length; i++) {
      crystalCells[i] = new AtomicInteger(0);
    }
    cyclicBarrier = new CyclicBarrier(K, Cells1::printData);
//    threadsStarted = System.currentTimeMillis();
    for (int i = 0; i < K; i++) {
      new Impurity1().start();
    }
  }

  private static void printData() {
//    threadsStopped = System.currentTimeMillis();
//    System.out.println("Час на операцію: " + (threadsStopped - threadsStarted) + " мілісекунд");
    AtomicInteger[] crystalSnapshot = new AtomicInteger[N];
    for (int i = 0; i < N; i++) {
      crystalSnapshot[i] = new AtomicInteger();
      crystalSnapshot[i].set(crystalCells[i].get());
    }
    AtomicInteger sum = new AtomicInteger(0);
    for (AtomicInteger atomicInteger : crystalSnapshot) {
      sum.getAndAdd(atomicInteger.get());
    }
    System.out.println(java.util.Arrays.toString(crystalSnapshot));
    System.out.println("Кількість домішок: " + sum);
    System.out.println();
//    threadsStarted = System.currentTimeMillis();
  }
}
