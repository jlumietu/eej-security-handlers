/**
 * 
 */
package com.eej.security.rememberme;

import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

/**
 * @author jlumietu - Mikel Ibiricu Alfaro
 *
 */
public class CustomizableJdbcTokenRepositoryImpl extends
		JdbcTokenRepositoryImpl {

	private String tokensBySeriesSql = DEF_TOKEN_BY_SERIES_SQL;
	private String insertTokenSql = DEF_INSERT_TOKEN_SQL;
	private String updateTokenSql = DEF_UPDATE_TOKEN_SQL;
	private String removeUserTokensSql = DEF_REMOVE_USER_TOKENS_SQL;
	private boolean createTableOnStartup = false;
	/**
	 * @return the tokensBySeriesSql
	 */
	public String getTokensBySeriesSql() {
		return tokensBySeriesSql;
	}
	/**
	 * @param tokensBySeriesSql the tokensBySeriesSql to set
	 */
	public void setTokensBySeriesSql(String tokensBySeriesSql) {
		this.tokensBySeriesSql = tokensBySeriesSql;
	}
	/**
	 * @return the insertTokenSql
	 */
	public String getInsertTokenSql() {
		return insertTokenSql;
	}
	/**
	 * @param insertTokenSql the insertTokenSql to set
	 */
	public void setInsertTokenSql(String insertTokenSql) {
		this.insertTokenSql = insertTokenSql;
	}
	/**
	 * @return the updateTokenSql
	 */
	public String getUpdateTokenSql() {
		return updateTokenSql;
	}
	/**
	 * @param updateTokenSql the updateTokenSql to set
	 */
	public void setUpdateTokenSql(String updateTokenSql) {
		this.updateTokenSql = updateTokenSql;
	}
	/**
	 * @return the removeUserTokensSql
	 */
	public String getRemoveUserTokensSql() {
		return removeUserTokensSql;
	}
	/**
	 * @param removeUserTokensSql the removeUserTokensSql to set
	 */
	public void setRemoveUserTokensSql(String removeUserTokensSql) {
		this.removeUserTokensSql = removeUserTokensSql;
	}
	/**
	 * @return the createTableOnStartup
	 */
	public boolean isCreateTableOnStartup() {
		return createTableOnStartup;
	}
	/**
	 * @param createTableOnStartup the createTableOnStartup to set
	 */
	public void setCreateTableOnStartup(boolean createTableOnStartup) {
		this.createTableOnStartup = createTableOnStartup;
	}
	
	
	
}
