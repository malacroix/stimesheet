package ca.lc.stimesheet.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * User definition which can manager timesheets depending on his subscription account.
 * 
 * A user is only supported to be assigned to a single subscription account.
 * 
 * @author Marc-Andre Lacroix
 *
 */
@Document
public class User implements Serializable {
    private static final long serialVersionUID = -6263342931115269042L;

    @Id
    private String openId;
    
	private String uuid;

	private String firstName;
	private String lastName;
	private String email;
	
	private String language;
	
	@DBRef
	private PartnerMarketplace fromMarketplace;
	
	@DBRef
	private SubscriptionAccount account;
	
	private boolean accountCreator;

    /**
     * @return the openId
     */
    public String getOpenId() {
        return openId;
    }
    /**
     * @param openId the openId to set
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    
	/**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
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
     * @return the account
     */
    public SubscriptionAccount getAccount() {
        return account;
    }
    /**
     * @param account the account to set
     */
    public void setAccount(SubscriptionAccount account) {
        this.account = account;
    }
    
    /**
     * @return the accountCreator
     */
    public boolean isAccountCreator() {
        return accountCreator;
    }
    /**
     * @param accountCreator the accountCreator to set
     */
    public void setAccountCreator(boolean accountCreator) {
        this.accountCreator = accountCreator;
    }

    /**
     * @return Flag indicating if its Subscription Account is active or not, no account is considered as not active
     */
    public boolean isAccountActive() {
        return getAccount() != null && getAccount().isActive();
    }
    
    @Override
	public String toString() {
	    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE); 
	}
}
