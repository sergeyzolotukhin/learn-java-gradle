package ua.in.szolotukhin.flowable.debug.engine.impl.agenda;

import org.flowable.common.engine.impl.interceptor.CommandContext;
import org.flowable.engine.FlowableEngineAgenda;
import org.flowable.engine.impl.agenda.DebugFlowableEngineAgendaFactory;

public class AdminFlowableEngineAgendaFactory extends DebugFlowableEngineAgendaFactory {
    @Override
    public FlowableEngineAgenda createAgenda(CommandContext commandContext) {
        return new AdminFlowableEngineAgenda(commandContext, debugger);
    }


}
