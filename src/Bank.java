import java.awt.desktop.SystemEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
  public HashMap<String, Client> clients;

  public Bank() {
    clients = new HashMap<>();
  }

  /**
   * Accumulates the amount of money in all users' accounts grouped by the currency type
   * @return Map of doubles where i-th element contains the sum amount of money stored in bank in (CurrencyType)i currency
   */
  public Map<CurrencyType, Double> GetAllClientsAccounts() {
    Map<CurrencyType, Double> accumulate = new HashMap<>();

    for (var client : clients.values()) {
      for (var account : client.getAllAccounts()) {
        double currentValue = 0;
        if (accumulate.containsKey(account.getCurrencyType()))
          currentValue = accumulate.get(account.getCurrencyType());
        currentValue += account.getValue();
        accumulate.put(account.getCurrencyType(), currentValue);
      }
    }

    return accumulate;
  }

  public void addClient(Client client) {
    if (client == null)
      throw new NullPointerException();

    clients.put(client.getPhoneNumber(), client);
  }

  public void removeClient(Client client) {
    if (client == null)
      throw new NullPointerException();

    removeClient(client.getPhoneNumber());
  }

  public void removeClient(String phoneNumber) {
    if (!clients.containsKey(phoneNumber)) {
      throw new ClientNotFoundException();
    }

    clients.remove(phoneNumber);
  }

  public Client getClient(String phoneNumber) {
    if (!clients.containsKey(phoneNumber)) {
      System.out.println("User not found");
      throw new ClientNotFoundException();
    }

    return clients.get(phoneNumber);
  }
}
