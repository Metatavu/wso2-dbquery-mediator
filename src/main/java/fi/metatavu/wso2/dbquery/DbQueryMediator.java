package fi.metatavu.wso2.dbquery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.db.AbstractDBMediator;
import org.apache.synapse.mediators.db.Statement;

/**
 * Mediator for making simple multi-row queries into database
 * 
 * @author Antti Leppä
 */
public class DbQueryMediator extends AbstractDBMediator {

  /**
   * Processes a statement and stores the result into property
   */
  protected void processStatement(Statement statement, MessageContext context) {
    try (Connection connection = this.getDataSource().getConnection()) {
      PreparedStatement preparedStatement = getPreparedStatement(statement, connection, context);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        OMFactory omFactory = context.getEnvelope().getOMFactory();
        OMElement resultRoot = omFactory.createOMElement(DbQueryConstants.RESULT_ROOT, DbQueryConstants.NAMESPACE_URI, DbQueryConstants.NAMESPACE_PREFIX);
        
        while (resultSet.next()) {
          OMElement dbLookupResult = omFactory.createOMElement(DbQueryConstants.RESULT_ROW, DbQueryConstants.NAMESPACE_URI, DbQueryConstants.NAMESPACE_PREFIX);
          
          for (String propertyName : statement.getResultsMap().keySet()) {
            String columnValue = statement.getResultsMap().get(propertyName);
            OMElement result = omFactory.createOMElement(propertyName, DbQueryConstants.NAMESPACE_URI, DbQueryConstants.NAMESPACE_PREFIX);
            result.addAttribute(omFactory.createOMAttribute(DbQueryConstants.RESULT_NAME_ATTR, null, columnValue));
            result.setText(String.valueOf(resultSet.getObject(columnValue)));
            dbLookupResult.addChild(result);
          }
          
          resultRoot.addChild(dbLookupResult);
        }
        
        context.setProperty(DbQueryConstants.RESULT_PROPERTY, resultRoot);
      }
    } catch (SQLException e) {
      log.error(String.format("Failed to execute SQL %s into datasource %s", statement.getRawStatement(), getDSName()), e);
    }
  }

}
