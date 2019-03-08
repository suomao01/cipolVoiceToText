package com.niuparser.yazhiwebapi.vo;

/**
 * 参数VO
 *
 * @author 87951
 */
public class ResultVo {
    private Integer status;//状态

    private Object result;//结果

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public ResultVo(int status, Object result) {
        this.status = status;
        this.result = result;
    }
    public ResultVo() {
    }

}
