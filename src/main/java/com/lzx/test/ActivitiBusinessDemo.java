package com.lzx.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import java.util.List;

/**
 * *
 *
 * @author: lzx@4229303
 * @create: 03-29
 **/


public class ActivitiBusinessDemo {


    /**
     * 添加业务Key
     */
    @Test
    public void addBusinessKey() {


        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RuntimeService runtimeService = processEngine.getRuntimeService();

        //myEvection==>bpmn的process id="myEvection"
        //businessKey==>实际的出差申请单的id
        ProcessInstance instance = runtimeService
                .startProcessInstanceByKey("myEvection", "1001");

        System.out.println(instance.getBusinessKey());
    }


    /**
     * 部署的流程全部挂起/激活
     */
    @Test
    public void suspendAllProcessInstance() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();


        RepositoryService repositoryService = processEngine.getRepositoryService();
        //查询流程部署对象
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("myEvection")
                .singleResult();

        //获取流程的状态
        boolean suspended = processDefinition.isSuspended();

        //流程的ID
        String processDefinitionId = processDefinition.getId();

        //如果是挂起状态就激活
        //如果是激活状态就挂起
        if (suspended) {
            //根据ID激活流程，参数1：流程的ID，参数2：是否激活，参数3：激活时间
            repositoryService.activateProcessDefinitionById(processDefinitionId, true, null);
            System.out.println(processDefinitionId + "已激活");
        } else {
            repositoryService.suspendProcessDefinitionById(processDefinitionId, true, null);
            System.out.println(processDefinitionId + "已挂起");
        }
    }


    /**
     * 挂起/激活单个流程实例
     */
    @Test
    public void suspendSingleProcessInstance(){

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RuntimeService runtimeService = processEngine.getRuntimeService();

        ProcessInstance processInstance = runtimeService
                .createProcessInstanceQuery()
                .processInstanceId("35001")
                .singleResult();

        Boolean suspended=processInstance.isSuspended();

        String instanceId=processInstance.getProcessInstanceId();

        if (suspended){
            runtimeService.activateProcessInstanceById(instanceId);
            System.out.println(instanceId+"已激活");
        }else {
            runtimeService.suspendProcessInstanceById(instanceId);
            System.out.println(instanceId+"已挂起");
        }

    }

    @Test
    public void queryProcessInstance() {
        // 流程定义key
        String processDefinitionKey = "evection";
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取RunTimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();
        List<ProcessInstance> list = runtimeService
                .createProcessInstanceQuery()
                .processDefinitionKey(processDefinitionKey)//
                .list();

        for (ProcessInstance processInstance : list) {
            System.out.println("----------------------------");
            System.out.println("流程实例id："
                    + processInstance.getProcessInstanceId());
            System.out.println("所属流程定义id："
                    + processInstance.getProcessDefinitionId());
            System.out.println("是否执行完成：" + processInstance.isEnded());
            System.out.println("是否暂停：" + processInstance.isSuspended());
            System.out.println("当前活动标识：" + processInstance.getActivityId());
        }
    }


}
