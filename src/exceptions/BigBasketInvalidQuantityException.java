package exceptions;

@SuppressWarnings("serial")
public class BigBasketInvalidQuantityException extends Exception{

	public BigBasketInvalidQuantityException(String message) {
		super(message);
	}
}
