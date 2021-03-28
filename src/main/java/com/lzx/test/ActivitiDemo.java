package com.lzx.test;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipInputStream;

/**
 * @author ：CmaZa
 * @date ：Created in 2021/3/13 15:38
 */

public class ActivitiDemo {


    /**
     * 测试流程部署
     */
    @Test
    public void testDeployment() {
        //1.创建引擎流程对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.从流程引擎中获取RepositoryService对像
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //3.使用Service对象进行流程得部署
        Deployment deploy = repositoryService.createDeployment().name("出差申请")
                .addClasspathResource("bpmn/evection.bpmn")
                .addClasspathResource("bpmn/evection.png")
                .deploy();
        //4.输出部署信息
        System.out.println("流程部署ID：" + deploy.getId());
        System.out.println("流程部署名称：" + deploy.getName());

    }


    /**
     * 启动流程实例
     */
    @Test
    public void testStartProcess() {
        //1.创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.获取RuntimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //3.根据流程实例ID启动流程
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("myEvection");

        //4.输出内容
        System.out.println(instance.getName());
        System.out.println(instance.getId());
        System.out.println(instance.getProcessDefinitionId());
        System.out.println(instance.getProcessDefinitionName());
        System.out.println(instance.getActivityId());

    }


    /**
     * 查询当前个人待执行的任务
     */
    @Test
    public void testFindPersonalTaskList() {
//        任务负责人
        String assignee = "jack";
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        创建TaskService
        TaskService taskService = processEngine.getTaskService();
//        根据流程key 和 任务负责人 查询任务
        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey("myEvection") //流程Key
                .taskAssignee(assignee)//只查询该任务负责人的任务
                .list();

        for (Task task : list) {

            System.out.println("流程实例id：" + task.getProcessInstanceId());
            System.out.println("任务id：" + task.getId());
            System.out.println("任务负责人：" + task.getAssignee());
            System.out.println("任务名称：" + task.getName());
        }
    }

    /**
     * 完成任务
     */
    @Test
    public void competeTask() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = processEngine.getTaskService();

//        //获取jerry对应的任务
//        Task task = taskService.createTaskQuery()
//                .processDefinitionKey("myEvection")
//                .taskAssignee("zhangsan")
//                .singleResult();

       //获取jerry对应的任务
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("myEvection")
                .taskAssignee("jerry")
                .singleResult();


//        //完成jack得任务
//        Task task = taskService.createTaskQuery()
//                .processDefinitionKey("myEvection")
//                .taskAssignee("jack")
//                .singleResult();

        //完成rose得任务
//        Task task = taskService.createTaskQuery()
//                .processDefinitionKey("myEvection")
//                .taskAssignee("rose")
//                .singleResult();


//        System.out.println("流程实例id：" + task.getProcessInstanceId());
//        System.out.println("任务id：" + task.getId());
//        System.out.println("任务负责人：" + task.getAssignee());
//        System.out.println("任务名称：" + task.getName());

//        taskService.complete("2505");
        taskService.complete(task.getId());
    }


    @Test
    public void deployProcessZip() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();

        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("bpmn/evection.zip");

        ZipInputStream zipInputStream = new ZipInputStream(inputStream);

        Deployment deploy = repositoryService.createDeployment()
                .name("出差流程申请")
                .addZipInputStream(zipInputStream)
                .deploy();


        System.out.println("ID:" + deploy.getId());
        System.out.println("name:" + deploy.getName());

    }


    /**
     * 查询流程定义
     */
    @Test
    public void queryProcessDefinition() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();

        ProcessDefinitionQuery definitionQuery = repositoryService.createProcessDefinitionQuery();

        List<ProcessDefinition> definitionList = definitionQuery.processDefinitionKey("myEvection")
                .orderByProcessDefinitionVersion()
                .desc()
                .list();

        for (ProcessDefinition processDefinition : definitionList) {
            System.out.println("流程定义ID："+processDefinition.getId());
            System.out.println("流程定义名称人："+processDefinition.getName());
            System.out.println("流程定义Key："+processDefinition.getKey());
            System.out.println("流程定义Version："+processDefinition.getVersion());
            System.out.println("流程部署ID："+processDefinition.getDeploymentId());

        }
    }


    /**
     * 删除流程部署信息
     */
    @Test
    public void deleteDeployment(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();

//        repositoryService.deleteDeployment("2501");
    repositoryService.deleteDeployment("2501",true);
    }


    /**
     * 下载 资源文件
     */
    @Test
    public void getDeployment(){

    }
}
