import java.util.*;

class Table {

	private String name;
	private String[] header;
	private List<Column> cols = new ArrayList<>();

	// Create a table with new name
	Table(String name) {
		this.name = name;
	}

	// Set the table header
	void setHeader(String... header) {
		this.header = header;
	}

	// Initialize the table with 
	void Table(Column... columns) {
		for (Column c : columns) {
			cols.add(c);
		}
	}







//	===============TESTING===============

	public static void main(String[] args) {
		Table program = new Table("new table");
		program.run();
	}

	private void run() {
		boolean testing = false;
		assert(testing = true);
		if (!testing) throw new Error("Use java -ea Triangle");
		test(); //test everything
		System.out.println("All tests pass");
	}

	private void test() {
		testName();
		testHeader();
	}

	private void testName() {
		assert(name == "new table");
	}

	private void testHeader() {
		setHeader("name", "age");
		assert(header[0] == "name");
		assert(header[1] == "age");
	}

}
