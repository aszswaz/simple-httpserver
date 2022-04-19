package cn.aszswaz.simplehttpserver.controllers;

import cn.aszswaz.simplehttpserver.entity.vo.EncodeVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.StringUtils.hasLength;
import static cn.aszswaz.simplehttpserver.config.Container.options;

/**
 * @author aszswaz
 * @createTime 2022-04-02 16:50:23
 * @ide IntelliJ IDEA
 */
@RestController
@SuppressWarnings("JavaDoc")
public class SimpleController {
    private final ObjectMapper jsonMapper;

    @Autowired
    public SimpleController(ObjectMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    /**
     * 回声
     */
    @RequestMapping(value = "ping")
    public String ping(@RequestBody(required = false) String content) {
        if (options().isVerbose()) {
            System.out.println(">>> Request body:");
            System.out.println(content);
        }
        return hasLength(content) ? content : "Hello World";
    }

    /**
     * 随机字符串
     */
    @GetMapping(value = "random")
    public String random(@RequestParam(value = "length") int length, HttpServletRequest request, HttpServletResponse response) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int code = (int) (97 + Math.random() * (122 - 97 + 1));
            builder.append((char) code);
        }

        String body = builder.toString();
        if (options().isVerbose()) {
            System.out.println(">>> Request body:");
            System.out.println(body);
        }
        response.setContentType(request.getContentType());
        return body;
    }

    /**
     * 转换文本为指定的编码
     */
    @PostMapping(value = "encode")
    public void encode(@RequestBody @NotNull String body, @NotNull HttpServletResponse response) throws IOException {
        if (options().isVerbose()) {
            System.out.println(">>> Request body:");
            System.out.println(body);
        }
        EncodeVO encode = this.jsonMapper.readValue(body, EncodeVO.class);

        response.setCharacterEncoding(encode.getCharset());
        response.setContentType("text/plain");
        response.getOutputStream().write(encode.getText().getBytes(encode.getCharset()));
        if (options().isVerbose()) {
            System.out.println("<<< Response body");
            System.out.println(encode.getText());
        }
    }

    /**
     * 延迟响应请求
     */
    @RequestMapping(value = "delay")
    public String delay(@RequestParam(value = "sleep", defaultValue = "1000") int sleep) throws InterruptedException {
        Thread.sleep(sleep);
        return "Hello World";
    }
}
