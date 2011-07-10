package suncertify.db.server;

import suncertify.db.RecordNotFoundException;

/**
 * Actual implementation of the DataServer logic. Should be the only place to
 * access the data file through DBMain interface. It should take care of
 * locking, reading and modifying the records. In order to make access secure
 * thread-wise, it's a Singleton.
 * 
 * @author Jakub Rozek
 * 
 */
public class DataServerLocal implements DataServer {

    private DataServerLocal() {

    }

    private static DataServerLocal instance;

    public static synchronized DataServerLocal getInstance() {
        if (instance == null) {
            instance = new DataServerLocal();
        }
        return instance;
    }

    @Override
    public String[][] allRecords()
            throws CannotReadRecordsException {
        // TODO only test data for now, read from DBMain instance
        return new String[][] { { "ASD", "zxc" },
                { "qwe", "gfd" } };
    }

    @Override
    public void book(String[][] records)
            throws RecordNotFoundException {
        // TODO Auto-generated method stub

    }

    @Override
    public String[][] search(String[] criteria)
            throws CannotReadRecordsException {
        // TODO Auto-generated method stub
        return null;
    }

}
