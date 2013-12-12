package mvc.models;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
	private List<String> ListValues=  new ArrayList<String>();;
	
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
			if(attrbts.get(ia).getName()=="Access") setAccess(param.getAttribute("Access").getValue());
			if(attrbts.get(ia).getName()=="UoM") setUoM(param.getAttribute("UoM").getValue());
			if(attrbts.get(ia).getName()=="ParamType") setFieldType(param.getAttribute("ParamType").getValue());
			if(attrbts.get(ia).getName()=="defaultValue") setDefaultValue(param.getAttribute("defaultValue").getValue());
			if(attrbts.get(ia).getName()=="ListValues"){
				setListValues(param.getAttribute("ListValues").getValue());
			}
			
		}
	}
	public String getFieldType() {
		return fieldType;
	}

	public boolean setFieldType(String fieldType) {
		//System.out.println("pt="+fieldType);
		if(!fieldType.equals("Integer")&&!fieldType.equals("Boolean")&&!fieldType.equals("String")) return false; 
		//System.out.println("set pt="+fieldType);
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
	public boolean ListValuesEmpty() {
		return null==ListValues || ListValues.size()==0;//true;// ListValues.isEmpty();
	}

	public void setListValues(String Values) {
		//String s = "Строка, которую мы хотим разобрать на слова"; 

		StringTokenizer st = new StringTokenizer(Values, " \t\n\r,."); 

		while(st.hasMoreTokens()){

			ListValues.add(st.nextToken());

	//	System.out.println(ListValues.get(ListValues.size()-1)) ; 

		}

	//ListValues = listValues;
	}
	public void setListValues(List<String> listValues) {
		ListValues = listValues;
	}


}
