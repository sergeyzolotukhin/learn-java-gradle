package ua.in.szolotukhin.flowable.debug.engine.impl.cmd;

import org.flowable.engine.impl.cmd.StartProcessInstanceCmd;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;

import java.util.Map;

@SuppressWarnings("unchecked")
public class CreateProcessInstanceCmd<T> extends StartProcessInstanceCmd<T> {
    public CreateProcessInstanceCmd(String processDefinitionKey, String processDefinitionId, String businessKey, Map variables) {
        super(processDefinitionKey, processDefinitionId, businessKey, variables);
    }

    protected ProcessInstance startProcessInstance(ProcessDefinition processDefinition) {
        return processInstanceHelper.createProcessInstance(processDefinition, businessKey, processInstanceName,
                overrideDefinitionTenantId, predefinedProcessInstanceId, variables, transientVariables, callbackId, callbackType, false);
    }
}
