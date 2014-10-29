/**
 * 
 */
package ca.lc.stimesheet.service.exception;

/**
 * Exception when an Event Handling doesn't execute as planned.
 * 
 * @author Marc-Andre Lacroix
 *
 */
public class EventHandlingException extends Exception {
    private static final long serialVersionUID = 7514484864035138485L;
    
    private String errorCode;  
    
    public EventHandlingException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    
    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }
}
