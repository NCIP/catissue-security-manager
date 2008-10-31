/**
 *<p>Title: </p>
 *<p>Description:  </p>
 *<p>Copyright: (c) Washington University, School of Medicine 2004</p>
 *<p>Company: Washington University, School of Medicine, St. Louis.</p>
 *@author Aarti Sharma
 *@version 1.0
 */

package edu.wustl.security.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import edu.wustl.common.beans.NameValueBean;
import edu.wustl.common.domain.AbstractDomainObject;
import edu.wustl.common.query.AbstractClient;
import edu.wustl.common.security.exceptions.SMException;
import edu.wustl.common.security.exceptions.SMTransactionException;
import edu.wustl.common.util.Permissions;
import edu.wustl.common.util.Roles;
import edu.wustl.common.util.XMLPropertyHandler;
import edu.wustl.common.util.global.Constants;
import edu.wustl.common.util.global.TextConstants;
import edu.wustl.common.util.logger.Logger;
import edu.wustl.security.beans.RoleGroupDetailsBean;
import edu.wustl.security.global.ProvisionManager;
import edu.wustl.security.locator.RoleGroupLocator;
import edu.wustl.security.locator.SecurityManagerPropertiesLocator;
import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.ObjectPrivilegeMap;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.authorization.domainobjects.Privilege;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.authorization.domainobjects.Role;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.dao.GroupSearchCriteria;
import gov.nih.nci.security.dao.ProtectionElementSearchCriteria;
import gov.nih.nci.security.dao.SearchCriteria;
import gov.nih.nci.security.dao.UserSearchCriteria;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.exceptions.CSTransactionException;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: (c) Washington University, School of Medicine 2005
 * </p>
 * <p>
 * Company: Washington University, School of Medicine, St. Louis.
 * </p>
 * 
 * @author Aarti Sharma
 * @version 1.0
 */

public class SecurityManager implements Permissions,ISecurityManager
{

	/**
	 * logger Logger - Generic logger.
	 */
	protected static org.apache.log4j.Logger logger = Logger.getLogger(SecurityManager.class);



	private Class requestingClass = null;


	public static HashMap<String, String> rolegroupNamevsId = new HashMap<String, String>();

	public static final String ADMINISTRATOR_GROUP = "ADMINISTRATOR_GROUP";
	public static final String SUPER_ADMINISTRATOR_GROUP = "SUPER_ADMINISTRATOR_GROUP";
	public static final String SUPERVISOR_GROUP = "SUPERVISOR_GROUP";
	public static final String TECHNICIAN_GROUP = "TECHNICIAN_GROUP";
	public static final String PUBLIC_GROUP = "PUBLIC_GROUP";

	public static final String CLASS_NAME = "CLASS_NAME";

	public static final String TABLE_NAME = "TABLE_NAME";

	public static final String TABLE_ALIAS_NAME = "TABLE_ALIAS_NAME";

	protected static String securityDataPrefix = CLASS_NAME;




	/**
	 * @param class1
	 *//*
	public SecurityManager(Class class1)
	{
		super();
		requestingClass = class1;
		if (!initialized)
		{
			getApplicationContextName();
			initializeConstants();
		}
	}*/

	/**
	 * @param class1
	 * @return
	 *//*
	public static final SecurityManager getInstance(Class class1)
	{
		Class className = null;
		SecurityManager securityManager=null;
		try
		{
			String SECURITY_MANAGER_CLASS=SECURITY_MANAGER_PROP.getProperty(Constants.SECURITY_MANAGER_CLASSNAME);
			if (SECURITY_MANAGER_CLASS != null)
			{
				className = Class.forName(SECURITY_MANAGER_CLASS);
			}
			if (className != null)
			{
				Constructor[] cons = className.getConstructors();
				securityManager= (SecurityManager) cons[0].newInstance(class1);
			}
		}
		catch (Exception exception)
		{
			logger.warn("Error in creating SecurityManager instance",exception);
		}

		if(null==securityManager)
		{
			securityManager= new SecurityManager(class1);
		}
		return securityManager;
	}*/

	/*private static String getApplicationContextName()
	{
		String applicationName = "";
		try
		{
			SecurityManagerPropertiesLocator.APPLICATION_CONTEXT_NAME = applicationName;
		}
		catch (Exception exception)
		{
			logger.warn("Error in getting application context name",exception);
		}

		return applicationName;
	}
	 */

