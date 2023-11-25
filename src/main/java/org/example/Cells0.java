package org.example;

import java.util.concurrent.CyclicBarrier;
import java.util.Scanner;

public class Cells0 {
  static long threadsStarted;
  static long threadsStopped;
  public static Integer[] crystalCells;
  public static CyclicBarrier cyclicBarrier;
  public static boolean isRunning;
  public static int N; //кількість клітинок
  public static int K; //кількість атомів домішок
  public static double p; //вірогідність переходу праворуч

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Введіть N: ");
    N = scanner.nextInt();
    System.out.print("Введіть K: ");
    K = scanner.nextInt();
    System.out.print("Введіть p: ");
    p = scanner.nextDouble();
    scanner.close();

    crystalCells = new Integer[N];
    crystalCells[0] = K;
    for (int i = 1; i < crystalCells.length; i++) {
      crystalCells[i] = 0;
    }
    cyclicBarrier = new CyclicBarrier(K, Cells0::printData);
    threadsStarted = System.currentTimeMillis();
    for (int i = 0; i < K; i++) {
      new Impurity0().start();
    }
    for (int i = 0; i < 5; i++) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      isRunning = false;
    }
  }

  private static void printData() {
//    threadsStopped = System.currentTimeMillis();
//    System.out.println("Час на операцію: " + (threadsStopped - threadsStarted) + " мілісекунд");
    Integer[] crystalSnapshot = new Integer[N];
    System.arraycopy(crystalCells, 0, crystalSnapshot, 0, N);
    int sum = 0;
    for (Integer integer : crystalSnapshot) {
      sum += integer;
    }
    System.out.println(java.util.Arrays.toString(crystalSnapshot));
    System.out.println("Кількість домішок: " + sum);
    System.out.println();
    isRunning = true;
//    threadsStarted = System.currentTimeMillis();
  }
}
