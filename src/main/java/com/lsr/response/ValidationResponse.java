package com.lsr.response;

public class ValidationResponse {

	private String ValueSuccess;
    private String ValueError;
    private String Result;
    private String ProcessId;
    private String SuccMesgs;
    private String ErrMesgs;
    
    public String getValueSuccess() {
		return ValueSuccess;
	}

	public void setValueSuccess(String valueSuccess) {
		ValueSuccess = valueSuccess;
	}

	public String getValueError() {
		return ValueError;
	}

	public void setValueError(String valueError) {
		ValueError = valueError;
	}

	public String getResult() {
		return Result;
	}

	public void setResult(String result) {
		Result = result;
	}

	public String getProcessId() {
		return ProcessId;
	}

	public void setProcessId(String processId) {
		ProcessId = processId;
	}

	public String getSuccMesgs() {
		return SuccMesgs;
	}

	public void setSuccMesgs(String succMesgs) {
		SuccMesgs = succMesgs;
	}

	public String getErrMesgs() {
		return ErrMesgs;
	}

	public void setErrMesgs(String messages) {
		ErrMesgs = messages;
	}

	public Object[] getParams() {
		return Params;
	}

	public void setParams(Object[] params) {
		Params = params;
	}

	private Object[] Params;
    
	public ValidationResponse() {
		// TODO Auto-generated constructor stub
	}

}
