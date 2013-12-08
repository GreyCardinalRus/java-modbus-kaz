package mvc.models;

//import org.w3c.dom.Document;
//import org.w3c.dom.NamedNodeMap;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.InputSource;
//import org.xml.sax.SAXException;
// 
//import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class ModbusParameter {
	private int id=0;
	private String fieldType;
	private String name = "";
	private String description= "";
	private String value="";
	private String defaultValue="";
	//private boolean boolValue;
	private int minIntValue;
	private int maxIntValue;
	
	
	public String getFieldType() {
		return fieldType;
	}

	public boolean setFieldType(String fieldType) {
		if(fieldType!="Integer"&&fieldType!="Boolean"&&fieldType!="String") return false; 
		this.fieldType = fieldType; return true;
	}

	public boolean SetValue(int newValue){
		if(fieldType!="int"||newValue<minIntValue||newValue>maxIntValue)
			return false;
		value= ""+newValue;
		return true;
	}
	public boolean SetDefaultValue(int newValue){
		if(fieldType!="int"||newValue<minIntValue||newValue>maxIntValue)
			return false;
		setDefaultValue(""+newValue);
		return true;
	}
	public boolean SetValue(Boolean newValue){
		if(fieldType!="boolean")
			return false;
		value= (newValue?"True":"false");
		return true;
	}
	public boolean GetBooleanValue() {
		return (value=="True"?true:false);
	}
	public int GetIntValue() {
		return Integer.parseInt(value);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public boolean readFromFile(String Filename) {
		
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

}
