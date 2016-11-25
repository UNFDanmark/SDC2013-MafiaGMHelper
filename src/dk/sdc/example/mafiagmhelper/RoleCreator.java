package dk.sdc.example.mafiagmhelper;

import java.util.ArrayList;

import android.R.color;

public class RoleCreator {

	public static ArrayList<RoleCreator> roleList = new ArrayList<RoleCreator>();
	
	static {
		roleList.add(new RoleCreator(Villager.class, "Villager", false, false, true));
		roleList.add(new RoleCreator(Healer.class, "Healer", false, false, false));
		roleList.add(new RoleCreator(Mafia.class, "Mafia", true, false, true));
		
		
	}
	public static Role getDefaultClass() {
		return roleList.get(0).getRole();
	}
	
	
	public static String[] toNameArray() {
		String[] out = new String[roleList.size()+1];
		for (int i=0; i < roleList.size(); i++) {
			out[i] = roleList.get(i).roleName;
		}
		out[roleList.size()] = "New Role";
		return out; 
	}
	
	private Class<Role> roleClass;
	private String roleName;
	private boolean evil;
	private boolean firstNight;
	private boolean inGroup; //true if all roles of this type wakes at the same time
	private color bg;
	private int rolesInGame;
	
	
	private ArrayList<Role> rolesMade;
	
	
	public RoleCreator(Class<?> roleClass, String roleName, boolean evil,
			boolean firstNight, boolean inGroup) {
		super();
		this.roleClass = (Class<Role>) roleClass;
		this.roleName = roleName;
		this.evil = evil;
		this.firstNight = firstNight;
		this.inGroup = inGroup;
		rolesInGame = 0;
		rolesMade = new ArrayList<Role>(); 
	}
	
	public Role getRole() {
		try {
			Role r = roleClass.newInstance();
			r.init(this, roleName, evil, firstNight);
			rolesMade.add(r);
			return r;
		} catch (Exception e) {
			return null;
		}
	}
	
	public String getName() {
		return roleName;
	}
	public void setRoleName(String s) {
		roleName = s;
	}
	public boolean isEvil() {
		return evil;
	}


	public void setEvil(boolean evil) {
		this.evil = evil;
	}


	public boolean isFirstNight() {
		return firstNight;
	}


	public void setFirstNight(boolean firstNight) {
		this.firstNight = firstNight;
	}


	public void setInGroup(boolean inGroup) {
		this.inGroup = inGroup;
	}


	public int getRolesInGame() {
		return rolesInGame;
	}
	public void setRolesInGame(int val) {
		rolesInGame = val;
	}
	public boolean isInGroup() {
		return inGroup;
	}
	
	public void deleteRole(Role r) {
		rolesMade.remove(r);
	}
	public String getRoleNameSpecifier(Role r) {
		if (inGroup || rolesMade.size() <= 1) {
			return "";
		} else {
			return " " + (rolesMade.indexOf(r)+1);
		}
	} 

	
}
