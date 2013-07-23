package modbusandserialtests.trash;

import java.util.Random;

/**
 * Создает десять потоков для поиска максимального значения в большой матрице.
 * Каждый поток обследует один из фрагментов матрицы.
 */
public class TenThreads {

    private static class WorkerThread extends Thread {
        int max = Integer.MIN_VALUE;
       
        int[] ourArray;

        public WorkerThread(int[] ourArray) {
            this.ourArray = ourArray;
        }

        // Поиск максимального значения в некотором фрагменте массива
        public void run() {
            for (int i = 0; i < ourArray.length; i++) 
                max = Math.max(max, ourArray[i]);                
        }

        public int getMax() {
            return max;
        }
    }

    public static void main(String[] args) {  
    	int numThreads=50;
        WorkerThread[] threads = new WorkerThread[numThreads];
        int[][] bigMatrix = getBigHairyMatrix(numThreads);
        int max = Integer.MIN_VALUE;
  
        // Выделение каждому потоку фрагмента матрицы, с которым он будет работать
        for (int i=0; i < numThreads; i++) {
            threads[i] = new WorkerThread(bigMatrix[i]);
            threads[i].start();
        }

        // Ожидание завершения работы потока
        try {
            for (int i=0; i < numThreads; i++) {
                threads[i].join();
                max = Math.max(max, threads[i].getMax());
            }
        }
        catch (InterruptedException e) {
            // неудача
        }

        System.out.println("Maximum value was " + max);
    }

	private static int[][] getBigHairyMatrix(int numThreads) {
		// TODO Auto-generated method stub
		int[][] bm= new int[numThreads][numThreads*numThreads*numThreads]; Random rnd = new Random();
		for(int i=0;i<numThreads;i++)
			for(int j=0;j<numThreads*numThreads*numThreads;j++)
				bm[i][j]=rnd.nextInt();
		return bm;
	}
}