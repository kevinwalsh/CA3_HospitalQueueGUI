package ca3hospital;

public class Node {
	private IData data;					
	private Node next;
	private Node prev;

	public Node (IData data,Node prev, Node next){
		this.data=data;
		this.prev=prev;
		this.next=next;
	}
	
	public IData getData(){
		return data;
	}
	public void setData (IData data){
		this.data = data;
	}
	public Node getNext(){
		return next;
	}
	public void setNext (Node next){			
		this.next=next;
	}
	public Node getPrev(){
		return prev;
	}
	public void setPrev (Node prev){	
		this.prev=prev;
	}
	
}
