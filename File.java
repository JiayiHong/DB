import java.util.*;
import java.io.*;

class File {

    private String fileName;
    Table table;
    PrintWriter outputStream;

    File (Table table) {
        this.table = table;
    }

    void getFileName(String name) {
       fileName = name;
    }

    boolean writeToFile() {
        try{
            outputStream = new PrintWriter(fileName);
            printTable();
            outputStream.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    void printStringArray(String[] toPrint) {
        outputStream.print("|");
        for(String string : toPrint) {
            outputStream.print("\t" + string + "\t|");
        }
        outputStream.println();
    }

    void printTable(){
        printStringArray(table.getHeader());
        for (int i = 1; i <= table.records.getRecordsNumber(); i++) {
            printStringArray(table.getTableRow(i).get(0));
        }
    }




    //	===============TESTING===============

	public static void main(String[] args) {
		File program = new File(new Table("TestTable"));
        program.run();
	}

    private void run() {
        boolean testing = false;
		assert(testing = true);
		if (!testing) throw new Error("Use java -ea Triangle");
		test(); 
		System.out.println("All tests pass");
    }

    private void test() {
        testWriteToFile();
    }

    private void testWriteToFile() {
        table.setHeader("name","age");
        table.addTableData(new String[]{"John", "10"});
        table.addTableData(new String[]{"Mary", "13"});
        table.addTableData(new String[]{"Frank", "25"});
        table.addTableData(new String[]{"Bob", "22"});
        getFileName("testOutput.txt");
        assert(writeToFile());
    }

}
