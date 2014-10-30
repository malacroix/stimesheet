/**
 * 
 */
package ca.lc.stimesheet.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Marc-Andre Lacroix
 *
 */
@Document
public class PartnerMarketplace implements Serializable {
    private static final long serialVersionUID = -4408736564695845283L;

    @Id
    private String partnerId;
    
    private String baseUrl;
    
    
    /**
     * @return the partnerId
     */
    public String getPartnerId() {
        return partnerId;
    }
    /**
     * @param partnerId the partnerId to set
     */
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
    
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
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE); 
    }
}
