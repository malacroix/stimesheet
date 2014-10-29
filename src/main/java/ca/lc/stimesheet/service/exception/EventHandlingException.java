/**
 * 
 */
package ca.lc.stimesheet.service.exception;

import ca.lc.stimesheet.model.event.ErrorCode;

/**
 * Exception when an Event Handling doesn't execute as planned.
 * 
 * @author Marc-Andre Lacroix
 *
 */
public class EventHandlingException extends Exception {
    private static final long serialVersionUID = 7514484864035138485L;
    
    private ErrorCode errorCode;  
    
    public EventHandlingException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    
    /**
     * @return the errorCode
     */
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
