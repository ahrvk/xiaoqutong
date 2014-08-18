package com.xiaoqu.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
/**
 * action抽象类
 * @author Administrator
 *
 */
public abstract class AbstractSuperAct {

	private static final long serialVersionUID = 1389199639575526640L;
//	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 请求成功
	 */
	protected static final int STATE_1 = 1;
	/**
	 * 请求失败
	 */
	protected static final int STATE_2 = 2;
	/**
	 * 请求获取数据为空
	 */
	protected static final int STATE_3 = 3;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
	this.request = request;
	this.response = response;
	this.session = request.getSession();
	}
	/**
	 * 向浏览器输出字符串（字节流）
	 * @param str 字符串
	 */
	protected void outWrite(String str) {
		try {
			response.getOutputStream().write(str.getBytes(), 0,
					str.getBytes().length);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 向浏览器输出字符串（字符流）
	 * @param str  字符串
	 * @param format  编码格式
	 */
	protected void outPrint(String str, String format) {
	 	response.setCharacterEncoding(format);
        response.setContentType("text/html;charset="+format);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Expires", "0");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
	 }
	/**
	 * 通过key取session中数据
	 * @param key 
	 * @return
	 * @throws ServicesException
	 */
	public Object getObjInSession(String key) {
		if(this.session==null )
			throw new RuntimeException("session is null");
		return this.session.getAttribute(key);
	}
	/**
	 * 将对象以json格式输出
	 * @param o
	 */
	protected void outPrint(Object o) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Expires", "0");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(JSON.toJSONString(o, SerializerFeature.DisableCircularReferenceDetect));
//            out.print(JSON.toJSONStringWithDateFormat(o, DATE_FORMAT, SerializerFeature.WriteDateUseDateFormat,SerializerFeature.DisableCircularReferenceDetect));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
                out.close();
        }
	}
	/**
	 * 将请求参数解析到map集合中
	 * @return
	 */
	protected Map<String,String> requestParameterToMap(){
		Map<String,String> map = new HashMap<String, String>();
		Enumeration<?> e=request.getParameterNames();
		while(e.hasMoreElements()){
			String key = (String) e.nextElement();
			String value = request.getParameter(key);
			map.put(key, value);
		}
		return map;
	}
	/**
	 * 将数据封装为客户端需要的格式
	 * @param date   数据
	 * @param state  状态
	 * @param msg    消息
	 * @return
	 */
	protected Map<String,Object> encapsulationResult(Object date,Integer state,String msg){
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("date",date );
		result.put("state",state );
		result.put("msg", msg);
		return result;
	}
}
