package shop.ui;

public interface UI {
  public <T> void processMenu(MenuForm<T> menu);
  public <T> String[] processForm(MenuForm<T> form);
  public void displayMessage(String message);
  public void displayError(String message);
}
