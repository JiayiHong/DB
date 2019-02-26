import java.util.*;

class Column<T> {

	private String type;
	private List<T> column = new ArrayList<T>();

	Column (String type) {

		this.type = type;
	}

	String getType() {
		return this.type;
	}

	void addData(T data) {
		column.add(data);
	}
 
	

//	===============TESTING===============

	public static void main(String[] args) {
		
	}

}
