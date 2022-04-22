package cn.aszswaz.simplehttpserver.controllers;

import cn.aszswaz.simplehttpserver.config.RandomType;
import cn.aszswaz.simplehttpserver.entity.Options;
import cn.aszswaz.simplehttpserver.entity.vo.EncodeVO;
import cn.aszswaz.simplehttpserver.service.RandomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
    private final Options options;
    private final RandomService rService;

    @Autowired
    public SimpleController(ObjectMapper jsonMapper, RandomService rService) {
        this.jsonMapper = jsonMapper;
        this.options = options();
        this.rService = rService;
    }

    /**
     * 回声
     */
    @RequestMapping(value = "ping")
    public void ping(
            @RequestBody(required = false) String content,
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response
    ) throws IOException {
        this.printRequest(content);
        String resBody;

        if (hasLength(content)) {
            response.setContentType(request.getContentType());
            resBody = content;
        } else {
            resBody = "Hello World";
            response.setContentType("text/plain");
        }
        this.printResponse(resBody);

        byte[] buff = resBody.getBytes(StandardCharsets.UTF_8);
        response.setContentLength(buff.length);
        response.getOutputStream().write(buff);
    }

    /**
     * 随机字符串
     */
    @GetMapping(value = "random")
    public String random(
            @RequestParam(value = "length", defaultValue = "100") int length,
            @RequestParam(value = "type", defaultValue = "TEXT") @NotNull RandomType type
    ) {
        String body;
        switch (type) {
            case TEXT:
                body = this.rService.text(length);
                break;
            case JSON:
                body = this.rService.json(length);
                break;
            default:
                throw new RuntimeException();
        }
        this.printResponse(body);
        return body;
    }

    /**
     * 转换文本为指定的编码
     */
    @PostMapping(value = "encode")
    public void encode(@RequestBody @NotNull String body, @NotNull HttpServletResponse response) throws IOException {
        this.printRequest(body);
        EncodeVO encode = this.jsonMapper.readValue(body, EncodeVO.class);

        response.setCharacterEncoding(encode.getCharset());
        response.setContentType("text/plain");
        response.getOutputStream().write(encode.getText().getBytes(encode.getCharset()));
        this.printResponse(encode.getText());
    }

    /**
     * 延迟响应请求
     */
    @RequestMapping(value = "delay")
    public String delay(@RequestParam(value = "sleep", defaultValue = "1000") int sleep) throws InterruptedException {
        Thread.sleep(sleep);
        String response = "Hello World";
        this.printResponse(response);
        return response;
    }

    private void printRequest(String request) {
        if (this.options.isVerbose()) {
            System.out.println(">>> Request body:");
            System.out.println(request);
        }
    }

    private void printResponse(String response) {
        if (this.options.isVerbose()) {
            System.out.println("<<< Response body");
            System.out.println(response);
        }
    }
}
