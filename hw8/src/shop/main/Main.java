package shop.main;

import shop.ui.UIFactory;
import shop.ui.UI;
import shop.data.Data;

public class Main {
  private Main() {}
  public static void main(String[] args) {
    UI ui;
    
    UIFactory retui = new UIFactory();
    if (Math.random() <= 0.5) {
      ui = retui.textUi();
    } else {
      ui = retui.popupUi();
    }
    Control control = new Control(Data.newInventory(), ui);
    control.run();
  }
}
