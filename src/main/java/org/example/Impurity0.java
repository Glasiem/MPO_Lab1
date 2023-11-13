package org.example;

public class Impurity0 extends Thread{

  @Override
  public void run() {
    super.run();
    int currentPosition = 0;
    for (int j = 0; j < 10; j++) {
      double m = Math.random();

      if (m > Cells0.p) {
        if (currentPosition < Cells0.N - 1) {
          Cells0.crystalCells[currentPosition]--;
          currentPosition++;
          Cells0.crystalCells[currentPosition]++;
        }
      } else {
        if (currentPosition > 0) {
          Cells0.crystalCells[currentPosition]--;
          currentPosition--;
          Cells0.crystalCells[currentPosition]++;
        }
      }
      try {
        Cells0.cyclicBarrier.await();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
