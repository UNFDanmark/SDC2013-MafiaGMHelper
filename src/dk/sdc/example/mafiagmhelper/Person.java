package dk.sdc.example.mafiagmhelper;

public class Person {
	private int nightOrder;
	private String name;
	private Role role;
	private String notes;
	private boolean alive = true;
	
	public Person() {
		nightOrder = 0;
		name = "Name";
		RoleCreator rc = RoleCreator.roleList.get(0);
		role = rc.getRole();
		notes = "";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role mafiaClass) {
		role.delete();
		this.role = mafiaClass;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getNightOrder() {
		return nightOrder;
	}
	public void setNightOrder(int nightOrder) {
		this.nightOrder = nightOrder;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public void resetPerson() {
		alive = true;
		notes = "";
		role = RoleCreator.getDefaultClass();
	}
	
	
}
