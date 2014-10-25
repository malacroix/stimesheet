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
public class Marketplace {

    private String baseUrl;
    private String partner;
    /**
     * @return the baseUrl
     */
    public String getBaseUrl() {
        return baseUrl;
    }
    /**
     * @param baseUrl the baseUrl to set
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    /**
     * @return the partner
     */
    public String getPartner() {
        return partner;
    }
    /**
     * @param partner the partner to set
     */
    public void setPartner(String partner) {
        this.partner = partner;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE); 
    }
}
