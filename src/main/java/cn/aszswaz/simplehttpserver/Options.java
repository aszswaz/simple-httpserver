package cn.aszswaz.simplehttpserver;

/**
 * 选项
 *
 * @author aszswaz
 * @createTime 2022-04-02 15:57:43
 * @ide IntelliJ IDEA
 */
@SuppressWarnings("JavaDoc")
public class Options {
    /**
     * 服务器绑定地址
     */
    private String host;
    /**
     * 服务器端口
     */
    private int port;
    /**
     * 静态文件目录
     */
    private String startDirectory;
    /**
     * 输出详细的请求和响应信息
     */
    private boolean verbose;
    /**
     * 启用 gzip 压缩
     */
    private boolean compression;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getStartDirectory() {
        return startDirectory;
    }

    public void setStartDirectory(String startDirectory) {
        this.startDirectory = startDirectory;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public boolean isCompression() {
        return compression;
    }

    public void setCompression(boolean compression) {
        this.compression = compression;
    }
}
