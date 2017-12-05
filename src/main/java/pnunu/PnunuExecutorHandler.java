package pnunu;

import com.alibaba.fastjson.JSON;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class PnunuExecutorHandler extends AbstractHandler {

	private static Logger logger = LoggerFactory.getLogger(PnunuExecutorHandler.class);

	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletException {
	    long start = Calendar.getInstance().getTimeInMillis();
	    String code = request.getRequestURI();
	    code = code.substring(1, code.length());
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Map<String, Object> map = getMap(request);
		map.put("code", code);
		response.setContentType("application/json; charset=utf-8");
	    PrintWriter out = null;  
	    try {  
	        out = response.getWriter();  
	        out.print(JSON.toJSON(map));
            out.flush();
	    } catch (IOException e) {  
	        e.printStackTrace();
	    } finally {  
	        if (out != null) {
	            out.close();
	        }  
	    }
	    long runTime = Calendar.getInstance().getTimeInMillis() - start;
        logger.info("Run Time Total::" + runTime);
		System.out.println(runTime);
	}

	/**
	 * 请求参数处理
	 * @param request
	 * @return
	 */
	private Map<String, Object> getMap(HttpServletRequest request) {
	    Map<String, Object> map = new HashMap<String, Object>();
        Enumeration<String> enump =request.getParameterNames();  
        while (enump.hasMoreElements()) {  
            String paramName = enump.nextElement();  
            String paramValue = request.getParameter(paramName);  
            logger.info("paramName={},paramValue={}", paramName,paramValue);
            map.put(paramName, paramValue);  
        }
        return map;
	}
}
