package es.codeurjc.deliveryservice.exception;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 2829664961387274080L;
	public BusinessException(String msg) {
        super(msg);
    }
}