package io.github.kimmking.gateway.filter;

import io.github.kimmking.gateway.outbound.okhttp.OkhttpOutboundHandler;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;

// TODO 是否应该改成责任链模式
public class HeaderFilter implements HttpRequestFilter {
    OkhttpOutboundHandler okhttpOutboundHandler;

    public HeaderFilter(OkhttpOutboundHandler handler) {
        this.okhttpOutboundHandler = handler;
    }

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        // 处理返回后的数据，加一个header
        FullHttpResponse response = okhttpOutboundHandler.doHandler(fullRequest, ctx);

        if (response != null) {
            response.headers().set("Hi", "HeyMan");

            handleResponse(fullRequest, ctx, response);
        }
    }

    private void handleResponse(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, final FullHttpResponse response) {
        if (fullRequest != null) {
            if (!HttpUtil.isKeepAlive(fullRequest)) {
                ctx.write(response).addListener(ChannelFutureListener.CLOSE);
            } else {
                ctx.write(response);
            }
        }
        ctx.flush();
    }
}
