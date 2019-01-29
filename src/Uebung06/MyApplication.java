public class MyApplication implements Controller {
	private DataStore dataStore;

	private static void fillDataStore() {

	}

	public static void main(String[] args) {

	}
}

class DataStore {
	private int[][] values;

	public DataStore(int height, int width) {

	}

	public boolean setValue(int row, int col, int value) {
		return true;
	}

	public int getValue(int row, int col) {
		return 0;
	}

	public int getHeigh() {
		return 0;
	}

	public int getWidth() {
		return 0;
	}

}

class DataStoreObserver implements Observer {
	protected DataStore dataStore;

	protected DataStoreObserver(DataStore dataStore) {

	}

	//@Override
	public void update() {

	}
}

class Subject {
	private static final int MAX_OBSERVERS = 10;
	private Observer[] observer = new Observer[MAX_OBSERVERS];

	public void attachObserver(Observer o) {

	}

	public void detachObserver(Observer o) {

	}

	public void update() {

	}

}

class ViewTable extends DataStoreObserver {
	private final static int COL_WIDTH = 7;

	protected ViewTable(DataStore datastore) {
		super(datastore);
	}

	protected static String format(int value) {
		return "";
	}

	protected static void printSeperator(int width) {

	}

}

class ViewTableLandscape extends ViewTable {
	public ViewTableLandscape(DataStore datastore) {
		super(datastore);
	}

	//@Override
	public void update() {

	}
}

class ViewTableNormal extends ViewTable {
	public ViewTableNormal(DataStore datastore) {
		super(datastore);
	}

	@Override
	public void update() {

	}
}

abstract class ViewSum extends DataStoreObserver {
	protected ViewSum(DataStore datastore) {
		super(datastore);
	}

	protected abstract int sum(int index);

}

class ViewSumColumn extends ViewSum {
	public ViewSumColumn(DataStore datastore) {
		super(datastore);
	}

	public void update() {

	}

	//@Override
	public int sum(int row) {
		return 0;
	}
}

class ViewSumRow extends ViewSum {
	public ViewSumRow(DataStore datastore) {
		super(datastore);
	}

	public void update() {

	}

	//@Override
	public int sum(int col) {
		return 0;
	}

}

interface Observer {
	public void update();
}

interface Controller {

}