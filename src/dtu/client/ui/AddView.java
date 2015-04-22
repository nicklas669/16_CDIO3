package dtu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.dal.IOperatoerDAO;
import dtu.client.dal.OperatoerDTO;
import dtu.shared.FieldVerifier;

public class AddView extends Composite {
	VerticalPanel addPanel;

	// reference to data layer
	IOperatoerDAO iOperatoerDAO;

	// controls
	Label idLbl;
	Label nameLbl;
	Label iniLbl;
	Label cprLbl;
	Label passLbl;
	
	TextBox idTxt;
	TextBox nameTxt;
	TextBox iniTxt;
	TextBox cprTxt;
	TextBox passTxt;
//	Button save = new Button("Tilf\u00F8j");
	Button save;

	// valid fields
	boolean ageValid = false;
	boolean nameValid = false;

	public AddView(final IOperatoerDAO iOperatoerDAO) {
		this.iOperatoerDAO = iOperatoerDAO;
		addPanel = new VerticalPanel();

		// total height of widget. Components are distributed evenly
		addPanel.setHeight("300px");	
		initWidget(this.addPanel);

		HorizontalPanel idPanel = new HorizontalPanel();
		HorizontalPanel namePanel = new HorizontalPanel();
		HorizontalPanel iniPanel = new HorizontalPanel();
		HorizontalPanel cprPanel = new HorizontalPanel();
		HorizontalPanel passPanel = new HorizontalPanel();

		idLbl = new Label("ID:");
		idLbl.setWidth("70px");
		idTxt = new TextBox();
		idTxt.setHeight("1em");
		idPanel.add(idLbl);
		idPanel.add(idTxt);
		
		nameLbl = new Label("Navn:");
		nameLbl.setWidth("70px");
		nameTxt = new TextBox();
		nameTxt.setHeight("1em");
		namePanel.add(nameLbl);
		namePanel.add(nameTxt);

		iniLbl = new Label("Initialer:");
		iniLbl.setWidth("70px");
		iniTxt = new TextBox();
//		iniTxt.setWidth("5em");
		iniTxt.setHeight("1em");
		iniPanel.add(iniLbl);
		iniPanel.add(iniTxt);
		
		cprLbl = new Label("CPR:");
		cprLbl.setWidth("70px");
		cprTxt = new TextBox();
//		cprTxt.setWidth("5em");
		cprTxt.setHeight("1em");
		cprPanel.add(cprLbl);
		cprPanel.add(cprTxt);
		
		passLbl = new Label("Password:");
		passLbl.setWidth("70px");
		passTxt = new TextBox();
//		passTxt.setWidth("5em");
		passTxt.setHeight("1em");
		passPanel.add(passLbl);
		passPanel.add(passTxt);

		// use unicode escape sequence \u00F8 for '�'
		save = new Button("Tilf\u00F8j");
		save.setEnabled(false);

		save.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				iOperatoerDAO.createOperator(new OperatoerDTO(Integer.valueOf(idTxt.getText()), nameTxt.getText(), iniTxt.getText(), cprTxt.getText(), passTxt.getText()));
				Window.alert("Operatør "+nameTxt.getText()+" gemt i kartotek.");
			}
		});


		// register event handlers
		nameTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidName(nameTxt.getText())) {
					nameTxt.setStyleName("gwt-TextBox-invalidEntry");
					nameValid = false;
				}
				else {
					nameTxt.removeStyleName("gwt-TextBox-invalidEntry");
					nameValid = true;
				}

				checkFormValid();
			}

		});

		iniTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidInitials(iniTxt.getText())) {
					iniTxt.setStyleName("gwt-TextBox-invalidEntry");
					ageValid = false;
				}
				else {
					iniTxt.removeStyleName("gwt-TextBox-invalidEntry");
					ageValid = true;
				}
				checkFormValid();
			}

		});
		
		addPanel.add(idPanel);
		addPanel.add(namePanel);
		addPanel.add(iniPanel);
		addPanel.add(cprPanel);
		addPanel.add(passPanel);
		addPanel.add(save);


	}

	private void checkFormValid() {
		if (ageValid && nameValid)
			save.setEnabled(true);
		else
			save.setEnabled(false);

	}

}
