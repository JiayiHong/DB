import java.util.*;

class Records {
    private int dataNumber; // the number of data every row has
    private int recordNumber; // the number of rows
    private List<String[]> records;
    private int[] keys;

    Records(int dataNumber) {
        this.dataNumber = dataNumber;
        records = new ArrayList<String[]>();
        recordNumber = 0;
    }

    void setKeys(int... keys) {
        this.keys = keys;
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
        // if (records == null) return false;
        for (String[] current : records) {
            for (int check : keys) {
                if (toadd[check].equals(current[check]))
                    count ++;
            }
            if (count == keys.length) return true;
            else count = 0;
        }
        return false;
    }

    boolean checkDuplicateUpdate(String[] toupdate) {
        int number = 0;
        int count = 0;
        // if (records == null) return false;
        for (String[] current : records) {
            for (int check : keys) {
                if (toupdate[check].equals(current[check]))
                count ++;
            }
            if (count == keys.length) number++;
            count = 0;
        }
        if (number >= 2) return true;
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
        setKeys(0);
        assert(dataNumber == 2);
        assert(recordNumber == 0);
    }

    private void testAddRecord() {
        assert(addRecord(new String[]{"Fido", "ab123"}));
        assert(recordNumber == 1);
        assert(addRecord(new String[]{"Wanda", "fish"}));
        assert(recordNumber == 2);
        assert(addRecord(new String[]{"Fdo", "ab123"}));
        assert(recordNumber == 3);
        assert(!addRecord(new String[]{"Fido", "ab123"}));
        assert(recordNumber == 3);
    }

    private void testDuplicate() {
        assert(checkDuplicateRecord(new String[]{"Fido", "ab"}));
        assert(checkDuplicateRecord(new String[]{"Fido", "23"}));
        assert(!checkDuplicateRecord(new String[]{"Fio", "ab123"}));
        assert(!checkDuplicateUpdate(new String[]{"Fido","ab123"}));
    }

}