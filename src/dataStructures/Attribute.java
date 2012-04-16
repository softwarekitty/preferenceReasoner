package dataStructures;

public class Attribute extends NameKeyObject<Domain>implements Comparable<Attribute>{
private AttributeKey attributeKey;
	
	public Attribute(String name, AttributeKey key, Domain object) {
		super(name, key.getKey(), object);
		attributeKey= key;
	}

	@Override
	public int compareTo(Attribute other) {
		return this.getName().toLowerCase().compareTo(other.getName().toLowerCase());
	}
	
	public AttributeKey getAttributeKey(){
		return attributeKey;
	}

}
