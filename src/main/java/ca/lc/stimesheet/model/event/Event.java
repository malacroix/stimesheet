/**
 * 
 */
package ca.lc.stimesheet.model.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Marc-Andre Lacroix
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Event {

    private EventUser creator;
    private Marketplace marketplace;
    private Payload payload;
    
    private String flag;
    private String returnUrl;
    private String type;
    /**
     * @return the creator
     */
    public EventUser getCreator() {
        return creator;
    }
    /**
     * @param creator the creator to set
     */
    public void setCreator(EventUser creator) {
        this.creator = creator;
    }
    /**
     * @return the marketplace
     */
    public Marketplace getMarketplace() {
        return marketplace;
    }
    /**
     * @param marketplace the marketplace to set
     */
    public void setMarketplace(Marketplace marketplace) {
        this.marketplace = marketplace;
    }
    /**
     * @return the payload
     */
    public Payload getPayload() {
        return payload;
    }
    /**
     * @param payload the payload to set
     */
    public void setPayload(Payload payload) {
        this.payload = payload;
    }
    /**
     * @return the flag
     */
    public String getFlag() {
        return flag;
    }
    /**
     * @param flag the flag to set
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }
    /**
     * @return the returnUrl
     */
    public String getReturnUrl() {
        return returnUrl;
    }
    /**
     * @param returnUrl the returnUrl to set
     */
    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE); 
    }
}
