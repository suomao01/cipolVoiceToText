package com.niuparser.yazhiwebapi.util.result;

public enum ResultCodeNlp {
	/** 成功 */
	SUCCESS(200, "成功"),
	
	/** 没有登录 */
	NOT_LOGIN(400, "没有登录"),
	
	/** 发生异常 */
	EXCEPTION(401, "发生异常"),
	
	/** 系统错误 */
	SYS_ERROR(402, "系统错误"),
	
	/** 参数错误 */
	PARAMS_ERROR(403, "参数错误 "),
	
	/** 不支持或已经废弃 */
	NOT_SUPPORTED(410, "不支持或已经废弃"),
	
	/** AuthCode错误 */
	INVALID_AUTHCODE(444, "无效的AuthCode"),
 
	/** 太频繁的调用 */
	TOO_FREQUENT(445, "太频繁的调用"),
	
	/** 未知的错误 */
	UNKNOWN_ERROR(499, "未知错误");
	
	private String msg;
	private int code;

	private ResultCodeNlp(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getMsg() {
		return this.msg;
	}

	public int getCode() {
		return this.code;
	}
}
