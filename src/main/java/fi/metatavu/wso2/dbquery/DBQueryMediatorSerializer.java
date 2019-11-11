package fi.metatavu.wso2.dbquery;

import org.apache.axiom.om.OMElement;
import org.apache.synapse.Mediator;
import org.apache.synapse.config.xml.AbstractDBMediatorSerializer;

/**
 * Mediator serializer for dbquery mediator
 * 
 * @author Antti Leppä
 */
public class DBQueryMediatorSerializer extends AbstractDBMediatorSerializer {
  
  @Override
  protected OMElement serializeSpecificMediator(Mediator mediator) {
    if (!(mediator instanceof DbQueryMediator)) {
      handleException(String.format("Unexpected mediator %s when trying to serialize dbquery mediator", mediator.getType()));
    }
    
    DbQueryMediator dbQueryediator = (DbQueryMediator) mediator;
    OMElement element = fac.createOMElement(DbQueryConstants.TAG_NAME, synNS);
    saveTracingState(element, mediator);
    serializeDBInformation(dbQueryediator, element);
    return element;
  }

  @Override
  public String getMediatorClassName() {
    return DbQueryMediator.class.getName();
  }
}
