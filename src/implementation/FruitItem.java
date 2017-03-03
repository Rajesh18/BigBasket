package implementation;

import java.math.BigDecimal;

import interfaces.Item;

public class FruitItem implements Item {
	
	private int qty;
	private BigDecimal price;
	private String name;
	
	public FruitItem( int qty, BigDecimal price, String name){
		this.qty = qty;
		this.price = price;
		this.name = name;
	}

	@Override
	public int getQuantity() {
		return this.qty;
	}

	@Override
	public void setQuantity(int quantity) {
		this.qty = quantity;
	}

	@Override
	public BigDecimal getPrice() {
		
		return this.price;
	}

	@Override
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String getName() {
		
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + qty;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FruitItem other = (FruitItem) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (qty != other.qty)
			return false;
		return true;
	}

}
