package storecounter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	private String      name;
	private ProductType type;
	private double      pricePerUnit;
	private String      unit;
	private double      amount;
	private double      totalPrice;
}
