package dtu.client.ui;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.dal.IOperatoerDAO;
import dtu.client.dal.OperatoerDTO;

public class DeleteView extends Composite {
	VerticalPanel deletePanel;
	FlexTable t;

	// reference to data layer
	IOperatoerDAO iOperatoerDAO;

	// previous cancel anchor
	Anchor previousCancel = null;

	public DeleteView(IOperatoerDAO iOperatoerDAO) {
		this.iOperatoerDAO = iOperatoerDAO;

		deletePanel = new VerticalPanel();
		initWidget(this.deletePanel);

		t = new FlexTable();
		t.getFlexCellFormatter().setWidth(0, 0, "50px");
		t.getFlexCellFormatter().setWidth(0, 1, "150px");
		t.getFlexCellFormatter().setWidth(0, 2, "15px");
		t.getFlexCellFormatter().setWidth(0, 3, "150px");
		t.getFlexCellFormatter().setWidth(0, 4, "100px");

		t.addStyleName("FlexTable");
		t.getRowFormatter().addStyleName(0,"FlexTable-Header");

		// set headers in flextable
		t.setText(0, 0, "ID");
		t.setText(0, 1, "Navn");
		t.setText(0, 2, "Initialer");
		t.setText(0, 3, "CPR");
		t.setText(0, 4, "Password");

		// fetch operators from data layer
		List<OperatoerDTO> operatoerer = iOperatoerDAO.getOperators();

		// populate table and add delete anchor to each row
		for (int rowIndex=0; rowIndex < operatoerer.size(); rowIndex++) {
			t.setText(rowIndex+1, 0, ""+operatoerer.get(rowIndex).getOprId());
			t.setText(rowIndex+1, 1, operatoerer.get(rowIndex).getOprNavn());
			t.setText(rowIndex+1, 2, operatoerer.get(rowIndex).getIni());
			t.setText(rowIndex+1, 3, operatoerer.get(rowIndex).getCpr());
			t.setText(rowIndex+1, 4, operatoerer.get(rowIndex).getPassword());
			Anchor delete = new Anchor("delete");
			t.setWidget(rowIndex+1, 5, delete);
			delete.addClickHandler(new DeleteHandler());
		}
		deletePanel.add(t);
	}

	private class DeleteHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			
			// if previous cancel open - force cancel operation�
			if (previousCancel != null)
				previousCancel.fireEvent(new ClickEvent(){});


			// get rowindex where event happened
			final int eventRowIndex = t.getCellForEvent(event).getRowIndex();

			// get delete anchor ref for cancel operation
			final Anchor delete =  (Anchor) event.getSource();

			Anchor ok = new Anchor("ok");
			ok.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					// remove row in flextable
					t.removeRow(eventRowIndex);

					// here you will normally fetch the primary key of the row 
					// and use it for location the object to be deleted
					iOperatoerDAO.deleteOperator(eventRowIndex-1);
					
				}

			});

			Anchor cancel = new Anchor("cancel");
			previousCancel = cancel;
			cancel.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					t.setWidget(eventRowIndex, 5, delete);
					t.clearCell(eventRowIndex, 6);
				}

			});

			// showing ok and cancel widgets
			t.setWidget(eventRowIndex, 5 , ok);
			t.setWidget(eventRowIndex, 6 , cancel);
		}
	}
}


