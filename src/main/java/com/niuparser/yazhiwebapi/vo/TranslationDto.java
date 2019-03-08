package com.niuparser.yazhiwebapi.vo;

import java.io.Serializable;

/**
 * @author wylin
 * @date 2018/12/26 15:10
 */
public class TranslationDto implements Serializable {
    private String from;
    private String to;
    private String tgt_text;
    private String language;
    private String error_code;
    private String error_msg;
    private String src_text;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTgt_text() {
        return tgt_text;
    }

    public void setTgt_text(String tgt_text) {
        this.tgt_text = tgt_text;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public String getSrc_text() {
        return src_text;
    }

    public void setSrc_text(String src_text) {
        this.src_text = src_text;
    }

}
