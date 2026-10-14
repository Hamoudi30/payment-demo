import java.util.UUID;

public class Customer {

  private final String id;
  private String name;
  private String email;

  public Customer (String name, String email) {
    this.id = UUID.randomUUID().toString();
    setName(name);
    setEmail(email);
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public void setName(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Name is required");
    }
    this.name = name;
  }

  public void setEmail(String email) {
    if (email == null || !email.contains("@")) {
      throw new IllegalArgumentException("Email is required");
    }
    this.email = email;
  }

  @Override
  public String toString() {
    return "Customer{" + name + ", " + email + "}";
  }

}
