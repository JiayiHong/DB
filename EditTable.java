import java.util.*;

class EditTable {

	Map<String, Table> tables = new HashMap<String, Table>();
	Table current;
	private enum dataType {number, text};

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
			System.out.println("Please type in every column's type with number or text.");
			return;
		}
		String[] words = typein.split(" ");
		dataType type;
		for (String word : words) {
			type = dataType.valueOf(word);
			switch (type) {
				case number:
					current.addTableColumn(new Column("double"));
					break;
				case text:
					current.addTableColumn(new Column("String"));
					break;
				default:
					System.out.println("Please type in number or text.");
			}
		}
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


	// Add table's data



	//	===============TESTING===============

	public static void main(String[] args) {
		
	}
}
