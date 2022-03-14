package shop.ui;

import java.util.ArrayList;
import java.util.List;

final class UIMenuFormBuilder<T> implements UIMenuFormBuilderInterface<T>{
	
	private final List<Pair<UIFormTest>> _menutest = new ArrayList<Pair<UIFormTest>>();
	private final List<Pair<UIMenuAction>> _menuaction = new ArrayList<Pair<UIMenuAction>>();
	
	
	public void add(String prompt, T t) {
		
		if(t instanceof UIFormTest) {
			_menutest.add(new Pair<UIFormTest>(prompt, (UIFormTest) t));
		}
		if(t instanceof UIMenuAction) {
			_menuaction.add(new Pair<UIMenuAction>(prompt, (UIMenuAction) t));
		}
		
	  }
	
	
	public UIMenuForm toUI(String heading) {
	    	if (_menutest.size() > 0) {
	    	 Pair<UIFormTest>[] array = new Pair[_menutest.size()];
	    	 for (int i = 0; i < _menutest.size(); i++)
	   	      array[i] = _menutest.get(i);
	   	    return new UIMenuForm(heading, array);
	    }
	    else  
	    	if (_menuaction.size() > 0) {
	    	Pair<UIMenuAction>[] array = new Pair[_menuaction.size()];
		    for (int i = 0; i < _menuaction.size(); i++)
		      array[i] = _menuaction.get(i);
		    return new UIMenuForm(heading, array);
	    }
	    	else {
	    		throw new IllegalStateException();
	    	}
	  }
	

}
