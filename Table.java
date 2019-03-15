import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class Table {

	private String name;
	private String[] header;
	private String[] type;
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
		this.type = type;
		if(header.length != type.length)	return false;
		return true;
	}

	String getName(){
		return name;
	}

	String[] getHeader() {
		return header;
	}

	Records getRecords() {
		return records;
	}

	boolean addTableData(String[] toadd) {
		if (toadd.length == header.length) {

			if (records.addRecord(toadd))	return true;
			else System.out.println("Duplicate records!");
		}
		else System.out.println("Please input "+ 
								header.length + " data in a row");
		return false;
	}

	void checkDataType(String[] toadd) {

	}

	List<String[]> getTableRow(int... number) {
		String[] row;
		List<String[]> list = new ArrayList<String[]>();
		for (int n : number ) {
			if (!checkRowIndexValid(n)) return null;
			row = records.getCertainRecord(n-1);
			list.add(row);
		} 
		return list;
	}

	boolean deleteRow(int... number) {
		for (int n : number) {
			if (!checkRowIndexValid(n)) return false;
			records.removeCertainRecord(n-1);
		}
		return true;
	}

	boolean updateRow(int rownumber, int colnumber, String toupdate) {
		if (!checkRowIndexValid(rownumber) || !checkColIndexValid(colnumber))
			return false;
		records.updateCertainRecord(rownumber-1, colnumber-1, toupdate);
		return true;
	}

	boolean checkRowIndexValid(int number) {
		if (number > records.getRecordsNumber() || number <= 0) {
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
	}

	private void testName() {
		assert(name == "new table");
	}

	private void testHeader() {
		setHeader("name", "age");
		assert(header[0] == "name");
		assert(header[1] == "age");
	}

	private void testAddTableData() {
		assert(addTableData(new String[]{"John", "10"}));
		assert(addTableData(new String[]{"Mary", "13"}));
		assert(addTableData(new String[]{"Frank", "25"}));
		assert(!addTableData(new String[]{"John", "10"}));
		assert(!addTableData(new String[]{"John", "10", "40"}));
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
		assert(getTableRow(3) == null);
	}

	private void testUpdateRow() {
		assert(!updateRow(0,1,"Bob"));
		assert(!updateRow(3,1,"Bob"));
		assert(!updateRow(1,0,"22"));
		assert(!updateRow(1,3,"22"));
		assert(updateRow(1,2,"22"));
		assert(updateRow(1,1,"Bob"));
		assert(getTableRow(1) != null);
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

}
