package dataStructures;

public class Document {

	private AttributeMap attributeMap;
	private AlternativeMap alternativeMap;
	private MetaData metaData;

	public Document() {
		attributeMap = new AttributeMap();
		alternativeMap = new AlternativeMap();
		metaData = new MetaData();
	}
	
	public AttributeMap getAttributeMap(){
		return attributeMap;
	}
	
	public AlternativeMap getAlternativeMap(){
		return alternativeMap;
	}
	
	public MetaData getMetaData(){
		return metaData;
	}
}
