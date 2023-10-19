import java.util.Map;
import java.util.prefs.BackingStoreException;

public class Main {
  public static void main(String[] args) {
    Bank bank = new Bank();

    Client client = new Client("James", "Waffle", "6272905721");
    client.CreateAccount(1_000_000, CurrencyType.RUB);
    client.CreateAccount(1_000, CurrencyType.USD);

    Client client1 = new Client("Emily", "Brown", "63294522423");
    client1.CreateAccount(500_000, CurrencyType.USD);

    bank.addClient(client);
    bank.addClient(client1);

    Map<CurrencyType, Double> accumulativeSum = bank.GetAllClientsAccounts();
    for (var key : accumulativeSum.keySet()) {
      System.out.println(key + ": " + accumulativeSum.get(key));
    }
  }
}