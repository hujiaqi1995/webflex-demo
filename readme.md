### Spring WebFlux 和 Router Functions 学习


#### Router Functions
ServerRequest < - - > HttpServletRequest
ServerResponse < - - > HttpServletResponse

**过程**

HandlerFunction (输入 ServerRequest 返回 ServerResponse)
-  RouterFunction (请求 URL 和 HandlerFunction 对应起来)
-  HttpHandler
- Server 处理


