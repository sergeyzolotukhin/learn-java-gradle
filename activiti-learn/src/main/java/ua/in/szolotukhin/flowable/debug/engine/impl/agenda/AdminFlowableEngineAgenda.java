package ua.in.szolotukhin.flowable.debug.engine.impl.agenda;

import org.flowable.common.engine.impl.interceptor.CommandContext;
import org.flowable.engine.impl.agenda.DebugFlowableEngineAgenda;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.flowable.engine.runtime.ProcessDebugger;

public class AdminFlowableEngineAgenda extends DebugFlowableEngineAgenda {

    AdminFlowableEngineAgenda(CommandContext commandContext, ProcessDebugger processDebugger) {
        super(commandContext, processDebugger);
    }

    @Override
    public void planTakeOutgoingSequenceFlowsOperation(ExecutionEntity execution, boolean evaluateConditions) {
        planOperation(new AdminTakeOutgoingSequenceFlowsOperation(commandContext, execution, evaluateConditions), execution);
    }
}
