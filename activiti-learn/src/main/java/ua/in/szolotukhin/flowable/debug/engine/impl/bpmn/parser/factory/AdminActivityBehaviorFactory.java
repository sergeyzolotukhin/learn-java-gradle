package ua.in.szolotukhin.flowable.debug.engine.impl.bpmn.parser.factory;

import org.flowable.bpmn.model.ExclusiveGateway;
import org.flowable.engine.impl.bpmn.behavior.ExclusiveGatewayActivityBehavior;
import org.flowable.engine.impl.bpmn.parser.factory.DefaultActivityBehaviorFactory;
import ua.in.szolotukhin.flowable.debug.engine.impl.bpmn.behavior.AdminExclusiveGatewayActivityBehavior;

public class AdminActivityBehaviorFactory extends DefaultActivityBehaviorFactory {
    @Override
    public ExclusiveGatewayActivityBehavior createExclusiveGatewayActivityBehavior(ExclusiveGateway exclusiveGateway) {
        return new AdminExclusiveGatewayActivityBehavior();
    }

}
