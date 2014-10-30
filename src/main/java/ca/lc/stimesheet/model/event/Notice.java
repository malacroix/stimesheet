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
public class Notice {

    private NoticeType type;
    
    /**
     * @return the type
     */
    public NoticeType getType() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(NoticeType type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE); 
    }
}