	/*private void initializeConstants()
	{

		try
		{

	 * Here 
	 * Constants.ADMINISTRATOR_ROLE = roleTYpe
	 * Constants.ADMINISTRATOR_GROUP_ID = group type
	 * Constants.ROLE_ADMINISTRATOR = role_name
	 * public static final String ADMINISTRATOR_GROUP = "ADMINISTRATOR_GROUP"; = groupName
	 * 

			rolegroupNamevsId.put(Constants.ADMINISTRATOR_ROLE,ProvisionManager.getRoleID(Constants.ROLE_ADMINISTRATOR));
			rolegroupNamevsId.put(Constants.PUBLIC_ROLE, ProvisionManager.getRoleID(Constants.SCIENTIST));
			rolegroupNamevsId.put(Constants.TECHNICIAN_ROLE, ProvisionManager.getRoleID(Constants.TECHNICIAN));
			rolegroupNamevsId.put(Constants.SUPER_ADMINISTRATOR_ROLE,ProvisionManager.getRoleID(Constants.ROLE_SUPER_ADMINISTRATOR));
			rolegroupNamevsId.put(Constants.SUPERVISOR_ROLE, ProvisionManager.getRoleID(Constants.SUPERVISOR));

			rolegroupNamevsId.put(Constants.ADMINISTRATOR_GROUP_ID, ProvisionManager.getGroupID(ADMINISTRATOR_GROUP));
			rolegroupNamevsId.put(Constants.PUBLIC_GROUP_ID, ProvisionManager.getGroupID(PUBLIC_GROUP));
			rolegroupNamevsId.put(Constants.TECHNICIAN_GROUP_ID, ProvisionManager.getGroupID(TECHNICIAN_GROUP));
			rolegroupNamevsId.put(Constants.SUPERVISOR_GROUP_ID, ProvisionManager.getGroupID(SUPERVISOR_GROUP));
			rolegroupNamevsId.put(Constants.SUPER_ADMINISTRATOR_GROUP_ID,ProvisionManager.getRoleID(SUPER_ADMINISTRATOR_GROUP));
		}
		catch (CSException exception)
		{
			logger.warn("Error in initializing rolegroupNamevsId map",exception);
		}
		catch (SMException exception)
		{
			logger.warn("Error in initializing rolegroupNamevsId map",exception);
		}
	}

	 */



	/**
	 * Returns true or false depending on the person gets authenticated or not.
	 * @param requestingClass
	 * @param loginName login name
	 * @param password password
	 * @return
	 * @throws CSException
	 */
	public boolean login(String loginName, String password) throws SMException
	{
		boolean loginSuccess = false;
		try
		{
			AuthenticationManager authMngr = ProvisionManager.getAuthenticationManager();
			loginSuccess = authMngr.login(loginName, password);
		}
		catch (CSException exception)
		{
			StringBuffer mesg=new StringBuffer("Authentication fails for user")
			.append(loginName).append("requestingClass:").append(requestingClass);
			logger.debug(mesg.toString());
			throw new SMException(mesg.toString(), exception);
		}
		return loginSuccess;
	}

	/**
	 * This method creates a new User in the database based on the data passed
	 * 
	 * @param user
	 *            user to be created
	 * @throws SMTransactionException
	 *             If there is any exception in creating the User
	 */
	public void createUser(User user) throws SMTransactionException
	{
		try
		{
			ProvisionManager.getUserProvisioningManager().createUser(user);
		}
		catch (CSTransactionException exception)
		{
			logger.debug("Unable to create user "+user.getEmailId());
			throw new SMTransactionException(exception.getMessage(), exception);
		}
		catch (CSException exception)
		{
			logger.debug("Unable to create user:"+user.getEmailId(), exception);
		}
	}

	/**
	 * This method returns the User object from the database for the passed
	 * User's Login Name. If no User is found then null is returned
	 *
	 * @param loginName
	 *            Login name of the user
	 * @return @throws
	 *         SMException
	 */
	public User getUser(String loginName) throws SMException
	{
		try
		{
			return ProvisionManager.getAuthorizationManager().getUser(loginName);
		}
		catch (CSException exception)
		{
			logger.debug("Unable to get user: "+loginName,exception);
			throw new SMException("Unable to get user: "+loginName, exception);
		}
	}

	/**
	 * This method returns array of CSM user id of all users who are administrators
	 * @return
	 * @throws SMException
	 *//*
	public Long[] getAllAdministrators() throws SMException
	{
		Long[] userId;
		try
		{
			Group group = new Group();
			group.setGroupName(ADMINISTRATOR_GROUP);
			GroupSearchCriteria groupSearchCriteria = new GroupSearchCriteria(group);
			List list = getObjects(groupSearchCriteria);
			group = (Group) list.get(0);
			Set<User> users = group.getUsers();
			userId = new Long[users.size()];
			Iterator<User> iterator = users.iterator();
			for (int i = 0; i < users.size(); i++)
			{
				userId[i] = ((User) iterator.next()).getUserId();
			}
		}
		catch (CSException exception)
		{
			logger.debug("Unable to get users: Exception: " + exception.getMessage());
			throw new SMException(exception.getMessage(), exception);
		}
		return userId;
	}*/

