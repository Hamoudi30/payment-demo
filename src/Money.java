import java.util.Locale;
import java.util.Objects;
import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Money {
  private final BigDecimal amount;
  private final String currency;

  // Public constructor takes a String, never a double
  // (new BigDecimal(0.1) would copy the binary error!).
  public Money(String amount, String currency) {
    this(new BigDecimal(amount), currency);
  }

  private Money(BigDecimal amount, String currency) {
    if (amount.signum() < 0) {
      throw new IllegalArgumentException("Money can not be negative");
    }
    if (currency == null || currency.isBlank()) {
      throw new IllegalArgumentException("Currency is required");
    }
    this.amount = amount.setScale(2, RoundingMode.HALF_EVEN);
    this.currency = currency.toUpperCase();
  }

  // Operations return a NEW Money; this object never changes.
  public Money add(Money other) {
    requireSameCurrency(other);
    return new Money(this.amount.add(other.amount), this.currency);
  }

  public Money subtract(Money other) {
    requireSameCurrency(other);
    return new Money(this.amount.subtract(other.amount), this.currency);
  }

  public boolean isPositive() {
    return amount.signum() > 0;
  }

  private void requireSameCurrency(Money other) {
    if (!currency.equals(other.currency)) {
      throw new IllegalArgumentException("Currency mismatch");
    }
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public String getCurrency() {
    return currency;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Money)) return false;
    Money other = (Money) o;
    return amount.equals(other.amount) && currency.equals(other.currency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, currency);
  }

  @Override
  public String toString() {
    return amount + " " + currency;
  }
}
