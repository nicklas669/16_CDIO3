package dtu.client.ui;

import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.dal.IOperatoerDAO;
import dtu.client.dal.OperatoerDTO;


public class BrowseView extends Composite {
	VerticalPanel browsePanel;
	
	// reference to data layer
	IOperatoerDAO iOperatoerDAO;
	
	public BrowseView(IOperatoerDAO iOperatoerDAO) {
		this.iOperatoerDAO = iOperatoerDAO;

		browsePanel = new VerticalPanel();
		initWidget(this.browsePanel);

		FlexTable t = new FlexTable();
		t.getFlexCellFormatter().setWidth(0, 0, "50px");
		t.getFlexCellFormatter().setWidth(0, 1, "170px");
		t.getFlexCellFormatter().setWidth(0, 2, "15px");
		t.getFlexCellFormatter().setWidth(0, 3, "100px");
		t.getFlexCellFormatter().setWidth(0, 4, "70px");
		
		t.addStyleName("FlexTable");
		t.getRowFormatter().addStyleName(0,"FlexTable-Header");

		t.setText(0, 0, "ID");
		t.setText(0, 1, "Navn");
		t.setText(0, 2, "Initialer");
		t.setText(0, 3, "CPR");
		t.setText(0, 4, "Password");

		List<OperatoerDTO> operatoerer = iOperatoerDAO.getOperators();

		for (int i=0; i < operatoerer.size(); i++) {
			t.setText(i+1, 0, ""+operatoerer.get(i).getOprId());
			t.setText(i+1, 1, operatoerer.get(i).getOprNavn());
			t.setText(i+1, 2, operatoerer.get(i).getIni());
			t.setText(i+1, 3, operatoerer.get(i).getCpr());
			t.setText(i+1, 4, operatoerer.get(i).getPassword());
		}

		browsePanel.add(t);
	}
}
