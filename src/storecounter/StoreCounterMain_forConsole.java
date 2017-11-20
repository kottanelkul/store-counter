package storecounter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StoreCounterMain_forConsole {

	public StoreCounterMain_forConsole() {
	}

	public static void main(String args[]) {

		System.out.print("Scanning a product by entering product ID (number between: 1-12).\n"
				+ "Enter '0' as ID for end of scanning and calculating total price.\n");

		Map<Integer, Product> productList = new Products().getProducts();
		Map<Integer, Product> boughtProducts = new HashMap<>();
		
		int id;
		double amount;

		do {
			Scanner sc = new Scanner(System.in);
			System.out.print("\nEnter product ID: ");
			id = sc.nextInt();
			Integer idObj = new Integer(id);
			
			if (id == 0) {
				
				sc.close();	
				
			} else {
				
				if (!productList.get(idObj).getType().equals(ProductType.PIECE)) {
					System.out.print("Enter amount: ");
					amount = sc.nextDouble();
				} else {
					amount = 1;
				}
				
				if (boughtProducts.get(idObj) == null) {
					
					boughtProducts.put(idObj, productList.get(idObj));
					boughtProducts.get(idObj).setAmount(amount);
//					System.out.print(boughtProducts.get(idObj).getName() + ", amount: " + boughtProducts.get(idObj).getAmount() + "\n");
					
				} else {
					
//					System.out.print(boughtProducts.get(idObj).getName() + ", old amount: " + boughtProducts.get(idObj).getAmount() + "\n");
					boughtProducts.get(idObj).setAmount(boughtProducts.get(idObj).getAmount() +  amount);
//					System.out.print(boughtProducts.get(idObj).getName() + ", amount: " + boughtProducts.get(idObj).getAmount() + "\n");
				}
			}
		} while (id != 0);
		
		calculateTotalAndPrint(boughtProducts);
		
	}

	private static void calculateTotalAndPrint(Map<Integer, Product> boughtProducts) {
		double totalPrice = 0;
		String amount;
		
        for (final Map.Entry<Integer, Product> entry : boughtProducts.entrySet()) {
        	Product value = entry.getValue();
        	value.setTotalPrice(calculateTotalPriceForAProduct(value));
        	totalPrice = totalPrice + value.getTotalPrice();
        	
        	if (value.getType().equals(ProductType.SALETWO) || value.getType().equals(ProductType.SALETHREE)) {
        		amount = String.valueOf((int) value.getAmount());
        	} else {
        		amount = String.valueOf(value.getAmount());
        	}
        	System.out.print("\n" + value.getName() + ", " + amount + " " + value.getUnit() + ": " + String.format("%.2f", value.getTotalPrice()) + " USD");
        }
		System.out.print("\n\nTotal price: " + String.format("%.2f", totalPrice) + " USD");
	}

	private static double calculateTotalPriceForAProduct(Product product) {
		int rest;
		int n;
		double price        = 0;
		double pricePerUnit = product.getPricePerUnit();
		double amount       = product.getAmount();
		int    amountInt    = (int) amount;
		
		switch (product.getType()) {
			case PIECE: price = pricePerUnit * amount;
				break;
			case BULK: price = pricePerUnit * amount;
				break;
			case SALETWO: 
				rest  = amountInt % 2;
				n     = (amountInt - rest) / 2;
				price = pricePerUnit * (n + rest);
				break;
			case SALETHREE: 
				rest  = amountInt % 3;
				n     = ((amountInt - rest) / 3) * 2;
				price = pricePerUnit * (n + rest);
				break;
		}
		return price;
	}

}
