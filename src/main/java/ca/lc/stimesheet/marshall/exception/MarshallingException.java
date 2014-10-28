/**
 * 
 */
package ca.lc.stimesheet.marshall.exception;

/**
 * Exception that could happen during the marshalling/unmarshalling of an Object.
 * 
 * @author Marc-Andre Lacroix
 *
 */
public class MarshallingException extends Exception {
    private static final long serialVersionUID = 3712969691411387475L;

    public MarshallingException(String message) {
        super(message);
    }
    
    public MarshallingException(Throwable cause) {
        super(cause);
    }
    
    public MarshallingException(String message, Throwable cause) {
        super(message, cause);
    }
}
