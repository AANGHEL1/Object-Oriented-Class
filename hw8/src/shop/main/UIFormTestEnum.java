package shop.main;

import shop.ui.UIFormTest;

enum UIFormTestEnum {
	YearTest(new UIFormTest() {
        public boolean run(String input) {
          try {
            int i = Integer.parseInt(input);
            return i > 1800 && i < 5000;
          } catch (NumberFormatException e) {
            return false;
          }
        }
      }),
	
	NumberTest(new UIFormTest() {
        public boolean run(String input) {
          try {
            Integer.parseInt(input);
            return true;
          } catch (NumberFormatException e) {
            return false;
          }
        }
      }),
	
	StringTest(new UIFormTest() {
        public boolean run(String input) {
          return ! "".equals(input.trim());
        }
      });
	
	
	
	
	private UIFormTest uiFormTest;
	
	UIFormTestEnum(UIFormTest uiFormTest) {
		this.uiFormTest = uiFormTest;
	}
	
	UIFormTest get() {
		return uiFormTest;
	}
}
