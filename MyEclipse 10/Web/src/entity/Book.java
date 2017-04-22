package entity;

public class Book {
	    private int  id;
		private String pic;
		private float price;
		private float remark;
		private String author;
		private String detail;
		private  String name;
		public synchronized int getId() {
			return id;
		}
		public synchronized void setId(int id) {
			this.id = id;
		}
		public synchronized String getPic() {
			return pic;
		}
		public synchronized void setPic(String pic) {
			this.pic = pic;
		}
		public synchronized float getPrice() {
			return price;
		}
		public synchronized void setPrice(float price) {
			this.price = price;
		}
		public synchronized float getRemark() {
			return remark;
		}
		public synchronized void setRemark(float remark) {
			this.remark = remark;
		}
		public synchronized String getAuthor() {
			return author;
		}
		public synchronized void setAuthor(String author) {
			this.author = author;
		}
		public synchronized String getDetail() {
			return detail;
		}
		public synchronized void setDetail(String detail) {
			this.detail = detail;
		}
		public synchronized String getName() {
			return name;
		}
		public synchronized void setName(String name) {
			this.name = name;
		}

		
}
