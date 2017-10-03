package ca3hospital;

public class DoubleLinkedList implements IList{

	private Node head;			
	private Node tail;
	private int size;
	
	public DoubleLinkedList(){
		head=null;
		tail=null;
		size=0;
	}
	public int getSize(){			//kw: had to add, to make it easier to identify size/length. part of IList!
		return size;
	}
	
	public Boolean findRemoveNode(Node node){			//remove a node from list where we dont know/ care which position it is in
		Node comparator=head;					
		for(int i=1;i<=size;i++){
			if (comparator.equals(node)){
				remove(i); 
				return true;
				}
			else{
				comparator=comparator.getNext();
				}
		}
		return false;
	}
	
	@Override
	public Boolean checkForPK(String val) {				//cycle through list and see if it contains a matching PK already
		Node n=head;
		for(int i=1;i<=size;i++){
			if(n==null){
				return false;		//i.e. cycled through list, reached end, did not find a clashing Primary Key
			}
			else if(n.getData().getPK().equals(val)){
				return true;		//i.e. "DO NOT CREATE"! Duplicate PK found
					
			}
			else{
				n=n.getNext();
				}
			
		}
		return false;				// this shouldnt be hit I believe... but is equivalent hopefully to the (if n==null) case; reached end
	}
		
	public Node getHighestPriority(){
		Node n = null;
		int highestPriority = 0;
		int pos= 0;
		for (int i=1;i<=size;i++){			//size is an actual property in our nodes
			n=getNode(i);
				if(n.getData().getPriority()>highestPriority && n.getData().getStage()==3){		//highest priority AWAITING DOCTOR
					highestPriority=n.getData().getPriority();
					pos=i;
				}
			}
		System.out.println("getHighestPriority, posN = "+pos);
		n=getNode(pos);
		return n;
	}
	
	public Node callNext( int stage){
					Node n = head;			
			for (int i=1;i<=size;i++){
				if(n.getData().getStage()==stage){
					return n;
				}
				else{ n=n.getNext();}
			}
			return n;		
	}
	
	public Node getNode(int pos){
		Node n=null;
		for(int i=1;i<=pos;i++){
			if (i==1){		
				n=this.head;
			}
			else{	n=n.getNext();	}	
		}
		return n;
	}
	
	public void add(IData data){		
		if (head==null){
			head= new Node (data,null,null);
			tail=head;
		}
		else{
			Node new_node= new Node (data,tail,null);
			tail.setNext(new_node);
			tail=new_node;
		}
		size+=1;
	}
	
	public void remove(int pos){
		if(pos==1){						 // if=1, remove the first
			Node n=getNode(1);
			this.head=n.getNext();
			if(head!=null){					//error when only one element in list. had to add this line later!
				head.setPrev(null);
			}
		}
		else if(pos==size && size>1){ 				//remove last
			Node n = tail;
			this.tail= n.getPrev();
			tail.setNext(null);
		}
		else {							//remove middle
			Node current= getNode(pos);
			current.getPrev().setNext(current.getNext());		
			current.getNext().setPrev(current.getPrev());
		}
		size-=1;
	}
	
	public String toString(){
		String output= "";
		
		if(head!=null){
			Node n=head;
			while (n!=null){
				output+="***"+n.getData().toString()+"***";
				n=n.getNext();
			}
		}
		return output;
	}
	
}