	/**
	 * This method checks whether a user exists in the database or not
	 *
	 * @param loginName
	 *            Login name of the user
	 * @return TRUE is returned if a user exists else FALSE is returned
	 * @throws SMException
	 *//*
	public boolean userExists(String loginName) throws SMException
	{
		boolean userExists = true;
		try
		{
			if (getUser(loginName) == null)
			{
				userExists = false;
			}
		}
		catch (SMException exception)
		{
			logger.debug("Unable to get user :"+loginName);
			throw exception;
		}
		return userExists;
	}*/

	public void removeUser(String userId) throws SMException
	{
		try
		{
			ProvisionManager.getUserProvisioningManager().removeUser(userId);
		}
		catch (CSTransactionException ex)
		{
			logger.debug("Unable to get user: Exception: " + ex.getMessage());
			throw new SMTransactionException("Failed to find this user with userId:" + userId, ex);
		}
		catch (CSException e)
		{
			logger.debug("Unable to obtain Authorization Manager: Exception: " + e.getMessage());
			throw new SMException("Failed to find this user with userId:" + userId, e);
		}
	}

	/**
	 * This method returns Vactor of all the role objects defined for the
	 * application from the database
	 * 
	 * @return @throws
	 *         SMException
	 */
	public List<Role> getRoles() throws SMException
	{
		List<Role> roles = new ArrayList<Role>();
		UserProvisioningManager userProvisioningManager = null;
		try
		{
			userProvisioningManager = ProvisionManager.getUserProvisioningManager();
			List<String> roleIdList = RoleGroupLocator.getAllRoleIds();
			for (String roleId : roleIdList) {
				roles.add(userProvisioningManager.getRoleById(roleId));
			}
			/*roles.add(userProvisioningManager.getRoleById(rolegroupNamevsId
					.get(Constants.SUPER_ADMINISTRATOR_ROLE)));
			roles.add(userProvisioningManager.getRoleById(rolegroupNamevsId
					.get(Constants.ADMINISTRATOR_ROLE)));
			roles.add(userProvisioningManager.getRoleById(rolegroupNamevsId
					.get(Constants.SUPERVISOR_ROLE)));
			roles.add(userProvisioningManager.getRoleById(rolegroupNamevsId
					.get(Constants.TECHNICIAN_ROLE)));
			roles.add(userProvisioningManager.getRoleById(rolegroupNamevsId
					.get(Constants.PUBLIC_ROLE)));*/

		}
		catch (CSException exception)
		{
			logger.debug("Unable to get roles: Exception: ",exception);
			throw new SMException(exception.getMessage(), exception);
		}
		return roles;
	}

	/**
	 * Assigns a Role to a User
	 * 
	 * @param userName - the User Name to to whom the Role will be assigned
	 * @param roleID -	The id of the Role which is to be assigned to the user
	 * @throws SMException
	 */
	public void assignRoleToUser(String userID, String roleID) throws SMException
	{
		try
		{
			UserProvisioningManager userProvisioningManager = ProvisionManager.getUserProvisioningManager();
			User user = userProvisioningManager.getUserById(userID);

			//Remove user from any other role if he is assigned some
			String userId = String.valueOf(user.getUserId());
			List<String> allGroupIds = RoleGroupLocator.getAllGroupIds();
			for (String grpId : allGroupIds) {
				userProvisioningManager.removeUserFromGroup(grpId, userId);
			}
			/*userProvisioningManager.removeUserFromGroup(rolegroupNamevsId
					.get(Constants.ADMINISTRATOR_GROUP_ID), userId);
			userProvisioningManager.removeUserFromGroup(rolegroupNamevsId
					.get(Constants.SUPERVISOR_GROUP_ID), userId);
			userProvisioningManager.removeUserFromGroup(rolegroupNamevsId
					.get(Constants.TECHNICIAN_GROUP_ID), userId);
			userProvisioningManager.removeUserFromGroup(rolegroupNamevsId
					.get(Constants.PUBLIC_GROUP_ID), userId);*/

			//Add user to corresponding group
			String groupId = getGroupIdForRole(roleID);
			if (groupId == null)
			{
				logger.info(" User assigned no role");
			}
			else
			{
				assignAdditionalGroupsToUser(userId,
						new String[]{groupId});
				logger.info(" User assigned role:" + groupId);
			}

		}
		catch (CSException exception)
		{
			logger.debug("UNABLE TO ASSIGN ROLE TO USER: Exception: " + exception.getMessage());
			throw new SMException(exception.getMessage(), exception);
		}
	}

