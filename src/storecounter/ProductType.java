package storecounter;

public enum ProductType {
	PIECE("Sold by piece."),
	BULK("Sold in bulk."),
	SALETWO("On sale! Buy one, get one free."),
	SALETHREE("On sale! Buy two, get one free.");

	private String caption;

	private ProductType(String caption) {
		this.caption = caption;
	}

	public String getCaption() {
		return caption;
	}
}
