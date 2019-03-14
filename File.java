import java.util.*;
import java.io.*;

class File {

    private String fileName;
    Table table;
    PrintWriter outputStream;

    File (Table table) {
        this.table = table;
        fileName = "databases/"+ table.getName() + ".txt";
    }

    void setFileName(String name) {
       fileName = name;
    }

    boolean writeToFile() {
        try{
            outputStream = new PrintWriter(fileName);
            saveTable();
            outputStream.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    void saveStringArray(String[] toPrint) {
        for(String string : toPrint) {
            outputStream.print(string + ",");
        }
        outputStream.println();
    }

    void saveTable(){
        saveStringArray(table.getHeader());
        for (int i = 1; i <= table.records.getRecordsNumber(); i++) {
            saveStringArray(table.getTableRow(i).get(0));
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
        setFileName("testfile/testOutput.txt");
        assert(writeToFile());
    }

}
