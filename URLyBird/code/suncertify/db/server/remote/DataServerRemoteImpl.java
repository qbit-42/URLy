package suncertify.db.server.remote;

import suncertify.db.RecordNotFoundException;
import suncertify.db.server.CannotReadRecordsException;
import suncertify.db.server.DataServerLocal;

/**
 * Server-side class for RMI. Should only forward calls to DataServerLocal
 * member and do nothing more.
 * 
 * @author Jakub Rozek
 * 
 */
public class DataServerRemoteImpl implements DataServerRemote {

    private DataServerLocal dataServer = DataServerLocal
            .getInstance();

    @Override
    public String[][] allRecords()
            throws CannotReadRecordsException {
        return dataServer.allRecords();
    }

    @Override
    public void book(String[][] records)
            throws RecordNotFoundException {
        dataServer.book(records);
    }

    @Override
    public String[][] search(String[] criteria)
            throws CannotReadRecordsException {
        return dataServer.search(criteria);
    }
}
