package fi.metatavu.wso2.dbquery;

import java.util.Properties;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.synapse.Mediator;
import org.apache.synapse.SynapseConstants;
import org.apache.synapse.config.xml.AbstractDBMediatorFactory;

/**
 * Mediator factory for dbquery mediator
 * 
 * @author Antti Leppä
 */
public class DbQueryMediatorFactory extends AbstractDBMediatorFactory {

  @Override
  public QName getTagQName() {
    return new QName(SynapseConstants.SYNAPSE_NAMESPACE, DbQueryConstants.TAG_NAME);
  }

  @Override
  protected Mediator createSpecificMediator(OMElement element, Properties properties) {
    DbQueryMediator mediator = new DbQueryMediator();
    buildDataSource(element, mediator);
    processStatements(element, mediator);
    return mediator;
  }

}
