package suncertify.db.server;

import suncertify.db.RecordNotFoundException;

/**
 * Top Data interface, for use in both MainWindowController (Model part). There
 * is a trick that will hopefully make implementation easier: When you change
 * this interface, ALWAYS update DataServerRemoteInterface and
 * DataServerRemoteImpl to reflect those changes (see their comments on how to
 * do that). This way, you only have to implement the methods in DataServerLocal
 * class and the structure will do the rest. It's a workaround for RMI Remote
 * exception declaration.
 * 
 * @author Jakub Rozek
 * 
 */
public interface DataServer {

    String SERVICE_NAME = "URLY_BIRD_DATA_SERVER";

    /**
     * Reads and returns an array of all records in the database.
     * 
     * @return all records from database
     * @throws CannotReadRecordsException
     *             if there is problem reading the database
     */
    String[][] allRecords() throws CannotReadRecordsException;

    /**
     * Locks, modifies and unlocks all records in a given array. If any record
     * from given array isn't found, no records are changed and
     * RecordNotFoundException is thrown.
     * 
     * @param records
     *            which are to be booked
     * @throws RecordNotFoundException
     *             if any of the records aren't present in the database
     */
    void book(String[][] records) throws RecordNotFoundException;

    /**
     * Constructs array of all records that match given search criteria. Null
     * field value means that any value of that field matches criteria, the
     * search matches fields' values exactly.
     * 
     * @param criteria
     *            a normal record
     * @return Array of all records matching criteria
     * @throws CannotReadRecordsException
     *             if there is a problem reading from database
     */
    String[][] search(String[] criteria)
            throws CannotReadRecordsException;
}
