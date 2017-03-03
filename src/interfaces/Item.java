package interfaces;

import java.math.BigDecimal;

public interface Item {
	
	
	public int getQuantity();
	
	public void setQuantity(int quantity);
	
	public BigDecimal getPrice();
	
	public void setPrice(BigDecimal price); 
	
	public String getName();
	
	public void setName(String name);
	
	
}
