package com.example.consulclientdemo;
/**
 * 服务注册和健康检测
 */

import com.ecwid.consul.v1.health.model.HealthService;
import com.google.common.net.HostAndPort;
import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.NotRegisteredException;
import com.orbitz.consul.model.agent.ImmutableRegCheck;
import com.orbitz.consul.model.agent.ImmutableRegistration;

import java.util.List;

public class ConsulInter {


    static Consul consul = Consul.builder().withHostAndPort(HostAndPort.fromString("202.120.167.198:8500")).build();
    /**
     * 服务注册
     */
    public static void serviceRegister() {
        AgentClient agent = consul.agentClient();

        //健康检测
        ImmutableRegCheck check = ImmutableRegCheck.builder().http("http://www.baidu.com/health").interval("5s").build();
        ImmutableRegistration.Builder builder = ImmutableRegistration.builder();
        builder.id("zzy1").name("zzyConsul").addTags("v1").address("www.baidu.com").addChecks(check);

        agent.register(builder.build());
    }

}