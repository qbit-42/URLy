package suncertify;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;

import suncertify.db.server.DataServer;
import suncertify.db.server.remote.DataServerRemote;
import suncertify.db.server.remote.DataServerRemoteImpl;
import suncertify.gui.MainWindow;

/**
 * The main entry class. Reads console parameter and runs URLyBird in
 * appropriate mode.
 * 
 * @author Jakub Rozek
 * 
 */
public class Main {

    private Properties properties;

    private static Main instance;

    public static synchronized Main getInstance() {
        if (instance == null) {
            instance = new Main();
        }
        return instance;
    }

    private Main() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(
                    "suncertify.properties"));
        } catch (FileNotFoundException e) {
            System.out
                    .println("Could not find suncertify.properties. Using empty properties.");
        } catch (IOException e) {
            System.out
                    .println("Could not load suncertify.properties. Using empty properties.");
        }
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
        try {
            properties.store(new FileOutputStream(
                    "suncertify.properties"), "");
        } catch (FileNotFoundException e) {
            System.out
                    .println("Unable to write to suncerrity.properties. File doesn't exist");
        } catch (IOException e) {
            System.out
                    .println("Writing properties unsuccessful.");
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static void main(String[] args) {
        new Main().run(args);
    }

    private void run(String args[]) {
        try {
            if (args.length == 0) { // no parameters - client mode
                runGui(false);
            } else if (args.length == 1) {
                if ("alone".equals(args[0])) { // standalone mode
                    runGui(true);
                } else if ("server".equals(args[0])) { // server mode
                    runServer();
                } else {
                    throw new IllegalRunModeException();
                }
            } else {
                throw new IllegalRunModeException();
            }
        } catch (IllegalRunModeException e) {
            System.out
                    .println("Usage: java -jar runme.jar [alone/server]");
        }
    }

    /**
     * Fires a client GUI in a separate thread. Refactored here to avoid code
     * repetition.
     */
    private void runGui(final boolean standalone) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow(standalone);
            }
        });
    }

    private void runServer() {
        String name = DataServer.SERVICE_NAME;
        DataServerRemote server = new DataServerRemoteImpl();
        try {
            DataServerRemote stub = (DataServerRemote) UnicastRemoteObject
                    .exportObject(server);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
        } catch (RemoteException e) {
            System.out
                    .println("Could not run in server mode, cause: "
                            + e.getLocalizedMessage());
        }
    }
}
