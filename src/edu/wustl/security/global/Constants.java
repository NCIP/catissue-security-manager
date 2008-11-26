
package edu.wustl.security.global;

import java.util.HashMap;

/**
 * Class specific to constants used in SecurityManager.
 *
 * @author deepti_shelar
 * */
public class Constants
{
	/**
	 * constant for CLASS_LEVEL_SECURE_RETRIEVE.
	 */
	public static final int CLASS_LEVEL_SECURE_RETRIEVE = 1;
	/**
	 * constant for OBJECT_LEVEL_SECURE_RETRIEVE.
	 */
	public static final int OBJECT_LEVEL_SECURE_RETRIEVE = 2;
	/**
	 * constant for INSECURE_RETRIEVE.
	 */
	public static final int INSECURE_RETRIEVE = 0;
	/**
	 * constant for PRIVILEGE_ASSIGN.
	 */
	public static final boolean PRIVILEGE_ASSIGN = true;
	/**
	 * constant for CP_CLASS_NAME.
	 */
	public static final String CP_CLASS_NAME =
		"edu.wustl.catissuecore.domain.CollectionProtocol";//CollectionProtocol.class.getName();
	/**
	 * constant for DP_CLASS_NAME.
	 */
	public static final String DP_CLASS_NAME =
		"edu.wustl.catissuecore.domain.DistributionProtocol";//DistributionProtocol.class.getName();
	/**
	 * constant for STATIC_PG_FOR_OBJ_TYPES.
	 */
	public static final HashMap<String, String[]> STATIC_PG_FOR_OBJ_TYPES
	= new HashMap<String, String[]>();
	/**
	 * constant for SUPER_ADMIN_ROLE.
	 */
	public static final String SUPER_ADMIN_ROLE = "SUPER_ADMIN_ROLE";
	/**
	 * constant for ADMIN_ROLE.
	 */
	public static final String ADMIN_ROLE = "ADMIN_ROLE";
	/**
	 * constant for SUPERVISOR_ROLE.
	 */
	public static final String SUPERVISOR_ROLE = "SUPERVISOR_ROLE";
	/**
	 * constant for TECHNICIAN_ROLE.
	 */
	public static final String TECHNICIAN_ROLE = "TECHNICIAN_ROLE";
	/**
	 * constant for PUBLIC_ROLE.
	 */
	public static final String PUBLIC_ROLE = "PUBLIC_ROLE";
	/**
	 * constant for ADMIN_GRP_ID.
	 */
	public static final String ADMIN_GRP_ID = "ADMIN_GRP_ID";
	/**
	 * constant for SUPERVISOR_GRP_ID.
	 */
	public static final String SUPERVISOR_GRP_ID = "SUPERVISOR_GRP_ID";
	/**
	 * constant for TECH_GRP_ID.
	 */
	public static final String TECH_GRP_ID = "TECH_GRP_ID";
	/**
	 * constant for PUBLIC_GROUP_ID.
	 */
	public static final String PUBLIC_GROUP_ID = "PUBLIC_GROUP_ID";
	/**
	 * constant for SUPER_ADM_GRP_ID.
	 */
	public static final String SUPER_ADM_GRP_ID = "SUPER_ADM_GRP_ID";
	/**
	 * constant for SM_PROP_FILE.
	 */
	public static final String SM_PROP_FILE = "SecurityManager.properties";
	/**
	 * constant for APP_CTX_NAME.
	 */
	public static final String APP_CTX_NAME = "application.context.name";
	/**
	 * constant for SM_CLASSNAME.
	 */
	public static final String SM_CLASSNAME = "class.name";
	/**
	 * constant for ROLE_ADMIN.
	 */
	public static final String ROLE_ADMIN = "Administrator";
	/**
	 * constant for TECHNICIAN.
	 */
	public static final String TECHNICIAN = "Technician";
	/**
	 * constant for SUPERVISOR.
	 */
	public static final String SUPERVISOR = "Supervisor";
	/**
	 * constant for SCIENTIST.
	 */
	public static final String SCIENTIST = "Scientist";
	/**
	 * constant for ROLE_SUPER_ADMIN.
	 */
	public static final String ROLE_SUPER_ADMIN = "SUPERADMINISTRATOR";
	/**
	 * constant for ISCHECKPERMISSION.
	 */
	public static final String ISCHECKPERMISSION = "isToCheckCSMPermission";
	/**
	 * constant for ADMINISTRATOR.
	 */
	public static final String ADMINISTRATOR = "Administrator";
	/**
	 * constant for CATISSUE_SPECIMEN.
	 */
	public static final String CATISSUE_SPECIMEN = "CATISSUE_SPECIMEN";
	/**
	 * constant for PHI_ACCESS.
	 */
	public static final String PHI_ACCESS = "PHI_ACCESS";
	/**
	 * constant for REGISTRATION.
	 */
	public static final String REGISTRATION = "REGISTRATION";
	/**
	 * constant for READ_DENIED.
	 */
	public static final String READ_DENIED = "READ_DENIED";
	/**
	 * constant for ALLOW_OPERATION.
	 */
	public static final String ALLOW_OPERATION = "allowOperation";
	/**
	 * constant for BDATE_TAG_NAME.
	 */
	public static final String BDATE_TAG_NAME = "IS_BIRTH_DATE";
	/**
	 * constant for HASHED_OUT.
	 */
	public static final String HASHED_OUT = "##";
	/**
	 * constant for HASH_CODE.
	 */
	public static final int HASH_CODE = 1;
	/**
	 * Constant for position1.
	 */
	public static final int POSITION1 = 1;
	/**
	 * Constant for position2.
	 */
	public static final int POSITION2 = 2;
	/**
	 * Constant for position3.
	 */
	public static final int POSITION3 = 3;
}