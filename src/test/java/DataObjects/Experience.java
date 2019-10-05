package DataObjects;

public class Experience {

	public boolean current;
	public String _id;
	public String title;
	public String company;
	public String location;
	public String from;
	public String to;
	public String description;

	// Default constructor
	public Experience() {
	}

	// custom constructor
	public Experience(boolean current, String _id, String title, String company, String location, String from,
			String to, String description) {
		this.current = current;
		this._id = _id;
		this.title = title;
		this.company = company;
		this.location = location;
		this.from = from;
		this.to = to;
		this.description = description;
	}

}
