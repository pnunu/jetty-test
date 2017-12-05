# jetty-test
jetty 练习

jetty 8 中设置参数字节长度用
```
server.setAttribute("org.eclipse.jetty.server.Request.maxFormContentSize", 2048 * 1024);  // 设置字节长度
```
