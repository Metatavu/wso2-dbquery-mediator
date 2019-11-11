package fi.metatavu.wso2.dbquery;

import javax.xml.namespace.QName;

import org.apache.synapse.SynapseConstants;

/**
 * Constants for dbquery mediator
 * 
 * @author Antti Leppä
 */
public class DbQueryConstants {

  public static final String TAG_NAME = "dbquery";
  public static final String RESULT_ROOT = "DBQueryResult";
  public static final String RESULT_ROW = "row";
  public static final String RESULT_NAME_ATTR = "name";
  public static final QName DB_SELECT_QNAME = new QName(SynapseConstants.SYNAPSE_NAMESPACE, TAG_NAME);
  public static final String NAMESPACE_URI = "http://wso2.metatavu.io/mediators/db/dbquery";
  public static final String NAMESPACE_PREFIX = "dbq";
  public static final String RESULT_PROPERTY = "DB_QUERY_RESULT";
  
}
