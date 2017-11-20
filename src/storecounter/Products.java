package storecounter;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public class Products {

	private Map<Integer, Product> products = new HashMap<>();
	
	public Products() {
		
		products.put( 1, new Product("Lightbulb" , ProductType.PIECE    ,  2.60, "piece" , 0, 0));
		products.put( 2, new Product("Lamp"      , ProductType.PIECE    , 11.32, "piece" , 0, 0));
		products.put( 3, new Product("Pen"       , ProductType.PIECE    ,  1.12, "piece" , 0, 0));
		products.put( 4, new Product("Table"     , ProductType.PIECE    , 99.00, "piece" , 0, 0));
		products.put( 5, new Product("Flour"     , ProductType.BULK     ,  1.12, "kg"    , 0, 0));
		products.put( 6, new Product("Milk"      , ProductType.BULK     ,  0.86, "l"     , 0, 0));
		products.put( 7, new Product("Sugar"     , ProductType.BULK     ,  2.05, "lb"    , 0, 0));
		products.put( 8, new Product("Beer"      , ProductType.BULK     ,  1.11, "pt"    , 0, 0));
		products.put( 9, new Product("Parfume"   , ProductType.SALETWO  , 12.60, "pieces", 0, 0));
		products.put(10, new Product("Toothpaste", ProductType.SALETWO  ,  7.35, "pieces", 0, 0));
		products.put(11, new Product("Shampoo"   , ProductType.SALETHREE,  5.60, "pieces", 0, 0));
		products.put(12, new Product("Brush"     , ProductType.SALETHREE,  7.45, "pieces", 0, 0));
	}
	
	public String [] getAllProductNamesArray() {
		String [] names = new String [products.size()];
		int x = 0;
		for (final Map.Entry<Integer, Product> entry : products.entrySet()) {
        	String name = entry.getValue().getName();
        	names[x] = name;
        	x++;
		}
		return names;
	}
	
	public String getUnitByName(String name) {
		String unit = null;
		for (final Map.Entry<Integer, Product> entry : products.entrySet()) {
        	if (name == entry.getValue().getName()) {
        		unit = entry.getValue().getUnit();
        	}
		}
		return unit;
	}
	
	public String getPricePerUnitByName(String name) {
		String unit = null;
		for (final Map.Entry<Integer, Product> entry : products.entrySet()) {
			if (name == entry.getValue().getName()) {
				unit = String.valueOf(entry.getValue().getPricePerUnit());
			}
		}
		return unit;
	}
	
	public Product getProductByName(String name) {
		Product product = null;
		for (final Map.Entry<Integer, Product> entry : products.entrySet()) {
			if (name == entry.getValue().getName()) {
				product = entry.getValue();
			}
		}
		return product;
	}
	

	public String getTypeByName(String name) {
		String type = null;
		for (final Map.Entry<Integer, Product> entry : products.entrySet()) {
			if (name == entry.getValue().getName()) {
				type = entry.getValue().getType().getCaption();
			}
		}
		return type;
	}
	
	public Integer getIdByName(String name) {
		Integer id = null;
		for (final Map.Entry<Integer, Product> entry : products.entrySet()) {
			if (name == entry.getValue().getName()) {
				id = entry.getKey();
			}
		}
		return id;
	}
	
	public boolean isAmountEditable(String name) {
		boolean editable = false;
		for (final Map.Entry<Integer, Product> entry : products.entrySet()) {
			if (name == entry.getValue().getName()) {
				if (entry.getValue().getType().equals(ProductType.PIECE)) {
					editable = false;
				} else {
					editable = true;
				}
			}
		}
		return editable;
	}
	

}

