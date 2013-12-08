package mvc.models;

import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.DataConversionException;
import org.jdom2.Element;

public class ModbusParameter {
	private int id=0;
	private String fieldType="";
	private String name = "";
	private String description= "";
	private String value="";
	private String defaultValue="";
	private int minIntValue;
	private int maxIntValue;
	private String UoM;
	private String Access;
	private List<String> ListValues;
	
	public ModbusParameter() {
		
	}
	public ModbusParameter(Element param) {
		List<Attribute> attrbts = param.getAttributes();
		
		for (int ia = 0; ia < attrbts.size(); ++ia) {
			//System.out.print(" " + attrbts.get(ia).getName()
			//		+ " = " + attrbts.get(ia).getValue());
			if(attrbts.get(ia).getName()=="id")
				try {
					setId(param.getAttribute("id").getIntValue());
				} catch (DataConversionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(attrbts.get(ia).getName()=="Name") setName(param.getAttribute("Name").getValue());
			if(attrbts.get(ia).getName()=="Description") setDescription(param.getAttribute("Description").getValue());
		}
	}
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

	public String getUoM() {
		return UoM;
	}

	public void setUoM(String uoM) {
		UoM = uoM;
	}

	public String getAccess() {
		return Access;
	}

	public void setAccess(String access) {
		Access = access;
	}

	public List<String> getListValues() {
		return ListValues;
	}
	public void setListValues(String Values) {
		//ListValues = listValues;
	}
	public void setListValues(List<String> listValues) {
		ListValues = listValues;
	}

}
