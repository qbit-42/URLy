package suncertify.db.server;

import java.rmi.RemoteException;

import suncertify.db.RecordNotFoundException;
import suncertify.db.server.remote.DataServerRemote;

/**
 * Client-side implementation of DataServer interface. Should only forward the
 * calls to DataServerRemoteInterface member, wrapping RemoteExceptions in
 * business exceptions for GUI.
 * 
 * @author Jakub Rozek
 * 
 */
public class DataServerClient implements DataServer {

    DataServerRemote dsRemote;

    public DataServerClient(String serverAddress)
            throws RemoteException {
        // Registry registry = LocateRegistry
        // .getRegistry(serverAddress);
        throw new RemoteException();
    }

    @Override
    public String[][] allRecords()
            throws CannotReadRecordsException {
        try {
            return dsRemote.allRecords();
        } catch (RemoteException e) {
            throw new CannotReadRecordsException(
                    "Cannot read records from remote server. ",
                    e);
        }
    }

    @Override
    public void book(String[][] records)
            throws RecordNotFoundException {
        try {
            dsRemote.book(records);
        } catch (RemoteException e) {
            throw new RecordNotFoundException(
                    "Cannot book records on remote server. ", e);
        }
    }

    @Override
    public String[][] search(String[] criteria)
            throws CannotReadRecordsException {
        try {
            return dsRemote.search(criteria);
        } catch (RemoteException e) {
            throw new CannotReadRecordsException(
                    "Cannot search for records on a remote server. ",
                    e);
        }
    }

}
