package dk.sdc.example.mafiagmhelper;


import android.graphics.Color;

public class Role {
	private String roleName;
	private boolean evil;
	private boolean firstNight;
	private RoleCreator parent;

	public String getRoleName() {
		return roleName + parent.getRoleNameSpecifier(this);
	}
	public boolean isEvil() {
		return evil;
	}
	public int getColor() {
		return evil ? Color.RED : Color.GREEN;
	}
	
	
	public void init(RoleCreator parent, String roleName, boolean evil, boolean firstNight) {
		this.parent = parent;
		this.roleName = roleName;
		this.evil = evil;
		this.firstNight = firstNight;
		
	}
	public void delete() {
		parent.deleteRole(this);
	}
	
	
}
