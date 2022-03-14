package shop.ui;

public class UIFactory {
  //public UIFactory() {}
  static private UI _UIPopUp = new PopupUI();
  static private UI _UIText = new TextUI();
  
  
  public UI popupUi () {
    return _UIPopUp;
  }
  public UI textUi () {
	return _UIText;
  }
  
  public UIMenuFormBuilderInterface<UIMenuAction> menuBuilder() {
	  return new UIMenuFormBuilder<UIMenuAction>();
  }
  public UIMenuFormBuilderInterface<UIFormTest> formBuilder() {
	  return new UIMenuFormBuilder<UIFormTest>();
  }

}
