package aditionalsDataTypes;

public class Node<T> {
	T info;
	Node<T> next;
	public Node(T info) {
		this.info=info;
		this.next=null;
	}
	public Node(T info,Node<T> next) {
		this.info=info;
		this.next=next;
	}
	public Node(Node<T> copy) {
		this.info=copy.info;
		this.next=copy.next;
	}
	public T getInfo() {
		return info;
	}
	public void setInfo(T info) {
		this.info = info;
	}
	public Node<T> getNext() {
		return next;
	}
	public void setNext(Node<T> next) {
		this.next = next;
	}
	@Override
	public String toString() {
		return info.toString();
	}
}
