package DataObjects;

// POJO - Plain old java object class
public class AmazonSearchResult {
	
	public int ItemID;
	public String ItemName;
	public double ItemPrice;
	
	// default constructor
	public AmazonSearchResult () {
	}
	
	// custom constructor
	public AmazonSearchResult (int ItemID, String ItemName, double ItemPrice) {
		this.ItemID = ItemID;
		this.ItemName = ItemName;
		this.ItemPrice = ItemPrice;
	}
	
}
