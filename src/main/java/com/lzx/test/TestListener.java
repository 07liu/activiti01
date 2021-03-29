package com.lzx.test;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

/**
 * *
 *
 * @author: lzx@4229303
 * @create: 03-29
 **/


public class TestListener {


    /**
     * 启动流程
     */
    @Test
    public void startProcess(){

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();

        Deployment deploy = repositoryService.createDeployment()
                .name("testListener")
                .addClasspathResource("bpmn/demo-listen.bpmn")
                .deploy();

        System.out.println(deploy.getId());
        System.out.println(deploy.getName());



    }


    /**
     *
     */
    @Test
    public void startDemoListener(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RuntimeService runtimeService = processEngine.getRuntimeService();

        runtimeService.startProcessInstanceByKey("testListener");
    }




}
