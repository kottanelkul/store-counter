package storecounter;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.SystemColor;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreCounterFrame {
	
	private Color      pinkColor               = new Color(255, 153, 153);
	private Color      menuColor               = new Color(240, 240, 240);
	private String     htmlStart               = "<html>";
	private String     htmlTableStart          = "<table>";
	private String     htmlEnd                 = "</html>";
	private String     htmlTableEnd            = "</table>";
	private String     boughtProductsList_html = "";
	private Products   products                = new Products();
	private Product    product_default         = products.getProducts().get(new Integer(1));
	
	private JFrame     frame;
	private JTextField amount;
	private JTextField unit;
	private JTextField price;
	private JTextField type;
	private JTextField warningField;
	private JComboBox  comboBox               = new JComboBox();
	private JTextPane  boughtProductsTextPane = new JTextPane();

	private Map<Integer, Product> products_Map       = products.getProducts();
	private Map<Integer, Product> boughtProducts_Map = new HashMap<>();

//		public static void main(String[] args) {
//			EventQueue.invokeLater(new Runnable() {
//				public void run() {
//					try {
//						StoreCounterFrame window = new StoreCounterFrame();
//						window.frame.setVisible(true);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			});
//		}

	public StoreCounterFrame() {

		frame = new JFrame("Store counter");
		frame.setBounds(100, 100, 498, 509);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel productslabel = new JLabel("Products");
		productslabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		productslabel.setBounds(16, 66, 80, 23);
		frame.getContentPane().add(productslabel);

		JButton totalButton = new JButton("TOTAL");
		totalButton.setToolTipText("");
		totalButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		totalButton.setForeground(Color.RED);
		totalButton.setBounds(16, 416, 216, 44);
		frame.getContentPane().add(totalButton);

		comboBox.setToolTipText("");
		comboBox.setBounds(16, 88, 177, 23);
		comboBox.setModel(new DefaultComboBoxModel(products.getAllProductNamesArray()));
		comboBox.setSelectedItem(products.getAllProductNamesArray()[0]);
		frame.getContentPane().add(comboBox);

		amount = new JTextField();
		amount.setBackground(menuColor);
		amount.setEditable(false);
		amount.setToolTipText("");
		amount.setBounds(209, 89, 71, 20);
		amount.setColumns(10);
		amount.setText("1");
		frame.getContentPane().add(amount);

		JLabel amountLabel = new JLabel("Amount");
		amountLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		amountLabel.setBounds(209, 66, 62, 23);
		frame.getContentPane().add(amountLabel);

		JLabel unitLabel = new JLabel("Unit");
		unitLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		unitLabel.setBounds(296, 66, 44, 23);
		frame.getContentPane().add(unitLabel);

		unit = new JTextField();
		unit.setColumns(10);
		unit.setBounds(296, 89, 52, 20);
		unit.setEditable(false);
		unit.setText(product_default.getUnit());
		frame.getContentPane().add(unit);

		price = new JTextField();
		price.setColumns(10);
		price.setBounds(364, 89, 101, 20);
		price.setEditable(false);
		price.setText(String.valueOf(product_default.getPricePerUnit()));
		frame.getContentPane().add(price);

		JLabel priceLabel = new JLabel("Price per unit");
		priceLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		priceLabel.setBounds(364, 66, 108, 23);
		frame.getContentPane().add(priceLabel);

		type = new JTextField();
		type.setBorder(null);
		type.setForeground(Color.BLUE);
		type.setFont(new Font("Tahoma", Font.BOLD, 11));
		type.setEditable(false);
		type.setBounds(16, 122, 187, 20);
		type.setColumns(10);
		type.setText(product_default.getType().getCaption());
		frame.getContentPane().add(type);

		JButton addButton = new JButton("BUY");
		addButton.setForeground(new Color(0, 128, 0));
		addButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		addButton.setToolTipText("");
		addButton.setBounds(16, 153, 449, 44);
		frame.getContentPane().add(addButton);

		JLabel topLabel = new JLabel("<html>Select desired product from \"Products\" dropdown menu. <br/>Buy selected product by pressing \"BUY\" button. <br/>If the product is sold in bulk or is on sale (see blue info) enter amount!</html>");
		topLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		topLabel.setBounds(16, 11, 449, 44);
		frame.getContentPane().add(topLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 208, 449, 197);
		frame.getContentPane().add(scrollPane);

		boughtProductsTextPane.setFont(new Font("Monospaced", Font.PLAIN, 12));
		boughtProductsTextPane.setEditable(false);
		scrollPane.setViewportView(boughtProductsTextPane);
		boughtProductsTextPane.setContentType("text/html");
		
		warningField = new JTextField();
		warningField.setForeground(Color.RED);
		warningField.setFont(new Font("Tahoma", Font.BOLD, 11));
		warningField.setEditable(false);
		warningField.setColumns(10);
		warningField.setBorder(null);
		warningField.setBounds(209, 122, 256, 20);
		frame.getContentPane().add(warningField);
		
		JButton printButton = new JButton("PRINT");
		printButton.setToolTipText("");
		printButton.setForeground(SystemColor.desktop);
		printButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		printButton.setBounds(249, 416, 216, 44);
		frame.getContentPane().add(printButton);

		comboBox.addItemListener(event -> {
			String selectedProduct = comboBox.getSelectedItem().toString();

			unit .setText(products.getUnitByName        (selectedProduct));
			price.setText(products.getPricePerUnitByName(selectedProduct));
			type .setText(products.getTypeByName        (selectedProduct));

			amount.setEditable(products.isAmountEditable(selectedProduct));
			
			warningField.setText("");
			
			if (!products.isAmountEditable(selectedProduct)) {
				amount.setText("1");
				this.amount.setBackground(menuColor);
			} else {
				amount.setText("");
				this.amount.setBackground(Color.WHITE);
			}
		});

		addButton.addActionListener(event -> {
			String  name       = comboBox.getSelectedItem().toString();
			Integer id         = products.getIdByName(name);
			String  amountText = this.amount.getText();
			double  amountDouble;
			
			switch (products.getProductByName(name).getType()) {
				case PIECE:
					this.amount.setBackground(menuColor);
					amountDouble = Double.parseDouble(amountText);
					buyProduct(id, amountDouble);
					boughtProductsTextPane.setText(build_boughtProductList_html(name, amountDouble));
					break;
				case BULK:
					if (amountText.isEmpty()) {
						warning("Enter amount!", pinkColor);
					} else if (Double.parseDouble(amountText) < 0.005) {
						warning("Minimum value is 0.005!", pinkColor);
					} else {
						warning("", Color.WHITE);
						amountDouble = Double.parseDouble(amountText);
						buyProduct(id, amountDouble);
						boughtProductsTextPane.setText(build_boughtProductList_html(name, amountDouble));
					}
					break;
				case SALETWO:
					if (amountText.isEmpty()) {
						warning("Enter amount!", pinkColor);
					} else if (Double.parseDouble(amountText) < 1) {
						warning("Minimum value is 1!", pinkColor);
					} else {
						warning("", Color.WHITE);
						amountDouble = (double) ((int) Double.parseDouble(amountText));
						buyProduct(id, amountDouble);
						boughtProductsTextPane.setText(build_boughtProductList_html(name, amountDouble));
					}
					break;
				case SALETHREE:
					if (amountText.isEmpty()) {
						warning("Enter amount!", pinkColor);
					} else if (Double.parseDouble(amountText) < 1) {
						warning("Minimum value is 1!", pinkColor);
					} else {
						warning("", Color.WHITE);
						amountDouble = (double) ((int) Double.parseDouble(amountText));
						buyProduct(id, amountDouble);
						boughtProductsTextPane.setText(build_boughtProductList_html(name, amountDouble));
					}
					break;
			}
		});

		totalButton.addActionListener(event -> {
			boughtProductsTextPane.setText(appendTotalToPurchaseList());
		});
		
		printButton.addActionListener(event -> {
			writePurchaseToFile();
		});

	}//END: init()

	public void warning(String text, Color color) {
		warningField.setText(text);
		this.amount.setBackground(color);
	}

	public JFrame getFrame() {
		return frame;
	}
	
	public void buyProduct(Integer id, double amount) {
		
		if (boughtProducts_Map.get(id) == null) {
			boughtProducts_Map.put(id, products_Map.get(id));
			boughtProducts_Map.get(id).setAmount(amount);
		} else {
			boughtProducts_Map.get(id).setAmount(boughtProducts_Map.get(id).getAmount() +  amount);
		}
	}
	
	public String build_boughtProductList_html(String  name, double amount) {
		Product actualProduct = products.getProductByName(name);
		String  unit          = actualProduct.getUnit();
		double  pricePerUnit  = actualProduct.getPricePerUnit();
		
		Product newProduct = new Product();
		newProduct.setType(actualProduct.getType());
		newProduct.setAmount(amount);
		newProduct.setPricePerUnit(pricePerUnit);
		
		double totalPrice = calculateTotalPriceForAProduct(newProduct);

		String tableRow = 
				          "<tr><td>" + name + "</td>"
						+ "<td style=\"text-align: right\";>" + String.format("%.2f", amount) + "</td>"
						+ "<td>" + unit + "</td>"
						+ "<td> * </td>"
						+ "<td style=\"text-align: right\";>" + String.format("%.2f", pricePerUnit) + " : </td>"
						+ "<td style=\"text-align: right\";>" + String.format("%.2f", totalPrice) + " USD</td></tr>";

		boughtProductsList_html = boughtProductsList_html + tableRow;

		return (htmlStart + htmlTableStart + boughtProductsList_html + htmlTableEnd + htmlEnd);
	}
	
	public double calculateTotalPriceForAProduct(Product product) {
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

	public String appendTotalToPurchaseList() {
		double totalPrice = calculateTotal();
		String totalrow   = "Total: " + String.format("%.2f", totalPrice) + " USD";
		String separator  = "";
		
		for (int i = 1; i <= totalrow.length() - 2; i++) {
			separator = separator + "_";
		}
		return (htmlStart + htmlTableStart + boughtProductsList_html+ htmlTableEnd + separator + "<br>" + totalrow + htmlEnd);
	}

	public double calculateTotal() {
		double totalPrice = 0;
		for (final Map.Entry<Integer, Product> entry : boughtProducts_Map.entrySet()) {
			Product value = entry.getValue();
			value.setTotalPrice(calculateTotalPriceForAProduct(value));
			totalPrice = totalPrice + value.getTotalPrice();
		}
		return totalPrice;
	}
	
	public void writePurchaseToFile() {
		
		//write textPane content to html file
		String fileName = "receipt.html";
		String textPaneContent = boughtProductsTextPane.getText();
		BufferedWriter wr;

		try {
			wr = new BufferedWriter(new FileWriter(fileName));
			wr.write(textPaneContent);
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//open html file
		try {
			URI oURL = new URI(fileName);
			Desktop.getDesktop().browse(oURL);
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
	}
}
