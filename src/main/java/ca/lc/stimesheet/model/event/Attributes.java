/**
 * 
 */
package ca.lc.stimesheet.model.event;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Marc-Andre Lacroix
 *
 */
public class Attributes {

    @XmlElement(name = "entry")
    private List<Entry> entries = new ArrayList<Entry>();
    
    /**
     * @return the entries
     */
    @XmlTransient
    public List<Entry> getEntries() {
        return entries;
    }
    /**
     * @param entries the entries to set
     */
    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE); 
    }
}
