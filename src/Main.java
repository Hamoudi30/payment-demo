public class Main {
  public static void main(String[] args) {
    Customer customer = new Customer("muhmd", "muhmd@gmail.com");
    Payment p1 = new Payment(customer, 150.0, "egp");
    Payment p2 = new Payment(customer, 200.0);
    Money m1 = new Money("10.11634010", "usd");
    System.out.println(m1.getAmount());
    System.out.println(m1.getCurrency());
    Money m2 = new Money("20.223", "usD");
    System.out.println(m1.add(m2));
  }
}