	public String getGroupIdForRole(String roleID)
	{
		/*String roleName=null;
		String groupType=null;*/
		String roleGroupId=null;
		Map<RoleGroupDetailsBean, RoleGroupDetailsBean> roleGroupDetailsMap = RoleGroupLocator.getRoleGroupDetailsMap();
		RoleGroupDetailsBean sampleBean = new RoleGroupDetailsBean();
		sampleBean.setRoleId(roleID);

		RoleGroupDetailsBean requiredBean = roleGroupDetailsMap.get(sampleBean);
		if(requiredBean == null)
		{
			logger.debug("role corresponds to no group");
		}
		else
		{
			/*	roleName = requiredBean.getRoleName();
			groupType = requiredBean.getGroupType();
			 */	roleGroupId = requiredBean.getGroupId();
		}
		return roleGroupId;

		/*if (roleID.equals(rolegroupNamevsId.get(Constants.ADMINISTRATOR_ROLE)))
		{
			roleName=Constants.ADMINISTRATOR_ROLE;
			groupType=Constants.ADMINISTRATOR_GROUP_ID;
		}
		else if (roleID.equals(rolegroupNamevsId.get(Constants.SUPERVISOR_ROLE)))
		{
			roleName=Constants.SUPERVISOR_ROLE;
			groupType=Constants.SUPERVISOR_GROUP_ID;
		}
		else if (roleID.equals(rolegroupNamevsId.get(Constants.TECHNICIAN_ROLE)))
		{
			roleName=Constants.TECHNICIAN_ROLE;
			groupType=Constants.TECHNICIAN_GROUP_ID;
		}
		else if (roleID.equals(rolegroupNamevsId.get(Constants.PUBLIC_ROLE)))
		{
			roleName=Constants.PUBLIC_ROLE;
			groupType=Constants.PUBLIC_GROUP_ID;
		}
		else
		{
			logger.debug("role corresponds to no group");
		}*/
		/*if(groupType!=null)
		{
			roleGroupId=rolegroupNamevsId.get(groupType);
			logger.info("role corresponds to "+roleName);
		}*/
	}

	public Role getUserRole(long userID) throws SMException
	{
		Set<Group> groups;
		UserProvisioningManager userProvisioningManager = null;
		Role role = null;
		try
		{
			userProvisioningManager = ProvisionManager.getUserProvisioningManager();
			groups = userProvisioningManager.getGroups(String.valueOf(userID));
			role = getRole(groups, userProvisioningManager);
		}
		catch (CSException exception)
		{
			logger.debug("Unable to get roles: Exception: " + exception.getMessage(),exception);
			throw new SMException(exception.getMessage(), exception);
		}
		return role;
	}

	/**
	 * @param groups
	 * @param userProvisioningManager
	 * @param role
	 * @return
	 * @throws CSObjectNotFoundException
	 */
	private Role getRole(Set groups, UserProvisioningManager userProvisioningManager)
	throws CSObjectNotFoundException
	{
		Role role = null;
		Map<RoleGroupDetailsBean, RoleGroupDetailsBean> roleGroupDetailsMap = RoleGroupLocator.getRoleGroupDetailsMap();
		Iterator<Group> it = groups.iterator();
		if (it.hasNext())
		{
			Group group = (Group) it.next();
			if (group.getApplication().getApplicationName().equals(SecurityManagerPropertiesLocator.APPLICATION_CONTEXT_NAME))
			{

				RoleGroupDetailsBean sampleBean = new RoleGroupDetailsBean();
				sampleBean.setGroupName(group.getGroupName());
				RoleGroupDetailsBean requiredBean = roleGroupDetailsMap.get(sampleBean);
				String roleId = requiredBean.getRoleId();
				role = userProvisioningManager.getRoleById(roleId);
				/*if (group.getGroupName().equals(ADMINISTRATOR_GROUP))
				{
					role = userProvisioningManager.getRoleById(rolegroupNamevsId
							.get(Constants.ADMINISTRATOR_ROLE));
					break;
				}
				else if (group.getGroupName().equals(SUPERVISOR_GROUP))
				{
					role = userProvisioningManager.getRoleById(rolegroupNamevsId
							.get(Constants.SUPERVISOR_ROLE));
					break;
				}
				else if (group.getGroupName().equals(TECHNICIAN_GROUP))
				{
					role = userProvisioningManager.getRoleById(rolegroupNamevsId
							.get(Constants.TECHNICIAN_ROLE));
					break;
				}
				else if (group.getGroupName().equals(PUBLIC_GROUP))
				{
					role = userProvisioningManager.getRoleById(rolegroupNamevsId
							.get(Constants.PUBLIC_ROLE));
					break;
				}*/
			}
		}
		return role;
	}

