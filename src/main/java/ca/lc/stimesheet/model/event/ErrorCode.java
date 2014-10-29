/**
 * 
 */
package ca.lc.stimesheet.model.event;

/**
 * The possible Error Codes that can be returned in the {@link EventResult}
 * 
 * @see <a href="http://info.appdirect.com/developers/docs/event_references/api_error_codes">API Error Codes</a>
 * 
 * @author Marc-Andre Lacroix
 *
 */
public enum ErrorCode {
    USER_ALREADY_EXISTS,
    USER_NOT_FOUND,
    ACCOUNT_NOT_FOUND,
    MAX_USERS_REACHED,
    UNAUTHORIZED,
    OPERATION_CANCELED,
    CONFIGURATION_ERROR,
    INVALID_RESPONSE,
    UNKNOWN_ERROR;
}
