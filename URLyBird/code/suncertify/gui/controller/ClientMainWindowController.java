package suncertify.gui.controller;

import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import suncertify.Main;
import suncertify.db.server.DataServerClient;
import suncertify.gui.MainWindow;

/**
 * MainWindoController implementation that needs to communicate with a remote
 * data server.
 * 
 * @author Jakub Rozek
 * 
 */
public class ClientMainWindowController extends
        MainWindowController {

    private DataServerClient client;

    public ClientMainWindowController(MainWindow window) {
        super(window);
        setStatus("Disconnected");
        getWindow().setConnected(false);
        initialConnect();
        if (connected) {
            setStatus("Ready");
            window.setConnected(true);
        }
    }

    public void initialConnect() {
        connected = false;
        String address = Main.getInstance().getProperty(
                "server.address");
        connectToAddress(address);
    }

    private void connectToAddress(String address) {
        try {
            client = new DataServerClient(address);
            connected = true;
            setStatus("Ready");
            getWindow().setConnected(true);
            Main.getInstance().setProperty("server.address",
                    address);
        } catch (RemoteException e) {
            showExceptionToUser(new CannotConnectToServerException(
                    "Could not connect to URLyBird server. Please make sure the server address is correct",
                    e));
        }
    }

    @Override
    public void displayConnectionDialog() {
        String address = JOptionPane
                .showInputDialog(
                        getWindow(),
                        "Please input server's address co connect to.",
                        Main.getInstance().getProperty(
                                "server.address"));
        if (address != null) {
            connectToAddress(address);
        }
    }

    @Override
    protected DataServerClient getData() {
        return client;
    }
}
