package ca3hospital;

public interface IList {
	public int getSize();			//size of list
	public void add(IData obj);			//add item to list queue
	public void remove(int index);			//remove from queue
	public Boolean findRemoveNode(Node node);				//extra addition- search for node where we don't know/care about position in list 
	public Node getNode(int index);				//get node at this position
	public Node getHighestPriority();
	public Node callNext(int stagenum);			//these both provide a shortcut to quickly probe elements of a list for determining next priority
	public Boolean checkForPK(String value);			//quickly probe list to ensure no duplicate/clashing PK's
//	public void changeDataSource(IDataStorage src);				//change type of data storage source, e.g. Database, File
	//public Boolean deleteFromDataSource(String PK);		//deleted cos this isnt really List's responsibility		
}
