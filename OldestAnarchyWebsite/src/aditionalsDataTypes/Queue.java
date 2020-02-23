package aditionalsDataTypes;

public class Queue<T> {
	Node<T> first;
	Node<T> last;
	public Queue() {
		this.first=null;
		this.last=null;
	}
	public boolean isEmpty() {
		return this.first==null;
	}
	public synchronized void insert(T x) {
		if(this.isEmpty()) {
			this.last=new Node<T>(x);
			this.first=this.last;
		}
		else {
			this.last.setNext(new Node<T>(x));
			this.last=this.last.getNext();
		}
	}
	public T head() {
		return this.first.getInfo();
	}
	
	public synchronized T remove() {
		T x=this.first.getInfo();
		this.first=this.first.getNext();
		return x;
	
	}
	public String toString() {
		String str="[";
		Node<T> pos=this.first;
		while(pos!=null) {
			if(!str.equals("[")) {
				str+=" , "+pos.getInfo().toString();
			}else {
				str+=pos.getInfo().toString();
			}
			pos=pos.getNext();
		}
		str+="]";
		return str;
	}
	public void reverse() {
		if(this.isEmpty()) {
			return ;
		}
		else {
			T x=this.remove();
			this.reverse();
			this.insert(x);
			return;
		}
	}


}
