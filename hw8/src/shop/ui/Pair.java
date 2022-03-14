package shop.ui;

@SuppressWarnings("hiding")
final class Pair <T> {

	    final String prompt;
	    UIMenuAction action;
	    UIFormTest test;

	    Pair(String thePrompt, T t) {
	      this.prompt = thePrompt;
	      //t = null;
	      
	      if (t instanceof UIFormTest) {
	    	  this.test = (UIFormTest) t;
	      }
	      else if (t instanceof UIMenuAction) {
	    	  this.action = (UIMenuAction) t;
	      }
	      else {
	    	  throw new IllegalArgumentException();
	      }
	    }

}
