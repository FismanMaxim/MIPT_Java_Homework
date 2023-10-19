import java.util.ArrayList;
import java.util.List;

public class Client {
  private String name;
  private String surname;
  private String phoneNumber;
  private List<Account> _accounts;


  public Client(String name, String surname, String phoneNumber) {
    this.name = name;
    this.surname = surname;
    this.phoneNumber = phoneNumber;
    _accounts = new ArrayList<>();
  }

  public void CreateAccount(double sum, CurrencyType currencyType) {
    _accounts.add(new Account(currencyType, sum));
  }

  public List<Account> getAllAccounts() {
    return _accounts;
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }
}
