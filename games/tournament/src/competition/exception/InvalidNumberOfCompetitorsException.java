package competition.exception;

/**
 * InvalidNumberOfCompetitorsException is an exception class, and it is raised when the number of the competitors is invalid
 *
 * @author Hichem KARFA, Benedictus Kent RACHMAT
 * @version 1.0
 */
public class InvalidNumberOfCompetitorsException extends Exception {

    private static final long serialVersionUID = 8861414840253662763L;

    /**
     * the message to be shown when the  exception is raised
     *
     * @param msg the error message
     */
    public InvalidNumberOfCompetitorsException(String msg) {
        super(msg);
    }
}