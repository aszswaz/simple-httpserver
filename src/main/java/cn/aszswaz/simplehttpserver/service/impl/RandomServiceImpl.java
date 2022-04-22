package cn.aszswaz.simplehttpserver.service.impl;

import cn.aszswaz.simplehttpserver.service.RandomService;
import org.springframework.stereotype.Service;

/**
 * 随机文本
 *
 * @author aszswaz
 * @createTime 2022-04-22 15:06:24
 * @ide IntelliJ IDEA
 */
@Service
@SuppressWarnings("JavaDoc")
public class RandomServiceImpl implements RandomService {
    /**
     * 随机普通文本
     */
    @Override
    public String text(int length) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int code = (int) (97 + Math.random() * (122 - 97 + 1));
            builder.append((char) code);
        }

        return builder.toString();
    }

    /**
     * 随机 JSON
     */
    @Override
    public String json(int length) {
        return null;
    }
}
