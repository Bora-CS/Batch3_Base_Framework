package DataObjects;

public class Post {

	public String _id;
	public String text;
	public String user;
	public String[] likes;
	public String[] comments;
	public String date;
	public int __v;

	public Post() {
	}

	public Post(String _id, String text, String user, String[] likes, String[] comments, String date, int __v) {
		this._id = _id;
		this.text = text;
		this.user = user;
		this.likes = likes;
		this.comments = comments;
		this.date = date;
		this.__v = __v;
	}

}
