package cn.aszswaz.simplehttpserver.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author aszswaz
 * @createTime 2022-04-07 12:27:29
 * @ide IntelliJ IDEA
 */
@SuppressWarnings("JavaDoc")
public class EncodeVO {
    @JsonProperty(value = "charset")
    private String charset;
    @JsonProperty(value = "text")
    private String text;

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
