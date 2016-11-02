/**
 * 
 */
package com.eej.security.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jlumietu - Mikel Ibiricu Alfaro
 *
 */
public interface LastLoginInformation extends Serializable {
	
	public abstract Date getLastLogin();

}
