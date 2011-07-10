package suncertify.gui.controller;

import javax.swing.table.DefaultTableModel;

import suncertify.db.DBHelper;

public class MainTableModel extends DefaultTableModel {

    public MainTableModel() {
        super(DBHelper.COLUMNS, 0);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    void setTableContent(String content[][]) {
        setDataVector(content, DBHelper.COLUMNS);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
