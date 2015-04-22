package dtu.client.ui;


import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.dal.IOperatoerDAO;

public class WelcomeView extends Composite {

	public WelcomeView(IOperatoerDAO iOperatoerDAO) {
		
	VerticalPanel w = new VerticalPanel();
	initWidget(w);
		w.add(new Label("Velkommen til operatørkartotek"));
		w.add(new Label("Antal operatører i kartotek = " + iOperatoerDAO.getSize()));
	}
	
	public void show() {
		
		
	}
}
