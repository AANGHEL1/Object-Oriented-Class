package shop.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import shop.command.Command;
import shop.data.Data;
import shop.data.Inventory;
import shop.data.Record;
import shop.data.Video;
import shop.ui.MenuForm;
import shop.ui.UI;
import shop.ui.UIFactory;
import shop.ui.UIFormTest;
import shop.ui.UIMenuAction;
import shop.ui.UIMenuFormBuilderInterface;

enum UIMenuActionEnum {
	 
	Default (new UIMenuAction() {
		public void run() {
		_ui.displayError("doh!");
		}
	}),
	  AddOrRemove(new UIMenuAction() {
	    public void run() {
	      String[] result1 = _ui.processForm(_getVideoForm);
	      Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);
	
	      UIFactory factory = new UIFactory();
	      UIMenuFormBuilderInterface<UIFormTest> f = factory.formBuilder();
	     
	      f.add("Number of copies to add/remove", _numberTest);
	      String[] result2 = _ui.processForm(f.toUI(""));
	                                         
	      Command c = Data.newAddCmd(_inventory, v, Integer.parseInt(result2[0]));
	      if (! c.run()) {
	        _ui.displayError("Command failed");
	      }
	    }
	  }),
      CheckIn(new UIMenuAction() {
        public void run() {
        	String[] result1 = _ui.processForm(_getVideoForm);
            Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);
                                               
            Command c = Data.newInCmd(_inventory, v);
            if (! c.run()) {
              _ui.displayError("Command failed");
            }
        	
        }
      }),
      CheckOut(new UIMenuAction() {
        public void run() {
          // TODO
        	String[] result1 = _ui.processForm(_getVideoForm);
            Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);
                   
            Command c = Data.newOutCmd(_inventory, v);
            if (! c.run()) {
              _ui.displayError("Command failed");
            }
        	
        }
      }),
      
      Print(new UIMenuAction() {
        public void run() {
          _ui.displayMessage(_inventory.toString());
        }
      }),
      
      Clear(new UIMenuAction() {
        public void run() {
          if (!Data.newClearCmd(_inventory).run()) {
            _ui.displayError("Command failed");
          }
        }
      }),
      
      Undo(new UIMenuAction() {
        public void run() {
          if (!Data.newUndoCmd(_inventory).run()) {
            _ui.displayError("Command failed");
          }
        }
      }),
      
      Redo(new UIMenuAction() {
        public void run() {
          if (!Data.newRedoCmd(_inventory).run()) {
            _ui.displayError("Command failed");
          }
        }
      }),
      
      PrintTopTen(new UIMenuAction() {
        public void run() {
        	Comparator<Record> comparator = new numRentalsComparator<Record>();
        	Iterator<Record> top10 = _inventory.iterator(comparator);
        	List<String> top = new ArrayList<String>();
        	int i =0;
        	while(top10.hasNext()) {
        		top.add(top10.next().toString());
        		i++;
        		if(i == 9)
        			break;
        	}
        	
        	
        	String printTop = "";
        	for(int j = 0;j<top.size();j++) {
        		printTop = printTop + top.get(j)+"\n";
        	}
        	_ui.displayMessage(printTop);
        	
        }
      }),
      
      Exit(new UIMenuAction() {
        public void run() {
          _state = EXIT;
        }
      }),
      
      
      Bogus(new UIMenuAction() {
        public void run() {
          Data.newAddCmd(_inventory, Data.newVideo("a", 2000, "m"), 1).run();
          Data.newAddCmd(_inventory, Data.newVideo("b", 2000, "m"), 2).run();
          Data.newAddCmd(_inventory, Data.newVideo("c", 2000, "m"), 3).run();
          Data.newAddCmd(_inventory, Data.newVideo("d", 2000, "m"), 4).run();
          Data.newAddCmd(_inventory, Data.newVideo("e", 2000, "m"), 5).run();
          Data.newAddCmd(_inventory, Data.newVideo("f", 2000, "m"), 6).run();
          Data.newAddCmd(_inventory, Data.newVideo("g", 2000, "m"), 7).run();
          Data.newAddCmd(_inventory, Data.newVideo("h", 2000, "m"), 8).run();
          Data.newAddCmd(_inventory, Data.newVideo("i", 2000, "m"), 9).run();
          Data.newAddCmd(_inventory, Data.newVideo("j", 2000, "m"), 10).run();
          Data.newAddCmd(_inventory, Data.newVideo("k", 2000, "m"), 11).run();
          Data.newAddCmd(_inventory, Data.newVideo("l", 2000, "m"), 12).run();
          Data.newAddCmd(_inventory, Data.newVideo("m", 2000, "m"), 13).run();
          Data.newAddCmd(_inventory, Data.newVideo("n", 2000, "m"), 14).run();
          Data.newAddCmd(_inventory, Data.newVideo("o", 2000, "m"), 15).run();
          Data.newAddCmd(_inventory, Data.newVideo("p", 2000, "m"), 16).run();
          Data.newAddCmd(_inventory, Data.newVideo("q", 2000, "m"), 17).run();
          Data.newAddCmd(_inventory, Data.newVideo("r", 2000, "m"), 18).run();
          Data.newAddCmd(_inventory, Data.newVideo("s", 2000, "m"), 19).run();
          Data.newAddCmd(_inventory, Data.newVideo("t", 2000, "m"), 20).run();
          Data.newAddCmd(_inventory, Data.newVideo("u", 2000, "m"), 21).run();
          Data.newAddCmd(_inventory, Data.newVideo("v", 2000, "m"), 22).run();
          Data.newAddCmd(_inventory, Data.newVideo("w", 2000, "m"), 23).run();
          Data.newAddCmd(_inventory, Data.newVideo("x", 2000, "m"), 24).run();
          Data.newAddCmd(_inventory, Data.newVideo("y", 2000, "m"), 25).run();
          Data.newAddCmd(_inventory, Data.newVideo("z", 2000, "m"), 26).run();
        }
      }),
      
      DefaultExit(new UIMenuAction() {
    	  public void run() {}
        }),
      
      Yes(new UIMenuAction() {
        public void run() {
          _state = EXITED;
        }
      }),
      
      No(new UIMenuAction() {
        public void run() {
          _state = START;
        }
      })
      
      
	
      
	
	
	;

	
	
	private UIMenuAction uiMenuAction;
	private static UI _ui;
	private static MenuForm<UIFormTest> _getVideoForm;
	private static Inventory _inventory;
	private static UIFormTest _numberTest = UIFormTestEnum.NumberTest.get();
	private static final int EXITED = 0;
	private static final int EXIT = 1;
	private static final int START = 2;
	private static int _state;
	
	
	UIMenuActionEnum(UIMenuAction uiMenuAction) {
		this.uiMenuAction = uiMenuAction;
	}


	UIMenuAction get() {
		return uiMenuAction;
	}
	
	public static int getState() {
		return _state;
	}
	
	
	static class Constructor{
		public Constructor(UI ui, Inventory inventory, MenuForm getVideoForm, int state) {
			_ui = ui;
			_inventory = inventory;
			_getVideoForm = getVideoForm;
			_state = state;
		}
	}
	
	
	
}
