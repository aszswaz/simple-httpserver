package cn.aszswaz.simplehttpserver.service;

/**
 * 随机文本
 *
 * @author aszswaz
 * @createTime 2022-04-22 15:05:50
 * @ide IntelliJ IDEA
 */
@SuppressWarnings("JavaDoc")
public interface RandomService {
    /**
     * 随机普通文本
     */
    String text(int length);

    /**
     * 随机 json
     */
    String json(int length);
}
