package fm9;

public interface Subscribe {
	public void addSubscribe(Copy exs);
	public void removeSubscribe(Copy exs);
	public void notifySubscribes();
}
