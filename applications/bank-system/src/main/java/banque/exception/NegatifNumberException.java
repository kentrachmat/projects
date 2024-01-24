package banque.exception;

/**
 * A class to design a NegatifNumberException extending the exception class
 *
 * @author Hichem KARFA, Benedictus Kent RACHMAT
 * @version 1.0
 */
public class NegatifNumberException extends Exception {

    private static final long serialVersionUID = 8861414840253662763L;

    /**
     * the message to be shown when the  exception is raised
     *
     * @param msg the error message
     */
    public NegatifNumberException(String msg) {
        super(msg);
    }
}