	/**
	 * Name : Virender Mehta
	 * Reviewer: Sachin Lale
	 * Bug ID: 3842
	 * Patch ID: 3842_2
	 * See also: 3842_1
	 * Description: This function will return the Role name(Administrator, Scientist, Technician, Supervisor )
	 * @param userID
	 * @return Role Name
	 * @throws SMException
	 */
	public String getUserGroup(long userID) throws SMException
	{
		String role=TextConstants.EMPTY_STRING;
		Set groups;
		UserProvisioningManager userProvisioningManager = null;
		Iterator it;
		Group group;
		try
		{
			userProvisioningManager = ProvisionManager.getUserProvisioningManager();
			groups = userProvisioningManager.getGroups(String.valueOf(userID));
			it = groups.iterator();
			while (it.hasNext())
			{
				group = (Group) it.next();
				if (group.getApplication().getApplicationName().equals(SecurityManagerPropertiesLocator.APPLICATION_CONTEXT_NAME))
				{
					if (group.getGroupName().equals(ADMINISTRATOR_GROUP))
					{
						role=Roles.ADMINISTRATOR;
						break;
					}
					else if (group.getGroupName().equals(SUPERVISOR_GROUP))
					{
						role=Roles.SUPERVISOR;
						break;
					}
					else if (group.getGroupName().equals(TECHNICIAN_GROUP))
					{
						role=Roles.TECHNICIAN;
						break;
					}
					else if (group.getGroupName().equals(PUBLIC_GROUP))
					{
						role=Roles.SCIENTIST;
						break;
					}
				}
			}
		}
		catch (CSException exception)
		{
			logger.debug("Unable to get roles: Exception: " + exception.getMessage(),exception);
			throw new SMException(exception.getMessage(), exception);
		}
		return role;
	}

	/**
	 * Modifies an entry for an existing User in the database based on the data
	 * passed.
	 *
	 * @param user -the User object that needs to be modified in the database
	 * @throws SMException if there is any exception in modifying the User in the database
	 */
	public void modifyUser(User user) throws SMException
	{
		try
		{
			ProvisionManager.getUserProvisioningManager().modifyUser(user);
		}
		catch (CSException exception)
		{
			logger.debug("Unable to modify user: Exception: " + exception.getMessage(),exception);
			throw new SMException(exception.getMessage(), exception);
		}
	}

	/**
	 * Returns the User object for the passed User id.
	 *
	 * @param userId -The id of the User object which is to be obtained
	 * @return The User object from the database for the passed User id
	 * @throws SMException -if the User object is not found for the given id
	 */
	public User getUserById(String userId) throws SMException
	{
		try
		{
			return ProvisionManager.getUserProvisioningManager().getUserById(userId);
		}
		catch (CSException exception)
		{
			logger.debug("Unable to get user by Id for : "+userId,exception);
			throw new SMException(exception.getMessage(), exception);
		}
	}

	/**
	 * Returns list of the User objects for the passed email address.
	 *
	 * @param emailAddress -Email Address for which users need to be searched
	 * @return List Returns list of the User objects for the passed email address.
	 * @throws SMException if there is any exception while querying the database
	 *//*
	public List getUsersByEmail(String emailAddress) throws SMException
	{
		try
		{
			User user = new User();
			user.setEmailId(emailAddress);
			SearchCriteria searchCriteria = new UserSearchCriteria(user);
			return ProvisionManager.getUserProvisioningManager().getObjects(searchCriteria);
		}
		catch (CSException exception)
		{
			logger.debug("Unable to get users by emailAddress for email:"+emailAddress,exception);
			throw new SMException(exception.getMessage(), exception);
		}
	}
	  */
	/**
	 * @throws SMException
	 *  
	 */
	public List getUsers() throws SMException
	{
		try
		{
			User user = new User();
			SearchCriteria searchCriteria = new UserSearchCriteria(user);
			return ProvisionManager.getUserProvisioningManager().getObjects(searchCriteria);
		}
		catch (CSException exception)
		{
			logger.debug("Unable to get all users: Exception: " + exception.getMessage());
			throw new SMException(exception.getMessage(), exception);
		}
	}



	public void assignUserToGroup(String userGroupname, String userId) throws SMException
	{
		checkForSufficientParamaters(userGroupname, userId);
		try
		{
			Group group = getUserGroup(userGroupname);
			if (group == null)
			{
				logger.debug("No user group with name " + userGroupname + " is present");
			}
			else
			{
				String[] groupIds = {group.getGroupId().toString()};
				assignAdditionalGroupsToUser(userId, groupIds);
			}
		}
		catch (CSException exception)
		{
			String mess="The Security Service encountered a fatal exception.";
			logger.fatal(mess, exception);
			throw new SMException(mess, exception);
		}
	}
	/**
	 * 
	 * @param userGroupname
	 * @param userId
	 * @throws SMException
	 */
	private void checkForSufficientParamaters(String userGroupname,
			String userId) throws SMException {
		if (userId == null || userGroupname == null)
		{
			logger.debug(" Null or insufficient Parameters passed");
			throw new SMException("Null or insufficient Parameters passed");
		}
	}

