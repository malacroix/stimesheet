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
public class Payload {

    private Company company;
    private Configuration configuration;
    private Account account;
    private Order order;
    private EventUser user;
    private Notice notice;
    
    /**
     * @return the company
     */
    public Company getCompany() {
        return company;
    }
    /**
     * @param company the company to set
     */
    public void setCompany(Company company) {
        this.company = company;
    }
    /**
     * @return the configuration
     */
    public Configuration getConfiguration() {
        return configuration;
    }
    /**
     * @param configuration the configuration to set
     */
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
    
    /**
     * @return the account
     */
    public Account getAccount() {
        return account;
    }
    /**
     * @param account the account to set
     */
    public void setAccount(Account account) {
        this.account = account;
    }
    
    /**
     * @return the order
     */
    public Order getOrder() {
        return order;
    }
    /**
     * @param order the order to set
     */
    public void setOrder(Order order) {
        this.order = order;
    }
    
    /**
     * @return the user
     */
    public EventUser getUser() {
        return user;
    }
    /**
     * @param user the user to set
     */
    public void setUser(EventUser user) {
        this.user = user;
    }
    
    /**
     * @return the notice
     */
    public Notice getNotice() {
        return notice;
    }
    /**
     * @param notice the notice to set
     */
    public void setNotice(Notice notice) {
        this.notice = notice;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE); 
    }
}
