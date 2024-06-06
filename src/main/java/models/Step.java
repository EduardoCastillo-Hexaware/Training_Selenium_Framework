package models;

public class Step {
	
	public String name;
	public String description;
	//boolean status;

	public Step(String name) {
		this.name = name;
	}
	
	public void executeStep() throws Exception {}

}