	public void removeUserFromGroup(String userGroupname, String userId) throws SMException
	{
		checkForSufficientParamaters(userGroupname, userId);
		try
		{
			UserProvisioningManager userProvisioningManager = ProvisionManager.getUserProvisioningManager();
			Group group = getUserGroup(userGroupname);
			if (group != null)
			{
				userProvisioningManager.removeUserFromGroup(group.getGroupId().toString(), userId);
			}
			else
			{
				logger.debug("No user group with name " + userGroupname + " is present");
			}
		}
		catch (CSException ex)
		{
			String mess="The Security Service encountered a fatal exception.";
			logger.fatal(mess, ex);
			throw new SMException(mess, ex);
		}
	}

	/**
	 * @param userGroupname
	 * @return
	 * @throws SMException
	 * @throws CSException
	 */
	private Group getUserGroup(String userGroupname) throws SMException, CSException
	{
		Group group = new Group();
		group.setGroupName(userGroupname);
		SearchCriteria searchCriteria = new GroupSearchCriteria(group);
		List list = ProvisionManager.getObjects(searchCriteria);
		if (list.isEmpty())
		{
			group=null;
		}
		else
		{
			group = (Group) list.get(0);
		}

		return group;
	}

	public void assignAdditionalGroupsToUser(String userId, String[] groupIds) throws SMException
	{
		if (userId == null || groupIds == null || groupIds.length < 1)
		{
			String mesg=" Null or insufficient Parameters passed";
			logger.debug(mesg);
			throw new SMException(mesg);
		}
		Set consolidatedGroupIds = new HashSet();
		Set consolidatedGroups;
		String[] finalUserGroupIds;
		UserProvisioningManager userProvisioningManager;
		Group group = null;
		try
		{
			userProvisioningManager = ProvisionManager.getUserProvisioningManager();
			consolidatedGroups = userProvisioningManager.getGroups(userId);
			if (null != consolidatedGroups)
			{
				Iterator it = consolidatedGroups.iterator();
				while (it.hasNext())
				{
					group = (Group) it.next();
					consolidatedGroupIds.add(String.valueOf(group.getGroupId()));
				}
			}
			//Consolidating all the Groups
			for (int i = 0; i < groupIds.length; i++)
			{
				consolidatedGroupIds.add(groupIds[i]);
			}
			finalUserGroupIds = new String[consolidatedGroupIds.size()];
			Iterator it = consolidatedGroupIds.iterator();
			for (int i = 0; it.hasNext(); i++)
			{
				finalUserGroupIds[i] = (String) it.next();
			}
			//Setting groups for user and updating it
			userProvisioningManager.assignGroupsToUser(userId, finalUserGroupIds);
		}
		catch (CSException exception)
		{
			String mesg="The Security Service encountered a fatal exception.";
			logger.fatal(mesg, exception);
			throw new SMException(mesg, exception);
		}
	}

	public boolean isAuthorized(String userName, String objectId, String privilegeName)
	throws SMException
	{
		try
		{
			return ProvisionManager.getAuthorizationManager().checkPermission(userName, objectId,privilegeName);
		}
		catch (CSException e)
		{
			logger.debug( e.getMessage(),e);
			throw new SMException(e.getMessage(), e);
		}
	}

	public boolean checkPermission(String userName, String objectType, String objectIdentifier,
			String privilegeName) throws SMException
			{
		boolean isAuthorized=true;
		if (Boolean.parseBoolean(XMLPropertyHandler.getValue(Constants.ISCHECKPERMISSION)))
		{
			try
			{
				isAuthorized = ProvisionManager.getAuthorizationManager().checkPermission(userName,
						objectType + "_" + objectIdentifier, privilegeName);
			}
			catch (CSException exception)
			{
				logger.debug("Unable tocheck permissionn" ,exception);
				throw new SMException("Unable to check permission",exception);
			}
		}
		return isAuthorized;
			}

