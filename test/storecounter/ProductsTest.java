package storecounter;

import org.junit.Assert;
import org.junit.Test;

public class ProductsTest {
	
	private Products products = new Products();

	@Test
	public void testGetAllProductNamesArray() {
		String [] productsNameArray = {"Lightbulb", "Lamp", "Pen", "Table", "Flour", "Milk", "Sugar", "Beer", "Parfume", "Toothpaste", "Shampoo", "Brush"};
		Assert.assertArrayEquals(productsNameArray, products.getAllProductNamesArray());
	}

	@Test
	public void testGetUnitByName() {
		Assert.assertEquals("piece" , products.getUnitByName("Lightbulb" ));
		Assert.assertEquals("piece" , products.getUnitByName("Lamp"      ));
		Assert.assertEquals("piece" , products.getUnitByName("Pen"       ));
		Assert.assertEquals("piece" , products.getUnitByName("Table"     ));
		Assert.assertEquals("kg"    , products.getUnitByName("Flour"     ));
		Assert.assertEquals("l"     , products.getUnitByName("Milk"      ));
		Assert.assertEquals("lb"    , products.getUnitByName("Sugar"     ));
		Assert.assertEquals("pt"    , products.getUnitByName("Beer"      ));
		Assert.assertEquals("pieces", products.getUnitByName("Parfume"   ));
		Assert.assertEquals("pieces", products.getUnitByName("Toothpaste"));
		Assert.assertEquals("pieces", products.getUnitByName("Shampoo"   ));
		Assert.assertEquals("pieces", products.getUnitByName("Brush"     ));
	}

	@Test
	public void testGetPricePerUnitByName() {
		Assert.assertEquals(String.valueOf( 2.60), products.getPricePerUnitByName("Lightbulb" ));
		Assert.assertEquals(String.valueOf(11.32), products.getPricePerUnitByName("Lamp"      ));
		Assert.assertEquals(String.valueOf( 1.12), products.getPricePerUnitByName("Pen"       ));
		Assert.assertEquals(String.valueOf(99.00), products.getPricePerUnitByName("Table"     ));
		Assert.assertEquals(String.valueOf( 1.12), products.getPricePerUnitByName("Flour"     ));
		Assert.assertEquals(String.valueOf( 0.86), products.getPricePerUnitByName("Milk"      ));
		Assert.assertEquals(String.valueOf( 2.05), products.getPricePerUnitByName("Sugar"     ));
		Assert.assertEquals(String.valueOf( 1.11), products.getPricePerUnitByName("Beer"      ));
		Assert.assertEquals(String.valueOf(12.60), products.getPricePerUnitByName("Parfume"   ));
		Assert.assertEquals(String.valueOf( 7.35), products.getPricePerUnitByName("Toothpaste"));
		Assert.assertEquals(String.valueOf( 5.60), products.getPricePerUnitByName("Shampoo"   ));
		Assert.assertEquals(String.valueOf( 7.45), products.getPricePerUnitByName("Brush"     ));
	}

	@Test
	public void testGetProductByName() {
		Assert.assertEquals(products.getProducts().get(new Integer( 1)), products.getProductByName("Lightbulb" ));
		Assert.assertEquals(products.getProducts().get(new Integer( 2)), products.getProductByName("Lamp"      ));
		Assert.assertEquals(products.getProducts().get(new Integer( 3)), products.getProductByName("Pen"       ));
		Assert.assertEquals(products.getProducts().get(new Integer( 4)), products.getProductByName("Table"     ));
		Assert.assertEquals(products.getProducts().get(new Integer( 5)), products.getProductByName("Flour"     ));
		Assert.assertEquals(products.getProducts().get(new Integer( 6)), products.getProductByName("Milk"      ));
		Assert.assertEquals(products.getProducts().get(new Integer( 7)), products.getProductByName("Sugar"     ));
		Assert.assertEquals(products.getProducts().get(new Integer( 8)), products.getProductByName("Beer"      ));
		Assert.assertEquals(products.getProducts().get(new Integer( 9)), products.getProductByName("Parfume"   ));
		Assert.assertEquals(products.getProducts().get(new Integer(10)), products.getProductByName("Toothpaste"));
		Assert.assertEquals(products.getProducts().get(new Integer(11)), products.getProductByName("Shampoo"   ));
		Assert.assertEquals(products.getProducts().get(new Integer(12)), products.getProductByName("Brush"     ));
	}

