/*
 * This interface will be implemented in classes
 * that will be observed by other classes
 */
public interface InterObservable {
	
	public void add(InterObserver o);
	public void remove(InterObserver o);
	public void notifyObservers();
	
}
