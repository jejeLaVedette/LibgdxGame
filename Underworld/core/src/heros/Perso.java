package heros;

public class Perso {
	
	private int hpMax;
	private int hpCurrent;
	private int manaMax;
	private int manaCurrent;
	private String name;
	
	// Penser a ajouter l image quand je passerai a ligbx
	public Perso(int hpMax, int manaMax, String name){
		this.setHpMax(hpMax);
		this.setHpCurrent(hpMax);
		this.setManaMax(manaMax);
		this.setManaCurrent(manaMax);
		this.setName(name);
	}
	
	public void lvlUp(){
		this.setHpMax(this.getHpMax()+200);
		this.setManaMax(this.getManaMax()+50);
		this.setHpCurrent(this.getHpMax());
		this.setManaCurrent(this.getManaMax());
	}
	

	public int getHpMax() {
		return hpMax;
	}

	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
	}

	public int getManaMax() {
		return manaMax;
	}

	public void setManaMax(int manaMax) {
		this.manaMax = manaMax;
	}

	public int getHpCurrent() {
		return hpCurrent;
	}

	public void setHpCurrent(int hpCurrent) {
		this.hpCurrent = hpCurrent;
	}

	public int getManaCurrent() {
		return manaCurrent;
	}

	public void setManaCurrent(int manaCurrent) {
		this.manaCurrent = manaCurrent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