	/**
	 * This method returns name of the Protection groupwhich consists of obj as
	 * Protection Element and whose name consists of string nameConsistingOf.
	 * 
	 * @param obj
	 * @param nameConsistingOf
	 * @return @throws
	 *         SMException
	 */
	public String getProtectionGroupByName(AbstractDomainObject obj, String nameConsistingOf)
	throws SMException
	{
		Set protectionGroups;
		ProtectionGroup protectionGroup;
		ProtectionElement protectionElement;
		String name = null;
		String protectionElementName = obj.getObjectId();
		try
		{
			AuthorizationManager authManager = ProvisionManager.getAuthorizationManager();
			protectionElement = authManager.getProtectionElement(protectionElementName);
			protectionGroups = authManager.getProtectionGroups(protectionElement
					.getProtectionElementId().toString());
			Iterator<ProtectionGroup> it = protectionGroups.iterator();
			while (it.hasNext())
			{
				protectionGroup = (ProtectionGroup) it.next();
				name = protectionGroup.getProtectionGroupName();
				if (name.indexOf(nameConsistingOf) != -1)
				{
					break;
				}
			}
		}
		catch (CSException exception)
		{
			String mess= new StringBuffer("Unable to get protection group by name")
			.append(nameConsistingOf).append(" for Protection Element ")
			.append(protectionElementName).toString();
			logger.debug(mess, exception);
			throw new SMException(mess,exception);
		}
		return name;

	}

	/**
	 * This method returns name of the Protection groupwhich consists of obj as
	 * Protection Element and whose name consists of string nameConsistingOf.
	 * 
	 * @param obj
	 * @param nameConsistingOf
	 * @return @throws SMException
	 */
	public String[] getProtectionGroupByName(AbstractDomainObject obj) throws SMException
	{
		Set protectionGroups;
		Iterator it;
		ProtectionGroup protectionGroup;
		ProtectionElement protectionElement;
		String[] names = null;
		String protectionElementName = obj.getObjectId();
		try
		{
			AuthorizationManager authManager = ProvisionManager.getAuthorizationManager();
			protectionElement = authManager.getProtectionElement(protectionElementName);
			protectionGroups = authManager.getProtectionGroups(protectionElement
					.getProtectionElementId().toString());
			it = protectionGroups.iterator();
			names = new String[protectionGroups.size()];
			int i = 0;
			while (it.hasNext())
			{
				protectionGroup = (ProtectionGroup) it.next();
				names[i++] = protectionGroup.getProtectionGroupName();

			}
		}
		catch (CSException exception)
		{
			String mess="Unable to get protection group for Protection Element "+ protectionElementName;
			logger.debug(mess,exception);
			throw new SMException(mess, exception);
		}
		return names;

	}

	/**
	 * Returns name value beans corresponding to all privileges that can be
	 * assigned for Assign Privileges Page.
	 * 
	 * @param roleName role name of user logged in
	 * @return
	 */
	public List<NameValueBean> getPrivilegesForAssignPrivilege(String roleName)
	{
		List<NameValueBean> privileges = new Vector();
		NameValueBean nameValueBean;
		nameValueBean = new NameValueBean(Permissions.READ, Permissions.READ);
		privileges.add(nameValueBean);
		//Use privilege only provided to Administrtor in Assing privileges page.
		if (roleName.equals(Constants.ADMINISTRATOR))
		{
			nameValueBean = new NameValueBean(Permissions.USE, Permissions.USE);
			privileges.add(nameValueBean);
		}
		return privileges;
	}

	/**
	 * This method returns NameValueBeans for all the objects of type objectType
	 * on which user with identifier userID has privilege ASSIGN_ <
	 * <privilegeName>>.
	 * 
	 * @param userID
	 * @param objectType
	 * @param privilegeName
	 * @return @throws
	 *         SMException thrown if any error occurs while retreiving
	 *         ProtectionElementPrivilegeContextForUser
	 */
	private Set<NameValueBean> getObjectsForAssignPrivilege(Collection privilegeMap, String objectType,
			String privilegeName) throws SMException
			{
		Set<NameValueBean> objects = new HashSet<NameValueBean>();
		NameValueBean nameValueBean;
		ObjectPrivilegeMap objectPrivilegeMap;
		Collection privileges;
		Iterator iterator;
		String objectId;
		Privilege privilege;

		if (privilegeMap != null)
		{
			iterator = privilegeMap.iterator();
			while (iterator.hasNext())
			{
				objectPrivilegeMap = (ObjectPrivilegeMap) iterator.next();
				objectId = objectPrivilegeMap.getProtectionElement().getObjectId();
				if (objectId.indexOf(objectType + "_") != -1)
				{
					privileges = objectPrivilegeMap.getPrivileges();
					Iterator it = privileges.iterator();
					String name;
					while (it.hasNext())
					{
						privilege = (Privilege) it.next();
						if (privilege.getName().equals("ASSIGN_" + privilegeName))
						{
							name=objectId.substring(objectId.lastIndexOf('_') + 1);
							nameValueBean = new NameValueBean(name,name);
							objects.add(nameValueBean);
							break;
						}
					}
				}
			}
		}

		return objects;
			}

