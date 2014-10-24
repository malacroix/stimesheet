package ca.lc.stimesheet.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User implements Serializable {
    private static final long serialVersionUID = -6263342931115269042L;

    @Id
	private String uuid;
	
	private String nickname;
	
	private String firstName;
	private String lastName;
	private String fullName;
	
	private String email;
	
	private String language;
	
	// Company info
	private String companyUuid;
	private String companyName;
	private String companyTitle;

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
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
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
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
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
     * @return the companyUuid
     */
    public String getCompanyUuid() {
        return companyUuid;
    }

    /**
     * @param companyUuid the companyUuid to set
     */
    public void setCompanyUuid(String companyUuid) {
        this.companyUuid = companyUuid;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the companyTitle
     */
    public String getCompanyTitle() {
        return companyTitle;
    }

    /**
     * @param companyTitle the companyTitle to set
     */
    public void setCompanyTitle(String companyTitle) {
        this.companyTitle = companyTitle;
    }

    @Override
	public String toString() {
	    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE); 
	}
}
