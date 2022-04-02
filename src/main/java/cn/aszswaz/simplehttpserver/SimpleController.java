package cn.aszswaz.simplehttpserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aszswaz
 * @createTime 2022-04-02 16:50:23
 * @ide IntelliJ IDEA
 */
@RestController
@SuppressWarnings("JavaDoc")
public class SimpleController {
    /**
     * 回声
     */
    @RequestMapping(value = "ping")
    public String ping() {
        return "Hello World";
    }

    /**
     * 随机字符串
     */
    @GetMapping(value = "random")
    public String random(int length) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int code = (int) (97 + Math.random() * (122 - 97 + 1));
            builder.append((char) code);
        }

        return builder.toString();
    }
}
