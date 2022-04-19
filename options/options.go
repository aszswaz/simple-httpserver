package options

import (
	"flag"
)

var Port int

// init 该函数在程序初始化时，执行一次。解析命令行参数
func init() {
	port := *flag.Int("p", 8080, "服务器端口")
	// 解析命令行参数
	flag.Parse()
	Port = port
}
