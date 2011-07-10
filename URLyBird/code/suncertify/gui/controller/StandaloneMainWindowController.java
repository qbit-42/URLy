package suncertify.gui.controller;

import suncertify.db.server.DataServerLocal;
import suncertify.gui.MainWindow;

public class StandaloneMainWindowController extends
        MainWindowController {

    public StandaloneMainWindowController(MainWindow window) {
        super(window);
    }

    private DataServerLocal server = DataServerLocal
            .getInstance();

    @Override
    protected DataServerLocal getData() {
        return server;
    }
}
