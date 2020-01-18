package ua.in.szolotukhin.flowable.debug.engine.impl.agenda;

import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.model.FlowNode;
import org.flowable.bpmn.model.SequenceFlow;
import org.flowable.common.engine.impl.interceptor.CommandContext;
import org.flowable.engine.impl.agenda.TakeOutgoingSequenceFlowsOperation;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;

import java.util.Optional;

@Slf4j
public class AdminTakeOutgoingSequenceFlowsOperation extends TakeOutgoingSequenceFlowsOperation {
    private static final String FLOW_NOT_FOUND = "No outgoing sequence flow of element '%s' with id '%s'";

    public static final String REQUESTED_OUTGOING_SEQUENCE_FLOW_ID = "requestedOutgoingSequenceFlowId";

    AdminTakeOutgoingSequenceFlowsOperation(
            CommandContext commandContext, ExecutionEntity execution, boolean evaluateConditions) {

        super(commandContext, execution, evaluateConditions);
    }

    @Override
    protected void leaveFlowNode(FlowNode flowNode) {
        Optional<String> flowId = requestedOutgoingSequenceFlowId();

        if (flowId.isPresent()) {
            SequenceFlow flow = findOutgoingSequenceFlow(flowNode, flowId.get());

            execution.setCurrentFlowElement(flow);
            execution.setActive(false);

            agenda.planContinueProcessOperation(execution);
        } else {
            super.leaveFlowNode(flowNode);
        }
    }

    private Optional<String> requestedOutgoingSequenceFlowId() {
        return Optional.ofNullable(
                (String) execution.getVariable(REQUESTED_OUTGOING_SEQUENCE_FLOW_ID));
    }

    private SequenceFlow findOutgoingSequenceFlow(FlowNode flowNode, String flowId) {
        return flowNode.getOutgoingFlows().stream()
                .filter(sequenceFlow -> flowId.equals(sequenceFlow.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format(FLOW_NOT_FOUND, flowNode.getId(), flowId)));
    }
}
