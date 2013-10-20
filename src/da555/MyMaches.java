package da555;

public class MyMaches {
	MyMaches(String key,Integer value,String comment)
	{
		this.key = key;
		this.value = value;
		this.comment = comment;
	}
	public String key = null;
	public Integer value = null;
	public String comment = null;


public void Show() {
	System.out.println(key+"="+value+" // "+comment);
}
//public Integer getValue(String)
}
