package api;

public class Player {
	
	
	private int Number;
	private String Name;
	private String Position;
	
	public int getNumber() {
		return Number;
	}
	public void setNumber(int number) {
		this.Number = Number;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}

	@Override
	public String toString() {
		return "Number:" + Number + " Name:" + Name + " Position:" + Position + ",\n";
	}
}
