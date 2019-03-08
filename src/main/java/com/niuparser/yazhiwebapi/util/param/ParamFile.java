package com.niuparser.yazhiwebapi.util.param;

import java.io.File;

/**
 * 文件传输实体
 * @author 87951
 *
 */
public class ParamFile {

	private File file;
	
	private String src_text;
	

	public String getSrc_text() {
		return src_text;
	}

	public void setSrc_text(String src_text) {
		this.src_text = src_text;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	
	
}
