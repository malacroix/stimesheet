/**
 * 
 */
package ca.lc.stimesheet.model.event;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Marc-Andre Lacroix
 *
 */
public class Order {

    private String editionCode;
    private List<OrderItem> items = new ArrayList<OrderItem>();
    private String pricingDuration;
    /**
     * @return the editionCode
     */
    public String getEditionCode() {
        return editionCode;
    }
    /**
     * @param editionCode the editionCode to set
     */
    public void setEditionCode(String editionCode) {
        this.editionCode = editionCode;
    }
    /**
     * @return the items
     */
    @XmlElement(name = "item")
    public List<OrderItem> getItems() {
        return items;
    }

    /**
     * @return the pricingDuration
     */
    public String getPricingDuration() {
        return pricingDuration;
    }
    /**
     * @param pricingDuration the pricingDuration to set
     */
    public void setPricingDuration(String pricingDuration) {
        this.pricingDuration = pricingDuration;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE); 
    }
}
