import java.util.*;

class EditTable {

	Map<String, Table> tables = new HashMap<String, Table>();
	Table current;


	// Create a new table
	void createTable(String name) {
		Table newtable = new Table(name);
		tables.put(name, newtable);
		current = newtable;
	}

	void addTableHeader(String header) {
		current.setHeader(header);
	}

	void defineType(String typein) {
		if (typein == null || typein.isEmpty()) {
			System.out.println("Please type in every column's type.");
			return;
		}
		String[] words = typein.split(" ");
		current.Table()
	}


	// Locate to the specific table
	void switchTable(String name) {
		boolean flag = false;	// Mark whether successful
		for (String key : tables.keySet()) {
			if (key == name) {
				current = tables.get(key);
				flag = true;
			}
		}
		if (flag == false) {
			System.out.println("Can't find the table.");
		}
	}




	//	===============TESTING===============

	public static void main(String[] args) {
		
	}
}