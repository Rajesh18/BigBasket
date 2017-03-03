package interfaces;

import java.math.BigDecimal;
import java.util.Map;

import exceptions.BigBasketInvalidQuantityException;

/**
 * @author Rajesh
 *
 */

public interface Basket {

	/**
	 * This method is used to add an item into the basket. 
	 * If the item is already present in the basket then the quantity is updated accorndingly. 
	 * @param item
	 * @param myBasketTemp
	 * @return updated basket
	 */
	Map<String, Item> addItem(Item item , Map<String, Item> myBasketTemp);
	
	  /**
	 * This method is used to calculate the total value of items in the basket.
	 * 
	 * @param myBasket
	 * @return Total value of the basket 
	 */
	BigDecimal calculateTotal( Map<String, Item> myBasket);
	
	  /**
	 * This method is used ot check if the item quantity is valid or not. If it is invalid then the BigBasketInvalidQuantityException is thrown.
	 * 
	 * @param item
	 * @throws BigBasketInvalidQuantityException
	 */
	void checkValidItemQuantity(Item item) throws BigBasketInvalidQuantityException;
	 
	/**
	 * This method is used to calculate the size of the basket.
	 * 
	 * @return the size of the basket. i.e. the number of unique items in the basket.
	 */
	public int size();
}