	@Test
	public void testGetTypeByName() {
		Assert.assertEquals(ProductType.PIECE    .getCaption(), products.getTypeByName("Lightbulb" ));
		Assert.assertEquals(ProductType.PIECE    .getCaption(), products.getTypeByName("Lamp"      ));
		Assert.assertEquals(ProductType.PIECE    .getCaption(), products.getTypeByName("Pen"       ));
		Assert.assertEquals(ProductType.PIECE    .getCaption(), products.getTypeByName("Table"     ));
		Assert.assertEquals(ProductType.BULK     .getCaption(), products.getTypeByName("Flour"     ));
		Assert.assertEquals(ProductType.BULK     .getCaption(), products.getTypeByName("Milk"      ));
		Assert.assertEquals(ProductType.BULK     .getCaption(), products.getTypeByName("Sugar"     ));
		Assert.assertEquals(ProductType.BULK     .getCaption(), products.getTypeByName("Beer"      ));
		Assert.assertEquals(ProductType.SALETWO  .getCaption(), products.getTypeByName("Parfume"   ));
		Assert.assertEquals(ProductType.SALETWO  .getCaption(), products.getTypeByName("Toothpaste"));
		Assert.assertEquals(ProductType.SALETHREE.getCaption(), products.getTypeByName("Shampoo"   ));
		Assert.assertEquals(ProductType.SALETHREE.getCaption(), products.getTypeByName("Brush"     ));
	}

	@Test
	public void testGetIdByName() {
		Assert.assertEquals(new Integer( 1), products.getIdByName("Lightbulb" ));
		Assert.assertEquals(new Integer( 2), products.getIdByName("Lamp"      ));
		Assert.assertEquals(new Integer( 3), products.getIdByName("Pen"       ));
		Assert.assertEquals(new Integer( 4), products.getIdByName("Table"     ));
		Assert.assertEquals(new Integer( 5), products.getIdByName("Flour"     ));
		Assert.assertEquals(new Integer( 6), products.getIdByName("Milk"      ));
		Assert.assertEquals(new Integer( 7), products.getIdByName("Sugar"     ));
		Assert.assertEquals(new Integer( 8), products.getIdByName("Beer"      ));
		Assert.assertEquals(new Integer( 9), products.getIdByName("Parfume"   ));
		Assert.assertEquals(new Integer(10), products.getIdByName("Toothpaste"));
		Assert.assertEquals(new Integer(11), products.getIdByName("Shampoo"   ));
		Assert.assertEquals(new Integer(12), products.getIdByName("Brush"     ));
	}

	@Test
	public void testIsAmountEditable() {
		Assert.assertEquals(false, products.isAmountEditable("Lightbulb" ));
		Assert.assertEquals(false, products.isAmountEditable("Lamp"      ));
		Assert.assertEquals(false, products.isAmountEditable("Pen"       ));
		Assert.assertEquals(false, products.isAmountEditable("Table"     ));
		Assert.assertEquals(true , products.isAmountEditable("Flour"     ));
		Assert.assertEquals(true , products.isAmountEditable("Milk"      ));
		Assert.assertEquals(true , products.isAmountEditable("Sugar"     ));
		Assert.assertEquals(true , products.isAmountEditable("Beer"      ));
		Assert.assertEquals(true , products.isAmountEditable("Parfume"   ));
		Assert.assertEquals(true , products.isAmountEditable("Toothpaste"));
		Assert.assertEquals(true , products.isAmountEditable("Shampoo"   ));
		Assert.assertEquals(true , products.isAmountEditable("Brush"     ));
	}

}
