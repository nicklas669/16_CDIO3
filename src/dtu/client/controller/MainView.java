package dtu.client.controller;

import com.google.gwt.user.client.ui.RootPanel;

import dtu.client.dal.IOperatoerDAO;
import dtu.client.dal.OperatoerDAO;
import dtu.client.ui.ContentView;
import dtu.client.ui.MenuView;


public class MainView  {
	
	// reference to ContentView
	private ContentView contentView;
	
	// reference to data layer
	private IOperatoerDAO iOperatoerDAO;
	
	public MainView() {
		
		// add implementation of data layer
		iOperatoerDAO = new OperatoerDAO();
		
		// wrap menuView
		MenuView m = new MenuView(this);
		RootPanel.get("nav").add(m);
		
		// wrap contentView
		contentView = new ContentView(iOperatoerDAO);
		RootPanel.get("section").add(contentView);
	
	
	}
	
	public void run() {
		// show welcome panel
		contentView.openWelcomeView();
	}
	
	
	// Call back handlers
	public void addOperator() {
		contentView.openAddView();
	}
	
	public void showOperators() {
		contentView.openBrowseView();
	}
	
	public void editOperators() {
		contentView.openEditView();
	}
	
	public void deleteOperators() {
		contentView.openDeleteView();
	}
	
}
