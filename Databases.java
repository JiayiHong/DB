import java.util.Scanner;
import java.io.File;
import java.util.*;
import java.io.*;

class Databases {

    Map<String, Table> tables = new HashMap<String, Table>();
    final File folder = new File("databases");

    Databases() {
        for (File fileEntry : folder.listFiles()) {
            if (!fileEntry.isHidden()) {
                readFileTable(fileEntry);
            }
        }
    }

    void readFileTable(File file) {
        String tablename = file.getName().replaceFirst("[.][^.]+$","");
        Table newTable = new Table(tablename);
        List<String[]> records = readFile(file);
        newTable.setHeader(records.get(0));
        newTable.setType(records.get(1));
        newTable.setKey(getKey(records.get(2)));
        for (int i = 3; i < records.size(); i++) {
            if (newTable.addTableData(records.get(i)));
        }
        addToDatabases(tablename, newTable);
    }

    List<String[]> readFile(File file) {
        List<String[]> records = new ArrayList<String[]>();;
        
        try{
            Scanner in = new Scanner(file);
            while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] data = line.split(",");
            records.add(data);
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return records;
    }

    void addToDatabases(String name, Table table) {
        tables.put(name, table);
    }

    Map<String, Table> getTables() {
        return tables;
    }
    
    int[] getKey(String... str) {
        int[] number = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            number[i] = Integer.parseInt(str[i]);
        }
        return number;
    }


//	===============TESTING===============

	public static void main(String[] args) {
		Databases program = new Databases();
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

    }

}