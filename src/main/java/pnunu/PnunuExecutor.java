package pnunu;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class PnunuExecutor implements ApplicationContextAware {

    public static ApplicationContext applicationContext;


    private int port = 8866;

    public void setPort(int port) {
        this.port = port;
    }

    // ---------------------------------- job server ------------------------------------
    Server server = null;

    public void start() throws Exception {

        Thread executorThread = new Thread(new Runnable() {
            public void run() {
                server = new Server(port);
                server.setAttribute("org.eclipse.jetty.server.Request.maxFormContentSize", 2048 * 1024);  // 设置字节长度

                // handler
                HandlerCollection handlerc = new HandlerCollection();
                handlerc.setHandlers(new Handler[]{new PnunuExecutorHandler()});
                server.setHandler(handlerc);

                try {
                    server.start();
                    server.join();  // block until thread stopped
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        executorThread.start();
    }

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        PnunuExecutor.applicationContext = applicationContext;

    }

}
