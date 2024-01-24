package competition.exception;

/**
 * InvalidNumberOfGroupsException is an exception class, and it is raised when the number of group is invalid
 *
 * @author Hichem KARFA, Benedictus Kent RACHMAT
 * @version 1.0
 */
public class InvalidNumberOfGroupsException extends Exception {

    private static final long serialVersionUID = -1829498603485706675L;

    /**
     * the message to be shown when the  exception is raised
     *
     * @param msg the error message
     */
    public InvalidNumberOfGroupsException(String msg) {
        super(msg);
    }
}