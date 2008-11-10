package edu.wustl.security.locator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import edu.wustl.common.util.dbmanager.DBUtil;
import edu.wustl.common.util.global.Constants;
import edu.wustl.common.util.logger.Logger;
import edu.wustl.security.manager.SecurityManager;
/**
 * Reads the SecurityManager.properties file and loads properties to be referred by SecurityManager.
 * 
 * @author deepti_shelar
 */
public final class SecurityManagerPropertiesLocator {

	/**
	 * logger Logger - Generic logger.
	 */
	private static org.apache.log4j.Logger logger = Logger.getLogger(SecurityManager.class);

	/**
	 * property names from SecurityManager.properties file 
	 */
	private  String applicationCtxName = null;	
	private  String securityMgrClassName = null;
	
	/**
	 * Instantiating the class whenever loaded for the first time. The same instance will be returned whenever getInstance is called. 
	 */
	private static SecurityManagerPropertiesLocator singleObj = new SecurityManagerPropertiesLocator();
	
	/**
	 * Making the class singleton.
	 */
	private SecurityManagerPropertiesLocator() 
	{
		Properties SECURITY_MANAGER_PROP;
		InputStream inputStream = SecurityManagerPropertiesLocator.class.getClassLoader().getResourceAsStream(
				Constants.SECURITY_MANAGER_PROP_FILE);
		SECURITY_MANAGER_PROP = new Properties();
		try
		{
			SECURITY_MANAGER_PROP.load(inputStream);
			inputStream.close();
			applicationCtxName = SECURITY_MANAGER_PROP.getProperty(Constants.APPLN_CONTEXT_NAME);
			securityMgrClassName = SECURITY_MANAGER_PROP.getProperty(Constants.SECURITY_MANAGER_CLASSNAME);
		}
		catch (IOException exception)
		{
			logger.fatal("Not able to initialize Security Manager Properties.", exception);
		}
	}
	/**
	 * Singleton class, will return the single object every time.
	 * @return SecurityManagerPropertiesLocator instance
	 */
	public static SecurityManagerPropertiesLocator getInstance()
	{
		return singleObj;
	}

	/**
	 * @return the applicationCtxName
	 */
	public String getApplicationCtxName() {
		return applicationCtxName;
	}

	/**
	 * @return the securityMgrClassName
	 */
	public String getSecurityMgrClassName() {
		return securityMgrClassName;
	}

	
	
}