package suncertify.gui.controller;

import javax.swing.JOptionPane;

import suncertify.db.server.CannotReadRecordsException;
import suncertify.db.server.DataServer;
import suncertify.gui.MainWindow;

/**
 * Abstract class to provide run-mode-independent Controller part for MVC. There
 * are two run-mode-specific implementations of all abstract methods:
 * ClientMainWindowController and StandaloneMainWindowController.
 * 
 * @author Jakub Rozek
 * 
 */
public abstract class MainWindowController {

    private MainWindow window;
    private MainTableModel tableModel;
    protected boolean connected = true;

    public MainWindowController(MainWindow win) {
        window = win;
        win.getMainTable().setModel(getTableModel());
        window.setConnected(true);
    }

    protected abstract DataServer getData();

    public void displayConnectionDialog() {
        // empty implementation for override in ClientMainWindowController.
    }

    public final void showAllRecords() {
        try {
            String[][] allRecords = getData().allRecords();
            getTableModel().setTableContent(allRecords);
        } catch (CannotReadRecordsException e) {
            showExceptionToUser(e);
        }
    }

    public final void showExceptionToUser(Exception e) {
        JOptionPane.showMessageDialog(window,
                e.getLocalizedMessage(), "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public final void setStatus(String status) {
        window.setStatus(status);
    }

    public final void confirmAndClose() {
        int n = JOptionPane.showConfirmDialog(window,
                "Are you sure you want to quit?", "Confirm",
                JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            window.dispose();
        }
    }

    public final synchronized MainTableModel getTableModel() {
        if (tableModel == null) {
            // when initializing, the number of search results is zero
            tableModel = new MainTableModel();
        }
        return tableModel;
    }

    public MainWindow getWindow() {
        return window;
    }
}
