package cn.aszswaz.simplehttpserver.config;

import cn.aszswaz.simplehttpserver.entity.Options;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

/**
 * 容器
 *
 * @author aszswaz
 * @createTime 2022-04-02 16:15:00
 * @ide IntelliJ IDEA
 */
@SuppressWarnings("JavaDoc")
public class Container {
    private static Options options;

    public static void init(String[] args) {
        parseOptions(args);
    }

    /**
     * 解析控制台传入参数
     */
    private static void parseOptions(String[] args) {
        final org.apache.commons.cli.Options options = new org.apache.commons.cli.Options();
        final Options serverOptions = new Options();

        try {
            Option port = new Option("p", "port", true, "服务器端口");
            Option host = new Option("H", "host", true, "服务器绑定地址");
            Option staticDirectory = new Option("dir", "static-directory", true, "静态文件目录");
            Option verbose = new Option("v", "verbose", false, "展示请求以及响应的详细信息");
            Option compression = new Option("c", "compression", false, "启用 gzip 压缩");
            Option help = new Option("h", "help", false, "输出帮助信息并退出");

            port.setType(Integer.TYPE);

            options.addOption(host);
            options.addOption(port);
            options.addOption(staticDirectory);
            options.addOption(verbose);
            options.addOption(compression);
            options.addOption(help);

            CommandLine cli = new DefaultParser().parse(options, args);

            if (cli.hasOption(help)) {
                new HelpFormatter().printHelp("simple-httpserver [options]", options);
                printAPIS();
                System.exit(0);
            }

            serverOptions.setHost(cli.getOptionValue(host, "localhost"));
            serverOptions.setPort(Integer.parseInt(cli.getOptionValue(port, "8080")));
            serverOptions.setVerbose(cli.hasOption(verbose));
            serverOptions.setStartDirectory(cli.getOptionValue(staticDirectory, System.getProperty("user.dir")));
            serverOptions.setCompression(cli.hasOption(compression));
            Container.options = serverOptions;
        } catch (ParseException e) {
            new HelpFormatter().printHelp("simple-httpserver [options] ...", options);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 打印可用的 API
     */
    private static void printAPIS() {
        System.out.println("Available APIs:");
        String format = "%-5s %-40s %-40s %s%n";
        System.out.printf(format, "METHOD", "URI", "REQUEST BODY", "COMMAND");
        System.out.printf(format, "ALL", "/ping", "", "回声，如果没有请求体，则返回 ping 字符串，如果有请求体，则返回请求体");
        System.out.printf(format, "GET", "/random?length=${length}", "", "获取指定长度的随机字符串");
        System.out.printf(format, "POST", "/encode", "{\"charset\": \"\", \"text\": \"\"}", "将 UTF-8 编码的字符集，转换为指定编码");
    }

    public static Options options() {
        return options;
    }
}
