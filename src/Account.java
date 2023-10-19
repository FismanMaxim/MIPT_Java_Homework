public class Account {
  private final CurrencyType currencyType;
  private double value;


  public Account(CurrencyType currencyType, double value) {
    this.currencyType = currencyType;
    this.value = value;
  }

  public CurrencyType getCurrencyType() {
    return currencyType;
  }

  public double getValue() {
    return value;
  }
}
