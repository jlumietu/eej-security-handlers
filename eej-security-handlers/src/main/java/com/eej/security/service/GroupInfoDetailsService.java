/**
 * 
 */
package com.eej.security.service;

import com.eej.security.model.GroupInfo;

/**
 * @author jlumietu
 *
 */
public interface GroupInfoDetailsService {
	
	public GroupInfo getGroupInfo(int groupId);
	
	public GroupInfo getUserGroupInfo(int userId);

}
