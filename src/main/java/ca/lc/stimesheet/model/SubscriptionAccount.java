/**
 * 
 */
package ca.lc.stimesheet.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Marc-Andre Lacroix
 *
 */
@Document
public class SubscriptionAccount implements Serializable {
    private static final long serialVersionUID = -2592320862461030862L;

    @Id
    public String id;
    
    private SubscriptionState state;
    
    private String editionCode;
    
    @DBRef
    private List<User> assignedUsers = new ArrayList<User>();
    
    @DBRef
    private PartnerMarketplace fromMarketplace;
    
    /**
     * @return Flag indicating if the subscription is currently active or not
     */
    public boolean isActive() {
        return SubscriptionState.ACTIVE.equals(state) || SubscriptionState.FREE_TRIAL.equals(state);
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * @return the state
     */
    public SubscriptionState getState() {
        return state;
    }
    /**
     * @param state the state to set
     */
    public void setState(SubscriptionState state) {
        this.state = state;
    }
    
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
     * @return the assignedUsers
     */
    public List<User> getAssignedUsers() {
        return assignedUsers;
    }
    /**
     * @param assignedUsers the assignedUsers to set
     */
    public void setAssignedUsers(List<User> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }
    
    /**
     * @return the fromMarketplace
     */
    public PartnerMarketplace getFromMarketplace() {
        return fromMarketplace;
    }
    /**
     * @param fromMarketplace the fromMarketplace to set
     */
    public void setFromMarketplace(PartnerMarketplace fromMarketplace) {
        this.fromMarketplace = fromMarketplace;
    }
    
    /**
     * Derived getter to retrieve the Account Creator from the Assigned Users.
     * @return 
     */
    @Transient
    public User getCreator() {
        for (User assignedUser : assignedUsers) {
            if (assignedUser.isAccountCreator()) {
                return assignedUser;
            }
        }
        // could not find any
        return null;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE); 
    }
}
