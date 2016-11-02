/**
 * 
 */
package com.eej.security.service;

import java.io.Serializable;

import com.eej.security.model.GroupInfo;

/**
 * @author jlumietu - Mikel Ibiricu Alfaro
 *
 */
public interface GroupInfoDetailsService {
	
	public GroupInfo getGroupInfo(Serializable groupId);
	
	public GroupInfo getUserGroupInfo(Serializable userId);

}
