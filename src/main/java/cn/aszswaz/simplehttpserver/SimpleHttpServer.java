package cn.aszswaz.simplehttpserver;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static cn.aszswaz.simplehttpserver.Container.options;

/**
 * 简单 HTTP 服务器，主要用于测试用途
 *
 * @author aszswaz
 * @createTime 2022-04-02 15:19:39
 * @ide IntelliJ IDEA
 */
@SuppressWarnings("JavaDoc")
@SpringBootApplication
public class SimpleHttpServer {
    public static void main(String[] args) {
        Container.init(args);
        Options options = options();

        SpringApplication application = new SpringApplication(SimpleHttpServer.class);

        Map<String, Object> configMap = new HashMap<>();
        configMap.put("server.address", options.getHost());
        configMap.put("server.port", options.getPort());
        configMap.put("spring.web.resources.static-locations", "file:" + options.getStartDirectory());
        configMap.put("server.compression.enabled", options.isCompression());
        application.setDefaultProperties(configMap);

        application.run();
    }
}
