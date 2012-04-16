package dataStructures;


public class Alternative extends
		NameKeyObject<ValueMap> implements
		Comparable<Alternative> {

	public Alternative(String name, Integer key, ValueMap values) {
		super(name, key, values);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(Alternative other) {
		return this.getKey().compareTo(other.getKey());
	}

}