	/**
	 * This method returns name value beans of the object ids for types
	 * identified by objectTypes on which user can assign privileges identified
	 * by privilegeNames User needs to have ASSIGN_ {privilegeName}privilege
	 * on these objects to assign corresponding privilege on them identified by
	 * userID has.
	 *
	 * @param userID
	 * @param objectTypes
	 * @param privilegeNames
	 * @return @throws SMException
	 */
	public Set<NameValueBean> getObjectsForAssignPrivilege(String userID, String[] objectTypes,
			String[] privilegeNames) throws SMException
			{
		Set<NameValueBean> objects=null;

		try
		{
			User user = new User();
			user = getUserById(userID);
			if (objectTypes == null || privilegeNames == null ||user == null)
			{
				logger.debug(" User not found");
				objects = new HashSet<NameValueBean>();
			}
			else
			{
				objects=getAssignedPrivilege(objectTypes, privilegeNames, user);
			}
		}
		catch (CSException exception)
		{
			logger.debug("Unable to get objects: " ,exception);
			throw new SMException("Unable to get objects: ", exception);
		}
		return objects;

			}

	/**
	 * @param objectTypes
	 * @param privilegeNames
	 * @param objects
	 * @param user
	 * @throws CSException
	 */
	private Set<NameValueBean> getAssignedPrivilege(String[] objectTypes, String[] privilegeNames,
			User user) throws CSException
			{
		Set<NameValueBean> objects = new HashSet<NameValueBean>();
		Collection privilegeMap;
		List list;
		ProtectionElement protectionElement;
		ProtectionElementSearchCriteria protectionElementSearchCriteria;
		AuthorizationManager authorizationManager = ProvisionManager.getAuthorizationManager();
		for (int i = 0; i < objectTypes.length; i++)
		{
			for (int j = 0; j < privilegeNames.length; j++)
			{
				try
				{
					protectionElement = new ProtectionElement();
					protectionElement.setObjectId(objectTypes[i] + "_*");
					protectionElementSearchCriteria=new ProtectionElementSearchCriteria(protectionElement);
					list = ProvisionManager.getObjects(protectionElementSearchCriteria);
					privilegeMap=authorizationManager.getPrivilegeMap(user.getLoginName(),list);
					for (int k = 0; k < list.size(); k++)
					{
						protectionElement = (ProtectionElement) list.get(k);
					}

					objects.addAll(getObjectsForAssignPrivilege(privilegeMap, objectTypes[i],
							privilegeNames[j]));
				}
				catch (SMException smex)
				{
					logger.debug(" Exception in getting object of privileges", smex);
				}
			}
		}
		return objects;
			}



	/**
	 * Checks whether an object type has any identified data associated with
	 * it or not.
	 * @param aliasName
	 * @return
	 */
	protected boolean hasAssociatedIdentifiedData(String aliasName)
	{
		boolean hasIdentifiedData = false;
		List identifiedData = new ArrayList();
		identifiedData = (List) AbstractClient.identifiedDataMap.get(aliasName);
		if (identifiedData != null)
		{
			hasIdentifiedData = true;
		}
		return hasIdentifiedData;
	}

	/*public static String getSecurityDataPrefix()
	{
		return securityDataPrefix;
	}

	public static void setSecurityDataPrefix(String securityDataPrefix)
	{
		SecurityManager.securityDataPrefix = securityDataPrefix;
	}*/

	/**
	 * Description: This method checks user's privilege on identified data.
	 * Name : Aarti Sharma
	 * Reviewer: Sachin Lale
	 * Bug ID: 4111
	 * Patch ID: 4111_2
	 * See also: 4111_1
	 * @param userId User's Identifier
	 * @return true if user has privilege on identified data else false
	 * @throws SMException
	 *//*
	public boolean hasIdentifiedDataAccess(Long userId) throws SMException
	{
		boolean hasIdentifiedDataAccess = false;
		try
		{
			//Get user's role
			Role role = getUserRole(userId.longValue());
			UserProvisioningManager userProvisioningManager = ProvisionManager.getUserProvisioningManager();

			//Get privileges the user has based on his role
			Set<Privilege> privileges = userProvisioningManager.getPrivileges(String.valueOf(role.getId()));
			Iterator<Privilege> privIterator = privileges.iterator();
			Privilege privilege;

			// If user has Identified data access set hasIdentifiedDataAccess true
			for (int i = 0; i < privileges.size(); i++)
			{
				privilege = (Privilege) privIterator.next();
				if (privilege.getName().equals(Permissions.IDENTIFIED_DATA_ACCESS))
				{
					hasIdentifiedDataAccess = true;
					break;
				}
			}
		}
		catch (CSException exception)
		{
			logger.debug("Exception in hasIdentifiedDataAccess method",exception);
			throw new SMException(exception.getMessage(), exception);
		}
		return hasIdentifiedDataAccess;

	}*/
}
