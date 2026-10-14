public class Main {
  public static void main(String[] args) {
    Customer customer = new Customer("muhmd", "muhmd@gmail.com");
    Payment p1 = new Payment(customer, 150.0, "egp");
    Payment p2 = new Payment(customer, 200.0);
    p1.process();
    p1.refund();
    System.out.println(p1);

//    try {
//      p2.refund();
//    } catch (IllegalStateException e) {
//      System.out.println("Blocked: " + e.getMessage());
//    }

    p1 = null;
    System.out.println(customer);

  }
}