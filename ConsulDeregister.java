package com.example.consulclientdemo;
/**
 * 注销服务
 */
import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.health.model.Check;
import com.ecwid.consul.v1.health.model.HealthService;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.support.LogAppenderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class ConsulDeregister {

    ConsulClient consulClient;

    @RequestMapping(value = "/unregister/{id}",method = RequestMethod.POST)
    public String unregisterServiceAll(@PathVariable String id){

        List<HealthService> response = consulClient.getHealthServices(id,false,null).getValue();
        for(HealthService service : response){
            //创建一个用来剔除无效实例的ConsulClient，连接到无效实例注册的agent
            ConsulClient clearClient = new ConsulClient(service.getNode().getAddress(),8500);
            service.getChecks().forEach(check -> {
                if (check.getStatus() != Check.CheckStatus.PASSING){
                    Logger logger = Logger.getLogger(check.getServiceId());
                    logger.info("unregister:{}"+check.getServiceId());
                    clearClient.agentServiceDeregister(check.getServiceId());
                }
            });
        }
    return null;
    }
}
