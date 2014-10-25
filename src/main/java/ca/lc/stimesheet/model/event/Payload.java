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
    private Order order;
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
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE); 
    }
}
