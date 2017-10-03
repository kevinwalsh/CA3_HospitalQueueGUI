package ca3hospital;

public interface IDataStorage {

	void Connect(String connection);
	void Save(IData object);		//actually should prob just save one-by-one really! never a block!
	void Load(IList list);		//	NEW load method	- just pass in list and populate it there
	void Delete(String pk);
	void SaveSession(IList mylist);		//block save; distinguishes between required INSERT and DELETE
}
