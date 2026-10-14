import java.time.LocalDateTime;
import java.util.UUID;

public class Payment {

  // attributes (state)
  private final String id;
  private String currency;
  private double amount;
  private PaymentStatus status;
  private final LocalDateTime createdAt;
  private final Customer customer;     // association: a reference to an independent object  -- means Payment can't point to a different customer

  // constructors
  public Payment(Customer customer, double amount, String currency) {
    if (customer == null) {
      throw new IllegalArgumentException("Customer is required");
    }
    this.id = UUID.randomUUID().toString();
    this.customer = customer;
    setAmount(amount);
    setCurrency(currency);
    this.status = PaymentStatus.PENDING;
    this.createdAt = LocalDateTime.now();
  }

  public Payment(Customer customer, double amount) {
    this(customer, amount, "USD");
  }

  // public interfaces
  public void process() {
    requireStatus(PaymentStatus.PENDING);
    this.status = PaymentStatus.COMPLETED;
  }

  public void markFailed() {
    requireStatus(PaymentStatus.PENDING);
    this.status = PaymentStatus.FAILED;
  }

  public void refund() {
    requireStatus(PaymentStatus.COMPLETED);
    this.status = PaymentStatus.REFUNDED;
  }


  // accessors
  public String getId() {
    return id;
  }

  public String getCustomerName() {
    return customer.getName();
  }

  public String getCurrency() {
    return currency;
  }

  public double getAmount() {
    return amount;
  }

  public PaymentStatus getStatus() {
    return status;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  // mutators (should validate data before set it)
  public void setAmount(double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount must be greater than zero");
    }
    this.amount = amount;
  }

  public void setCurrency(String currency) {
    if (currency == null || currency.isBlank()) {
      throw new IllegalArgumentException("Currency is required");
    }
    this.currency = currency;
  }

  // private implementation
  private void requireStatus(PaymentStatus expected) {
    if (status != expected) {
      throw new IllegalArgumentException("Expected status " + expected + " but was " + status);
    }
  }

  // object overrides
  @Override
  public String toString() {
    return "Payment{id=" + id + ", customer=" + customer.getName()
        + ", amount=" + amount + " " + currency + ", status=" + status + "}";
  }
}