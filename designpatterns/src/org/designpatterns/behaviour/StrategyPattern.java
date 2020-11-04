package org.designpatterns.behaviour;


interface SortAlgorithm
{
  public void sort (int[] arr);
}

class InsertionSort implements SortAlgorithm
{
  @Override
  public void sort(int[] arr) 
  {
      for (int i = 1; i < arr.length; i++) 
      {
        int j = i;
        int key = arr[j];
        while (j > 0 && arr[j - 1] > key) 
        {
            arr[j] = arr[j - 1];
            j = j - 1;
        }
        arr[j] = key;
      }  
  }
}

class QuickSort implements SortAlgorithm
{

  @Override
  public void sort(int[] arr) {
    quicksort (arr,0,arr.length-1);  
  }
  
  private static void quicksort (int[] arr, int p, int r)
  {
    if (p < r)
    {
        int q = partition(arr, p, r);
        quicksort(arr, p, q - 1);
        quicksort(arr, q + 1, r);
    }
  }

  private static int partition(int[] arr, int p, int r) 
  {    
      int key = arr[r];
      int i = p - 1;
      for (int j = p; j < r; j++) {
          if (arr[j] <= key) {
              i = i + 1;
              exchange(arr, j, i);
          }
      }
      exchange(arr, i + 1, r);
      return (i + 1);
  }

  private static void exchange(int[] arr, int x, int y)
  {
      int temp = arr[x];
      arr[x] = arr[y];
      arr[y] = temp;
  }
  
}


class SortingMachine
{
  SortAlgorithm algorithm;
  int[] arr;
  
  public SortingMachine (int[] arr, SortAlgorithm algorithm)
  {
    this.arr = arr;
    this.algorithm = algorithm;
  }
  
  public void doSort ()
  {
    algorithm.sort(arr);
  }
  
  public void printResponse ()
  {
    System.out.println ("\nSort result from "+ this);
    for (int x:arr) System.out.print(x+",");
  }
}


public class StrategyPattern {

  public static void main(String[] args) {
   
    int[] arr = {5,12,7,9,3,4,5,-2,0,6,6,7};
    SortingMachine machine1 = new SortingMachine (arr,new InsertionSort());
    SortingMachine machine2 = new SortingMachine (arr,new QuickSort());
    
    machine1.doSort();
    machine2.doSort();
    
    machine1.printResponse();
    machine2.printResponse();
  }

}
