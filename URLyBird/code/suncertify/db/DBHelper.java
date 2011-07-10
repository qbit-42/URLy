package suncertify.db;

/**
 * Interface containing all database-related methods that aren't defined by the
 * specification for DBMain. Made the distinction for maintainability.
 * 
 * @author Jakub Rozek
 * 
 */
public interface DBHelper {
    String COLUMNS[] = new String[] { "Hotel", "City",
            "Room Size", "Smoking", "Price", "Available From",
            "Free" };
}
