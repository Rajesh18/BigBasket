package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import base.MyBasket;
import enumTypes.FruitEnum;
import exceptions.BigBasketInvalidQuantityException;
import exceptions.BigBasketNotAllowedFruitException;
import implementation.FruitItem;
import interfaces.Item;


public class BigBasketTest {


	MyBasket myBasket;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp() throws Exception {

		myBasket = new MyBasket();

	}



	@Test
	public final void testBasketNotNull() {
		assertNotNull(myBasket);
	}

	@Test
	public final void testBasketTotalValue() {
		Map<String, Item> innerBasket = myBasket.getInnerBasket();
		BigDecimal d = myBasket.calculateTotal(innerBasket);
		assertTrue(new BigDecimal(303).equals(d));
	}


	@Test
	public final void testWrongTotal() {
		
		Map<String, Item> innerBasket = myBasket.getInnerBasket();
		BigDecimal d = myBasket.calculateTotal(innerBasket);
		assertFalse(new BigDecimal(3).equals(d));

	}
	
	@Test
	public final void testBasketSize() {

		assertNotNull(myBasket);
		assertTrue(3 == myBasket.size());

	}

	@Test
	public final void testWrongSize() {

		assertNotNull(myBasket);
		assertFalse(20 == myBasket.size());

	}

	@Test
	public final void testBigBasketNotAllowedFruitException() throws BigBasketNotAllowedFruitException {

		exception.expect(BigBasketNotAllowedFruitException.class);
		exception.expectMessage("The item is not a valid fruit");
		Item wrongItem = new FruitItem(1,new BigDecimal(100),"Potato");
		myBasket.checkIfValidFruit(wrongItem);
		fail("No excpetion was thrown");	

	}

	@Test
	public final void testBigBasketInvalidQuantityException() throws BigBasketInvalidQuantityException {

		exception.expect(BigBasketInvalidQuantityException.class);
		exception.expectMessage("The item quantity is invalid");
		Item appleWithWrongQuantity = new FruitItem(-1,new BigDecimal(100),String.valueOf(FruitEnum.Apple));
		myBasket.checkValidItemQuantity(appleWithWrongQuantity);
		fail("No excpetion was thrown");	
	}

	@Test
	public final void testBigBasketValidQuantity() throws BigBasketInvalidQuantityException {

		Item apple = new FruitItem(1,new BigDecimal(100),String.valueOf(FruitEnum.Apple));
		myBasket.checkValidItemQuantity(apple);
		assertTrue("The fruit has valid quantity",true);
	}

	@Test
	public final void testBigBasketValidFruit() throws BigBasketNotAllowedFruitException {

		Item apple = new FruitItem(5,new BigDecimal(100),String.valueOf(FruitEnum.Apple));
		myBasket.checkIfValidFruit(apple);
		assertTrue("The fruit is valid",true);

	}

	@Test
	public final void testBigBasketAddAlreadyExistingItem(){
		Item apple = new FruitItem(5,new BigDecimal(100),String.valueOf(FruitEnum.Apple));
		myBasket.addItem(apple, myBasket.getInnerBasket());
		assertTrue(myBasket.getInnerBasket().get(String.valueOf(FruitEnum.Apple)).getQuantity()==6);
		assertTrue(myBasket.getInnerBasket().size() == 3);

	}

	
	@Test
	public final void testBigBasketAddAlreadyExistingItemNegative(){
		Item apple = new FruitItem(5,new BigDecimal(100),String.valueOf(FruitEnum.Apple));
		myBasket.addItem(apple, myBasket.getInnerBasket());
		assertFalse(myBasket.getInnerBasket().get(String.valueOf(FruitEnum.Apple)).getQuantity()==5);
		assertFalse(myBasket.getInnerBasket().size() == 4);

	}
	
	@Test
	public final void testBigBasketAddNewItem(){
		Item apple = new FruitItem(2,new BigDecimal(110),String.valueOf(FruitEnum.Peach));
		myBasket.addItem(apple, myBasket.getInnerBasket());
		assertTrue(myBasket.getInnerBasket().get(String.valueOf(FruitEnum.Peach)).getQuantity()==2);
		assertTrue(myBasket.getInnerBasket().size() == 4);

	}
	
	@Test
	public final void testBigBasketAddNewItemNegative(){
		Item apple = new FruitItem(2,new BigDecimal(110),String.valueOf(FruitEnum.Peach));
		myBasket.addItem(apple, myBasket.getInnerBasket());
		assertFalse(myBasket.getInnerBasket().size() == 3);

	}
}
