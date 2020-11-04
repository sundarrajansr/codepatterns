package io.github.sundarrajansr.concurrent.threading.basicthread;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;

/**
 * author : sundarrajan.srgm@gmail.com
 *
 * <p>Spawn 5 threads with 5 messages , But they co-ordinate if someone is printing. Multiple runs
 * might result in different order, But each message should be printed exactly.
 */
public class SyncPrinter {

  public static void main(String... args) throws Exception {
    Boolean print_monitor = Boolean.FALSE;
    List<String> messageList =
        Stream.of("Hello", "World", "Test", "India", "Developer").collect(Collectors.toList());
    ArrayList<PrinterTask> tasks = new ArrayList<PrinterTask>(5);
    for (String message:messageList)
        tasks.add(new PrinterTask(message,print_monitor));
    for (PrinterTask task:tasks)
        task.start();
    for (PrinterTask task:tasks)
        task.join();
  }
}

class PrinterTask extends Thread {
  private Boolean monitor;
  private String message;
  private static int id;

  static {
    id = 1;
  }

  PrinterTask(String name, Boolean monitor) {
    super("Printer " + String.valueOf(id++));
    this.message = name;
    this.monitor = monitor;
  }

  @Override
  public void run() {
    print();
  }
  
  // Business logic which is supposed to be out-of-sync failure.
  public void print() {
    synchronized (this.monitor) {
      for (int i = 0; i < this.message.length(); i++) {
        System.out.print(message.charAt(i));
        PrinterTask.busy(); // Giving room for other threads to interfere.
      }
      System.out.print("\n");
    }
  }

  public static void busy() {
    try {
      Thread.currentThread().sleep(100);
    } catch (InterruptedException ie) {

    }
  }
}
