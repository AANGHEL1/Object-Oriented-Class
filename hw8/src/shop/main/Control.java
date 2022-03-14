package shop.main;

import shop.ui.*;
import shop.data.Data;
import shop.data.Inventory;
import shop.data.Video;
import shop.data.Record;
import shop.command.Command;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

class Control {
  private static final int EXITED = 0;
  private static final int EXIT = 1;
  private static final int START = 2;
  private static final int NUMSTATES = 10;
  private MenuForm<UIMenuAction>[] _menus;
  private int _state;

  private MenuForm<UIFormTest> _getVideoForm;
  private UIFormTest _stringTest = UIFormTestEnum.StringTest.get();
  private UIFormTest _yearTest = UIFormTestEnum.YearTest.get();
    
  private Inventory _inventory;
  private UI _ui;
  
  Control(Inventory inventory, UI ui) {
    _inventory = inventory;
    _ui = ui;
    _menus = new MenuForm[NUMSTATES];
    _state = START;
    addSTART(START);
    addEXIT(EXIT);
    UIFactory fact = new UIFactory();
    UIMenuFormBuilderInterface<UIFormTest> f = fact.formBuilder();
    f.add("Title", _stringTest);
    f.add("Year", _yearTest);
    f.add("Director", _stringTest);
    _getVideoForm = f.toUI("Enter Video");
    new UIMenuActionEnum.Constructor(_ui, _inventory, _getVideoForm, _state);
  }
  
  void run() {
    try {
      while (_state != EXITED) {
        _ui.processMenu(_menus[_state]);
        _state = UIMenuActionEnum.getState();
      }
    } catch (UIError e) {
      _ui.displayError("UI closed");
    }
  }
  
  private void addSTART(int stateNum) {
	UIFactory fact = new UIFactory();
    UIMenuFormBuilderInterface<UIMenuAction> m = fact.menuBuilder();
    
    m.add("Default",UIMenuActionEnum.Default.get());
    m.add("Add/Remove copies of a video",UIMenuActionEnum.AddOrRemove.get());
    m.add("Check in a video", UIMenuActionEnum.CheckIn.get());
    m.add("Check out a video", UIMenuActionEnum.CheckOut.get());
    m.add("Print the inventory", UIMenuActionEnum.Print.get());
    m.add("Clear the inventory", UIMenuActionEnum.Clear.get());
    m.add("Undo", UIMenuActionEnum.Undo.get());
    m.add("Redo", UIMenuActionEnum.Redo.get());
    m.add("Print top ten all time rentals in order", UIMenuActionEnum.PrintTopTen.get());   
    m.add("Exit",UIMenuActionEnum.Exit.get());
    m.add("Initialize with bogus contents", UIMenuActionEnum.Bogus.get());
 
    _menus[stateNum] = m.toUI("Bob's Video");
  }
  private void addEXIT(int stateNum) {
	  UIFactory fact = new UIFactory();
	  UIMenuFormBuilderInterface<UIMenuAction> m = fact.menuBuilder();
    
    m.add("Default", UIMenuActionEnum.DefaultExit.get());
    m.add("Yes", UIMenuActionEnum.Yes.get());
    m.add("No", UIMenuActionEnum.No.get());
    
    _menus[stateNum] = m.toUI("Are you sure you want to exit?");
  }
}
