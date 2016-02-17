/**
 * 
 */
package com.eej.security.handler.model;

import java.io.Serializable;

import com.eej.security.ApplicationVersion;

/**
 * @author jlumietu
 *
 */
public class ThreePartPrincipalImpl implements ThreePartPrincipal, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = ApplicationVersion.APP_VERSION;
	
	private String name;
	private String mail;
	private int id;
	
	/**
	 * 	
	 * @param name
	 * @param mail
	 * @param id
	 */
	public ThreePartPrincipalImpl(String name, String mail, int id) {
		super();
		this.name = name;
		this.mail = mail;
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see java.security.Principal#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see com.eej.security.handler.model.ThreePartPrincipal#getMail()
	 */
	@Override
	public String getMail() {
		return this.mail;
	}

	/* (non-Javadoc)
	 * @see com.eej.security.handler.model.ThreePartPrincipal#getId()
	 */
	@Override
	public int getId() {
		return this.id;
	}

}
