package base;

import implementation.FruitItem;
import interfaces.Basket;
import interfaces.FruitBasket;
import interfaces.Item;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import enumTypes.FruitEnum;
import exceptions.BigBasketInvalidQuantityException;
import exceptions.BigBasketNotAllowedFruitException;

public class MyBasket implements Basket,FruitBasket{
	
	private Map<String, Item> myBasket = new HashMap<String, Item>();

	public Map<String,Item> getInnerBasket() {
		return myBasket;
	} 

	public MyBasket(){
		myBasket = populateMyBasket(myBasket);	
	}



	private  Map<String, Item>  populateMyBasket(Map<String, Item> myBasket){


		Item apple = new FruitItem(1,new BigDecimal(100),String.valueOf(FruitEnum.Apple));
		Item orange = new FruitItem(1,new BigDecimal(101), String.valueOf(FruitEnum.Orange));
		Item grapes = new FruitItem(1,new BigDecimal(102), String.valueOf(FruitEnum.Grapes));

		myBasket = addItem(apple, myBasket);
		myBasket = addItem(orange, myBasket);
		myBasket = addItem(grapes, myBasket);

		return 	myBasket;
	}

	/* (non-Javadoc)
	 * @see interfaces.FruitBasket#checkIfValidFruit(interfaces.Item)
	 */
	@Override
	public void checkIfValidFruit(Item item)
			throws BigBasketNotAllowedFruitException {
		boolean validFruit= false;

		for( FruitEnum FruitEnum : FruitEnum.values()){

			if(FruitEnum.toString().equals(item.getName()))
				validFruit = true;

		}
		if(!validFruit) throw new BigBasketNotAllowedFruitException("The item is not a valid fruit");

	}

	/* (non-Javadoc)
	 * @see interfaces.Basket#addItem(interfaces.Item, java.util.Map)
	 */
	@Override
	public Map<String, Item> addItem(Item item, Map<String, Item> myBasketTemp) {
		if(myBasketTemp.containsKey(item.getName())){

			int qty = item.getQuantity() +  myBasketTemp.get(item.getName()).getQuantity();
			item.setQuantity(qty);
		}
		myBasketTemp.put(item.getName(), item);

		return myBasketTemp;

	}

	/* (non-Javadoc)
	 * @see interfaces.Basket#calculateTotal(java.util.Map)
	 */
	@Override
	public BigDecimal calculateTotal(Map<String, Item> myBasket) {
		BigDecimal total = new BigDecimal(0.0);

		for( String itemName : myBasket.keySet()){
		 total = total.add(myBasket.get(itemName).getPrice().multiply(new BigDecimal(myBasket.get(itemName).getQuantity())));
		}


		return total;
	}

	
	/* (non-Javadoc)
	 * @see interfaces.Basket#checkValidItemQuantity(interfaces.Item)
	 */
	@Override
	public void checkValidItemQuantity(Item item)
			throws BigBasketInvalidQuantityException {
		if(item.getQuantity() <= 0) throw new BigBasketInvalidQuantityException("The item quantity is invalid");

	}

	/* (non-Javadoc)
	 * @see interfaces.Basket#size()
	 */
	@Override
	public int size() {
		return getInnerBasket().size();
	}
}

