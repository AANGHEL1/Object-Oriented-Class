package shop.ui;

import java.util.ArrayList;
import java.util.List;

public interface UIMenuFormBuilderInterface<T> {
	
	  void add(String prompt, T t);
	  
	  UIMenuForm toUI(String heading);
	  
}
