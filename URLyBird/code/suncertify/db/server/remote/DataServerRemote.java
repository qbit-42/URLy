package suncertify.db.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

import suncertify.db.server.remote.commands.DataServerCommand;

public interface DataServerRemote extends Remote {
    <T> T executeCommand(DataServerCommand<T> command)
            throws RemoteException;
}
