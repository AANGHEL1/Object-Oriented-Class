package shop.ui;

final class UIMenuForm<T> implements MenuForm<T> {
	
	  private final String _heading;
	  private final Pair<T>[] _form;

	  UIMenuForm(String heading, Pair<T>[] form) {
	    _heading = heading;
	    _form = form;
	  }
	  
	public int size() {
	    return _form.length;
	  }
	public String getHeading() {
	    return (String)_heading;
	  }
	public String getPrompt(int i) {
	    return (String)_form[i].prompt;
	  }
	public boolean checkInput(int i, String input) {
	    if (null == _form[i])
	      return true;
	    return _form[i].test.run(input);
	  }

	public void runAction(int i) {
		_form[i].action.run();
  }

}
