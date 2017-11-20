package storecounter;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class StoreCounterFrameTest {

	@Test
	public void testWarning() {
		StoreCounterFrame frame = new StoreCounterFrame();
		frame.warning("testtext", Color.BLUE);
		Assert.assertEquals("testtext", frame.getWarningField().getText());	
		Assert.assertEquals(Color.BLUE, frame.getAmount().getBackground());	
	}

	@Test
	public void testBuyProduct() {
		StoreCounterFrame frame = new StoreCounterFrame();
		Map<Integer, Product> products_Map = new Products().getProducts();
		Map<Integer, Product> boughtProducts_Map = new HashMap<>();
		frame.setBoughtProducts_Map(boughtProducts_Map);		
		
		frame.getBoughtProducts_Map().put(new Integer(1), products_Map.get(new Integer( 1)));
		frame.buyProduct(new Integer(1), 5.00);
		
		frame.buyProduct(new Integer(6), 66.35);
		
		assertEquals(5.00, boughtProducts_Map.get(new Integer(1)).getAmount(), 0.005);	
		assertEquals(66.35, boughtProducts_Map.get(new Integer(6)).getAmount(), 0.005);	
	}
	
	@Test
	public void testCalculateTotalPriceForAProduct() {
		StoreCounterFrame frame = new StoreCounterFrame();
		Map<Integer, Product> products_Map = new Products().getProducts();
		Map<Integer, Product> boughtProducts_Map = new HashMap<>();
		frame.setBoughtProducts_Map(boughtProducts_Map);		
		
		boughtProducts_Map.put(new Integer( 1), products_Map.get(new Integer( 1)));
		boughtProducts_Map.put(new Integer( 6), products_Map.get(new Integer( 6)));
		boughtProducts_Map.put(new Integer(10), products_Map.get(new Integer(10)));
		boughtProducts_Map.put(new Integer(12), products_Map.get(new Integer(12)));
		
		boughtProducts_Map.get(new Integer( 1)).setAmount( 5.00);//price per unit: 2.60
		boughtProducts_Map.get(new Integer( 6)).setAmount(66.35);//price per unit: 0.86
		boughtProducts_Map.get(new Integer(10)).setAmount( 6.00);//price per unit: 7.35
		boughtProducts_Map.get(new Integer(12)).setAmount( 6.00);//price per unit: 7.45
		
		assertEquals(13.00, frame.calculateTotalPriceForAProduct(frame.getBoughtProducts_Map().get(new Integer( 1))), 0.005);	
		assertEquals(57.06, frame.calculateTotalPriceForAProduct(frame.getBoughtProducts_Map().get(new Integer( 6))), 0.005);	
		assertEquals(22.05, frame.calculateTotalPriceForAProduct(frame.getBoughtProducts_Map().get(new Integer(10))), 0.005);	
		assertEquals(29.80, frame.calculateTotalPriceForAProduct(frame.getBoughtProducts_Map().get(new Integer(12))), 0.005);	
	}

	@Test
	public void testCalculateTotal() {
		StoreCounterFrame frame = new StoreCounterFrame();
		Map<Integer, Product> products_Map = new Products().getProducts();
		Map<Integer, Product> boughtProducts_Map = new HashMap<>();
		frame.setBoughtProducts_Map(boughtProducts_Map);
		
		boughtProducts_Map.put(new Integer( 1), products_Map.get(new Integer( 1)));
		boughtProducts_Map.put(new Integer( 6), products_Map.get(new Integer( 6)));
		boughtProducts_Map.put(new Integer(12), products_Map.get(new Integer(12)));
		
		boughtProducts_Map.get(new Integer( 1)).setAmount( 5.00);//price per unit: 2.60
		boughtProducts_Map.get(new Integer( 6)).setAmount(66.35);//price per unit: 0.86
		boughtProducts_Map.get(new Integer(12)).setAmount( 6.00);//price per unit: 7.45

		assertEquals(99.86, frame.calculateTotal(), 0.005);	
	}


}
