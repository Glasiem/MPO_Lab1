package org.example;

public class Impurity1 extends Thread{

  @Override
  public void run() {
    super.run();
    int currentPosition = 0;
    for (int j = 0; j < 5; j++) {
      long endTime;
      long startTime = System.currentTimeMillis();
      do {
        double m = Math.random();

        if (m > Cells1.p) {
          if (currentPosition < Cells1.N - 1) {
            synchronized (Cells1.crystalCells) {
              Cells1.crystalCells[currentPosition].getAndDecrement();
              currentPosition++;
              Cells1.crystalCells[currentPosition].getAndIncrement();
            }
          }
        } else {
          if (currentPosition > 0) {
            synchronized (Cells1.crystalCells) {
              Cells1.crystalCells[currentPosition].getAndDecrement();
              currentPosition--;
              Cells1.crystalCells[currentPosition].getAndIncrement();
            }
          }
        }

//     if (m > Cells1.p) {
//       if (currentPosition < Cells1.N - 1) {
//         synchronized (Cells1.crystalCells[currentPosition]) {
//           Cells1.crystalCells[currentPosition].getAndDecrement();
//         }
//         currentPosition++;
//         synchronized (Cells1.crystalCells[currentPosition]) {
//           Cells1.crystalCells[currentPosition].getAndIncrement();
//         }
//       }
//     } else {
//       if (currentPosition > 0) {
//         synchronized (Cells1.crystalCells[currentPosition]) {
//           Cells1.crystalCells[currentPosition].getAndDecrement();
//         }
//         currentPosition--;
//         synchronized (Cells1.crystalCells[currentPosition]) {
//           Cells1.crystalCells[currentPosition].getAndIncrement();
//         }
//       }
//     }

        endTime = System.currentTimeMillis();
      } while(endTime - startTime < 1000);

      try {
        Cells1.cyclicBarrier.await();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
