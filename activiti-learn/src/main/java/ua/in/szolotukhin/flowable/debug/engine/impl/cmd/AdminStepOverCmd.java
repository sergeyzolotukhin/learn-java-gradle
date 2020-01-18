package ua.in.szolotukhin.flowable.debug.engine.impl.cmd;

import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.IntermediateCatchEvent;
import org.flowable.bpmn.model.UserTask;
import org.flowable.common.engine.impl.interceptor.CommandContext;
import org.flowable.engine.delegate.event.impl.FlowableEventBuilder;
import org.flowable.engine.impl.agenda.ContinueProcessOperation;
import org.flowable.engine.impl.cmd.NeedsActiveExecutionCmd;
import org.flowable.engine.impl.persistence.entity.EventSubscriptionEntity;
import org.flowable.engine.impl.persistence.entity.EventSubscriptionEntityManager;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;

import java.util.List;
import java.util.stream.Collectors;

import static org.flowable.common.engine.api.delegate.event.FlowableEngineEventType.ACTIVITY_SIGNALED;
import static org.flowable.engine.impl.event.MessageEventHandler.EVENT_HANDLER_TYPE;
import static org.flowable.engine.impl.util.CommandContextUtil.*;
import static ua.in.szolotukhin.flowable.debug.engine.impl.agenda.AdminTakeOutgoingSequenceFlowsOperation.REQUESTED_OUTGOING_SEQUENCE_FLOW_ID;

@Slf4j
public class AdminStepOverCmd extends NeedsActiveExecutionCmd<Object> {
    private final String flow;

    AdminStepOverCmd(String executionId, String flow) {
        super(executionId);
        this.flow = flow;
    }

    @Override
    protected Object execute(CommandContext commandContext, ExecutionEntity execution) {
        execution.setVariable(REQUESTED_OUTGOING_SEQUENCE_FLOW_ID, flow);

        FlowElement currentFlowElement = execution.getCurrentFlowElement();
        if (currentFlowElement instanceof UserTask) {
            executeUserTask(commandContext, execution);
        } else {
            executeContinueProcess(commandContext, execution);
        }

        if (currentFlowElement instanceof IntermediateCatchEvent) {
            getAgenda(commandContext).planOperation(
                    () -> executeWaitEvent(commandContext, execution));
        }

        return null;
    }

    private void executeContinueProcess(CommandContext commandContext, ExecutionEntity execution) {
        ContinueProcessOperation operation = new ContinueProcessOperation(
                commandContext, execution, true, false);

        getAgenda(commandContext).planOperation(operation, execution);
    }

    private void executeUserTask(CommandContext commandContext, ExecutionEntity execution) {
        fireActivitySignaledEvent(execution);
        getAgenda(commandContext).planTriggerExecutionOperation(execution);
    }

    private void executeWaitEvent(CommandContext commandContext, ExecutionEntity execution) {
        EventSubscriptionEntityManager subscriptionManager = getEventSubscriptionEntityManager(commandContext);

        List<EventSubscriptionEntity> subscriptions =
                subscriptionManager.findEventSubscriptionsByExecution(execution.getId())
                        .stream()
                        .filter(subscription -> EVENT_HANDLER_TYPE.equals(subscription.getEventType()))
                        .collect(Collectors.toList());

        subscriptions.forEach(
                subscription -> subscriptionManager.eventReceived(subscription, null, false));
    }

    private void fireActivitySignaledEvent(ExecutionEntity execution) {
        getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(
                FlowableEventBuilder.createSignalEvent(ACTIVITY_SIGNALED, execution.getCurrentActivityId(),
                        null,null,
                        execution.getId(), execution.getProcessInstanceId(), execution.getProcessDefinitionId()));
    }
}
