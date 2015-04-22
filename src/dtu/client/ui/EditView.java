package dtu.client.ui;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.dal.IOperatoerDAO;
import dtu.client.dal.OperatoerDTO;
import dtu.shared.FieldVerifier;


public class EditView extends Composite {
	VerticalPanel editPanel;
	FlexTable t;
	

	// editing text boxes
	TextBox idTxt;
	TextBox nameTxt;
	TextBox iniTxt;
	TextBox cprTxt;
	TextBox passTxt;

	// valid fields - initially a field is valid
	boolean nameValid = true;
	boolean ageValid = true;

	int eventRowIndex;

	// reference to data layer
	IOperatoerDAO iOperatoerDAO;
	// person list
	List<OperatoerDTO> operatoerer;

	// previous cancel anchor
	Anchor previousCancel = null;

	public EditView(IOperatoerDAO iOperatoerDAO) {
		this.iOperatoerDAO = iOperatoerDAO;

		editPanel = new VerticalPanel();
		initWidget(this.editPanel);

		t = new FlexTable();

		// adjust column widths
		t.getFlexCellFormatter().setWidth(0, 0, "50px");
		t.getFlexCellFormatter().setWidth(0, 1, "150px");
		t.getFlexCellFormatter().setWidth(0, 2, "15px");
		t.getFlexCellFormatter().setWidth(0, 3, "150px");
		t.getFlexCellFormatter().setWidth(0, 4, "100px");

		// style table
		t.addStyleName("FlexTable");
		t.getRowFormatter().addStyleName(0,"FlexTable-Header");

		// set headers in flextable
		t.setText(0, 0, "ID");
		t.setText(0, 1, "Navn");
		t.setText(0, 2, "Initialer");
		t.setText(0, 3, "CPR");
		t.setText(0, 4, "Password");

		// fetch persons from data layer
		operatoerer = iOperatoerDAO.getOperators();

		// populate table and add edit anchor to each row
		for (int rowIndex=0; rowIndex < operatoerer.size(); rowIndex++) {
			t.setText(rowIndex+1, 0, ""+operatoerer.get(rowIndex).getOprId());
			t.setText(rowIndex+1, 1, operatoerer.get(rowIndex).getOprNavn());
			t.setText(rowIndex+1, 2, operatoerer.get(rowIndex).getIni());
			t.setText(rowIndex+1, 3, operatoerer.get(rowIndex).getCpr());
			t.setText(rowIndex+1, 4, operatoerer.get(rowIndex).getPassword());
			Anchor edit = new Anchor("edit");
			t.setWidget(rowIndex+1, 5, edit);

			edit.addClickHandler(new EditHandler());
		}

		editPanel.add(t);
		
		// text boxes
		idTxt = new TextBox();
		nameTxt = new TextBox();
		iniTxt = new TextBox();
		cprTxt = new TextBox();
		passTxt = new TextBox();
	}

	private class EditHandler implements ClickHandler {
		public void onClick(ClickEvent event) {

			// if previous edit open - force cancel operation�
			if (previousCancel != null)
				previousCancel.fireEvent(new ClickEvent(){});

			// get rowindex where event happened
			eventRowIndex = t.getCellForEvent(event).getRowIndex();

			// populate textboxes
			idTxt.setText(t.getText(eventRowIndex, 0));
			nameTxt.setText(t.getText(eventRowIndex, 1));
			iniTxt.setText(t.getText(eventRowIndex, 2));
			cprTxt.setText(t.getText(eventRowIndex, 3));
			passTxt.setText(t.getText(eventRowIndex, 4));

			// show text boxes for editing
			t.setWidget(eventRowIndex, 0, idTxt);
			t.setWidget(eventRowIndex, 1, nameTxt);
			t.setWidget(eventRowIndex, 2, iniTxt);
			t.setWidget(eventRowIndex, 3, cprTxt);
			t.setWidget(eventRowIndex, 4, passTxt);

			// start editing here
			nameTxt.setFocus(true);

			// get edit anchor ref for cancel operation
			final Anchor edit =  (Anchor) event.getSource();

			// get textbox contents for cancel operation
			final String id = idTxt.getText();
			final String name = nameTxt.getText();
			final String ini = iniTxt.getText();
			final String cpr = cprTxt.getText();
			final String pass = passTxt.getText();


			final Anchor ok = new Anchor("ok");
			ok.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					// remove inputboxes
					t.setText(eventRowIndex, 0, idTxt.getText());
					t.setText(eventRowIndex, 1, nameTxt.getText());
					t.setText(eventRowIndex, 2, iniTxt.getText());
					t.setText(eventRowIndex, 3, cprTxt.getText());
					t.setText(eventRowIndex, 4, passTxt.getText());

					// here you will normally fetch the primary key of the row 
					// and use it for location the object to be edited

					// fill DTO
					OperatoerDTO operatoerDTO = new OperatoerDTO(Integer.valueOf(t.getText(eventRowIndex, 0)), 
							t.getText(eventRowIndex, 1), t.getText(eventRowIndex, 2), t.getText(eventRowIndex, 3), t.getText(eventRowIndex, 4));

					// save in backend
					iOperatoerDAO.updateOperator(operatoerDTO, eventRowIndex-1);		

					// restore edit link
					t.setWidget(eventRowIndex, 5, edit);
					t.clearCell(eventRowIndex, 6);
					
					previousCancel = null;

				}

			});

			Anchor cancel = new Anchor("cancel");
			previousCancel = cancel;
			cancel.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					
					// restore original content of textboxes and rerun input validation
					idTxt.setText(id);
					idTxt.fireEvent(new KeyUpEvent() {}); // validation
					
					nameTxt.setText(name);
					nameTxt.fireEvent(new KeyUpEvent() {}); // validation
					
					iniTxt.setText(ini);
					iniTxt.fireEvent(new KeyUpEvent() {});  // validation
					
					cprTxt.setText(cpr);
					cprTxt.fireEvent(new KeyUpEvent() {}); // validation
					
					passTxt.setText(pass);
					passTxt.fireEvent(new KeyUpEvent() {}); // validation
					
					t.setText(eventRowIndex, 0, id);
					t.setText(eventRowIndex, 1, name);
					t.setText(eventRowIndex, 2, ini);
					t.setText(eventRowIndex, 3, cpr);
					t.setText(eventRowIndex, 4, pass);
					
					// restore edit link
					t.setWidget(eventRowIndex, 5, edit);
					t.clearCell(eventRowIndex, 6);
					
					previousCancel = null;
				}

			});


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

					// enable/disable ok depending on form status 
					if (nameValid&&ageValid)
						t.setWidget(eventRowIndex, 5, ok);
					else
						t.setText(eventRowIndex, 5, "ok");				
				}

			});

			iniTxt.addKeyUpHandler(new KeyUpHandler(){

				@Override
				public void onKeyUp(KeyUpEvent event) {
					if (!FieldVerifier.isValidAge(iniTxt.getText())) {
						iniTxt.setStyleName("gwt-TextBox-invalidEntry");
						ageValid = false;
					}
					else {
						iniTxt.removeStyleName("gwt-TextBox-invalidEntry");
						ageValid = true;
					}

					// enable/disable ok depending on form status 
					if (nameValid&&ageValid)
						t.setWidget(eventRowIndex, 5, ok);
					else
						t.setText(eventRowIndex, 5, "ok");
				}

			});

			// showing ok and cancel widgets
			t.setWidget(eventRowIndex, 5 , ok);
			t.setWidget(eventRowIndex, 6 , cancel);		
		}
	}
}
