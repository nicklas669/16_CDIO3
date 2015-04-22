package dtu.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.dal.IOperatoerDAO;

public class ContentView extends Composite {
	// reference to data layer
	IOperatoerDAO iOperatoerDAO;

	VerticalPanel contentPanel;

	public ContentView() {
	}

	public ContentView(IOperatoerDAO iOperatoerDAO) {
		this.iOperatoerDAO = iOperatoerDAO;
		contentPanel = new VerticalPanel();
		initWidget(this.contentPanel);
	}	



	// Sub views
	public void openWelcomeView() {
		contentPanel.clear();
		WelcomeView welcomeView = new WelcomeView(iOperatoerDAO);
		contentPanel.add(welcomeView);
	}

	public void openAddView() {
		contentPanel.clear();
		AddView addView = new AddView(iOperatoerDAO);
		contentPanel.add(addView);

	}

	public void openBrowseView() {
		contentPanel.clear();
		BrowseView browseView = new BrowseView(iOperatoerDAO);
		contentPanel.add(browseView);
	}

	public void openDeleteView() {
		contentPanel.clear();
		DeleteView deleteView = new DeleteView(iOperatoerDAO);
		contentPanel.add(deleteView);
	}

	public void openEditView() {
		contentPanel.clear();
		EditView editView = new EditView(iOperatoerDAO);
		contentPanel.add(editView);
	}


}
