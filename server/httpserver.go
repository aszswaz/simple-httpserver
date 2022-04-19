package server

import (
	"fmt"
	"net"
	"simple-server/options"
	"simple-server/utils"
	"strconv"
)

// Start 启动 HTTP 服务器
func Start() {
	fmt.Println("listen port:", options.Port)

	// 监听端口
	server, err := net.Listen("tcp", ":"+strconv.Itoa(options.Port))
	if err != nil {
		utils.PrintErr(err)
		return
	}
	for {
		client, err := server.Accept()
		if err != nil {
			utils.PrintErr(err)
			break
		}
		go handlerConn(client)
	}
}

// handler 处理客户端的连接
func handlerConn(client net.Conn) {
	buf := make([]byte, 8192)
	l, err := client.Read(buf)
	if err != nil {
		utils.PrintErr(err)
		return
	}
	fmt.Print(string(buf[:l]))
}
