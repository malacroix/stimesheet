/**
 * 
 */
package ca.lc.stimesheet.model.event;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Marc-Andre Lacroix
 *
 */
public class Account {

    private String accountIdentifier;
    private String status;
    
    /**
     * @return the accountIdentifier
     */
    public String getAccountIdentifier() {
        return accountIdentifier;
    }
    /**
     * @param accountIdentifier the accountIdentifier to set
     */
    public void setAccountIdentifier(String accountIdentifier) {
        this.accountIdentifier = accountIdentifier;
    }
    
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE); 
    }
}
