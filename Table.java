import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class Table {

	private String name;
	private String[] header;
	private String[] type;
	private int[] keys;
	Records records;

	// Create a table with new name
	Table(String name) {
		this.name = name;
	}

	// Set the table header
	void setHeader(String... header) {
		this.header = header;
		records = new Records(header.length);
	}

	boolean setType(String... type) {
		if(header.length != type.length)	return false;
		this.type = type;
		if(records.addRecord(type));
		return true;
	}

	void setKey(int... keys) {
		this.keys = keys;
		records.setKeys(keys);
	}

	int[] getKey() {
		return keys;
	}

	String getName(){
		return name;
	}

	String[] getHeader() {
		return header;
	}

	String[] getType() {
		return type;
	}

	Records getRecords() {
		return records;
	}

	boolean addTableData(String... toadd) {
		if (checkDataType(toadd)) {
			if (records.addRecord(toadd))	return true;
			// else System.out.println("Duplicate records!");
		}
		return false;
	}

	boolean checkDataType(String... toadd) {
		for (int i = 0; i < header.length; i++) {
			if ((type[i].equals("number") && !checkNum(toadd[i])) ||
				(type[i].equals("date") && !checkDate(toadd[i])) ||
				(type[i].equals("currency") && !checkCurrency(toadd[i])))
				return false;
		}
		return true;
	}

	List<String[]> getTableRow(int... number) {
		String[] row;
		List<String[]> list = new ArrayList<String[]>();
		for (int n : number ) {
			if (!checkRowIndexValid(n)) return null;
			row = records.getCertainRecord(n);
			list.add(row);
		} 
		return list;
	}

	boolean deleteRow(int... number) {
		for (int n : number) {
			if (!checkRowIndexValid(n)) return false;
			records.removeCertainRecord(n);
		}
		return true;
	}

	boolean updateRow(int rownumber, int colnumber, String toupdate) {
		if (!checkRowIndexValid(rownumber) || !checkColIndexValid(colnumber))
			return false;
		records.updateCertainRecord(rownumber, colnumber-1, toupdate);
		return true;
	}

	boolean checkRowIndexValid(int number) {
		if (number >= records.getRecordsNumber() || number < 1) {
			return false;
		}
		return true;
	}

	boolean checkColIndexValid(int number) {
		if (number > header.length || number <= 0) {
			return false;
		}
		return true;
	}

	boolean checkNum(String string) {
		return string.matches("-?\\d+(\\.\\d+)?");
	}
	
	boolean checkDate(String string) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(string.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
	}

	boolean checkCurrency(String string) {
		Currency currency = Currency.getInstance(Locale.UK);
		String symbol = currency.getSymbol();
		if(string.startsWith(symbol) || string.endsWith(symbol)){
    		return true;
		}
    	return false;
	}






//	===============TESTING===============

	void printTable() {
		printStringArray(header);
		for (int i = 0; i < records.getRecordsNumber(); i++) {
			printStringArray(records.getCertainRecord(i));
		}
	}

	void printStringArray(String[] toprint) {
		System.out.print("|\t");
		for (String string : toprint) {
			System.out.print(string + "\t|\t");
		}
		System.out.println();
	}

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
		testAddTableData();
		testGetTableRow();
		testDeleteRow();
		testUpdateRow();
		testStringType();
		testCheckType();
	}

	private void testName() {
		assert(name == "new table");
	}

	private void testHeader() {
		setHeader("name", "age");
		records.setKeys(0);
		assert(header[0] == "name");
		assert(header[1] == "age");
	}

	private void testAddTableData() {
		assert(setType("text", "number"));
		assert(addTableData(new String[]{"John", "10"}));
		assert(addTableData(new String[]{"Mary", "13"}));
		assert(addTableData(new String[]{"Frank", "25"}));
		assert(!addTableData(new String[]{"John", "10"}));
		assert(!addTableData(new String[]{"John", "f1"}));
		assert(!addTableData(new String[]{"Mary", "13"}));
	}

	private void testGetTableRow() {
		assert(getTableRow(0)==null);
		// assert(!getTableRow(-3));
		// assert(!getTableRow(4));
	}

	private void testDeleteRow() {
		assert(!deleteRow(4));
		assert(!deleteRow(0));
		assert(getTableRow(3) != null);
		assert(deleteRow(1));
		assert(getTableRow(4) == null);
	}

	private void testUpdateRow() {
		assert(!updateRow(0,1,"Bob"));
		assert(!updateRow(4,1,"Bob"));
		assert(!updateRow(2,0,"22"));
		assert(!updateRow(2,3,"22"));
		assert(updateRow(2,2,"22"));
		assert(updateRow(2,1,"Bob"));
		assert(getTableRow(2) != null);
	}

	private void testStringType() {
		assert(checkNum("2.2"));
		assert(checkNum("0"));
		assert(checkNum("-1"));
		assert(checkNum("-505"));
		assert(checkNum("2.02"));
		assert(!checkNum("2.2.2"));
		assert(!checkNum("-.43"));
		assert(checkDate("14-03-2019"));
		assert(!checkDate("29-02-2019"));
		assert(!checkDate("44-03-2019"));
		assert(!checkDate("14032019"));
		assert(checkCurrency("£20.0"));
		assert(!checkCurrency("20.0"));
		assert(!checkCurrency("-£20.0"));
	}

	private void testCheckType() {
		assert(!setType("number"));
		assert(checkDataType("Bob","40.5"));
		assert(!checkDataType("Bob","Ann"));
		assert(!checkDataType("Bob","fr3"));
		assert(!checkDataType("Bob","£20"));
		assert(!addTableData("Bob", "A1"));
		assert(!addTableData("Bob", "2f"));
		assert(setType("date", "currency"));
		assert(checkDataType("12-04-2019","£10"));
		assert(!checkDataType("Bob","22"));
		assert(!checkDataType("12-04-2019","22"));
	}

}
