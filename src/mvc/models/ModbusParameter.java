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
	private int numberParam=0;
	private String fieldType;
	private int intValue;
	private boolean boolValue;
	private int minIntValue;
	private int maxIntValue;
	
	
	public String getFieldType() {
		return fieldType;
	}

	public boolean setFieldType(String fieldType) {
		if(fieldType!="int"&&fieldType!="boolean") return false; 
		this.fieldType = fieldType; return true;
	}

	public boolean SetValue(int newValue){
		if(fieldType!="int"||newValue<minIntValue||newValue>maxIntValue)
			return false;
		intValue= newValue;
		return true;
	}
	public boolean SetValue(Boolean newValue){
		if(fieldType!="boolean")
			return false;
		boolValue= newValue;
		return true;
	}
	public boolean GetBooleanValue() {
		return boolValue;
	}
	public int GetIntValue() {
		return intValue;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int getNumberParam() {
		return numberParam;
	}

	public void setNumberParam(int numberParam) {
		this.numberParam = numberParam;
	}
	public boolean readFromFile(String Filename) {
		
		return true;
	}

}
