public class SystemLogs extends Admin{
	private String name;

	public SystemLogs(String name){
		this.name = name;
	}
	public void display(){
		System.out.println(systemlogs.get());
	}
}