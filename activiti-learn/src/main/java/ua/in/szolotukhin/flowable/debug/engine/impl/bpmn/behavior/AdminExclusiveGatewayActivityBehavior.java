package ua.in.szolotukhin.flowable.debug.engine.impl.bpmn.behavior;

import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.model.ExclusiveGateway;
import org.flowable.bpmn.model.FlowNode;
import org.flowable.bpmn.model.SequenceFlow;
import org.flowable.common.engine.api.delegate.event.FlowableEngineEventType;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.event.impl.FlowableEventBuilder;
import org.flowable.engine.impl.bpmn.behavior.ExclusiveGatewayActivityBehavior;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.springframework.util.Assert;
import ua.in.szolotukhin.flowable.debug.engine.impl.agenda.AdminTakeOutgoingSequenceFlowsOperation;

import static org.flowable.engine.impl.util.CommandContextUtil.getProcessEngineConfiguration;

@Slf4j
public class AdminExclusiveGatewayActivityBehavior extends ExclusiveGatewayActivityBehavior {
    @Override
    public void leave(DelegateExecution execution) {

        if (log.isDebugEnabled()) {
            log.debug("Leaving exclusive gateway '{}'", execution.getCurrentActivityId());
        }

        ExclusiveGateway exclusiveGateway = (ExclusiveGateway) execution.getCurrentFlowElement();

        dispatchEvent(execution, exclusiveGateway);

        String flow = (String) execution.getVariable(AdminTakeOutgoingSequenceFlowsOperation.REQUESTED_OUTGOING_SEQUENCE_FLOW_ID);
        Assert.hasText(flow, "Flow undefine");

        SequenceFlow outgoingSequenceFlow = findOutgoingSequenceFlow(exclusiveGateway, flow);

        execution.setCurrentFlowElement(outgoingSequenceFlow);

        bpmnActivityBehavior.performDefaultOutgoingBehavior((ExecutionEntity) execution);
    }

    private void dispatchEvent(DelegateExecution execution, ExclusiveGateway gateway) {
        if (getProcessEngineConfiguration() != null && getProcessEngineConfiguration().getEventDispatcher().isEnabled()) {
            getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(
                    FlowableEventBuilder.createActivityEvent(
                            FlowableEngineEventType.ACTIVITY_COMPLETED, gateway.getId(), gateway.getName(),
                            execution.getId(), execution.getProcessInstanceId(), execution.getProcessDefinitionId(),
                            gateway));
        }
    }

    private SequenceFlow findOutgoingSequenceFlow(FlowNode flowNode, String flowId) {
        return flowNode.getOutgoingFlows().stream()
                .filter(sequenceFlow -> flowId.equals(sequenceFlow.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "No outgoing sequence flow of element '" + flowNode.getId() + "' with id '" + flowId + "'"));
    }
}
