package com.lzx.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

/**
 * 使用默认方式创建数据库表
 *
 * @author ：CmaZa
 * @date ：Created in 2021/3/13 10:34
 */

public class TestCreate {


    @Test
    public void testCreateDbTable() {

        //默认配置
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //自定义配置
//        ProcessEngineConfiguration processEngineConfigurationFromResource = ProcessEngineConfiguration.
//                createProcessEngineConfigurationFromResource("activiti.cfg.xml","processEngineConfiguration");

          //获取流程引擎对象
//        ProcessEngine processEngine = processEngineConfigurationFromResource.buildProcessEngine();
        System.out.println(processEngine);


    }


}
