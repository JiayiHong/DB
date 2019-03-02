import java.util.*;

class Records {
    private int dataNumber; // the number of data every row has
    private int recordNumber; // the number of rows
    private List<String[]> records;

    Records(int dataNumber) {
        this.dataNumber = dataNumber;
        records = new ArrayList<String[]>();
        recordNumber = 0;
    }

    boolean addRecord(String[] toadd) {
        if (checkDuplicateRecord(toadd))    return false;
        else {
            records.add(toadd);
            recordNumber ++;
            return true;
        }
    }

    String[] getCertainRecord(int number) {
		return records.get(number);
	}

    // Looking for duplicate records
    boolean checkDuplicateRecord(String[] toadd) {
        int count = 0;
        for (String[] current : records) {
            for (int i = 0; i < toadd.length; i++) {
                if (current[i].equals(toadd[i]))
                    count ++;
            }
            if (count == toadd.length)  return true;
            else    count = 0;
        }
        return false;
    }    

    void removeCertainRecord(int number) {
        records.remove(number);
        recordNumber--;
    }

    void updateCertainRecord(int rownumber, int colnumber, String toupdate) {
        records.get(rownumber)[colnumber] = toupdate;
    }

    int getRecordsNumber() {
        return recordNumber;
    }




    //	===============TESTING===============

	public static void main(String[] args) {
		Records program = new Records(2);
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
        testInitial();
        testAddRecord();
        testDuplicate();
    }

    private void testInitial() {
        assert(dataNumber == 2);
        assert(recordNumber == 0);
    }

    private void testAddRecord() {
        assert(addRecord(new String[]{"Fido", "ab123"}));
        assert(recordNumber == 1);
        assert(addRecord(new String[]{"Wanda", "fish"}));
        assert(recordNumber == 2);
        assert(addRecord(new String[]{"Fido", "23"}));
        assert(recordNumber == 3);
        assert(!addRecord(new String[]{"Fido", "ab123"}));
        assert(recordNumber == 3);
    }

    private void testDuplicate() {
        assert(checkDuplicateRecord(new String[]{"Fido", "ab123"}));
        assert(checkDuplicateRecord(new String[]{"Fido", "23"}));
        assert(!checkDuplicateRecord(new String[]{"Fio", "ab123"}));
    }

}