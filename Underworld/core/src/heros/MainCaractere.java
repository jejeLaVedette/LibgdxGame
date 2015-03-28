package heros;

public class MainCaractere extends Perso{

	private String weapons;
	private String armor;
	private int xpMax;
	private int manaMax;
	private int xpCurrent;
	private int manaCurrent;
	private int lvlCurrent;

	public MainCaractere(int hpMax, int manaMax, String name, int lvl) {
		super(hpMax, manaMax, name);
		// TODO Auto-generated constructor stub
		this.xpMax=hpMax;
		this.manaMax=manaMax;
		setLvlCurrent(lvl);
	}

	public String getWeapons() {
		return weapons;
	} 

	/*
	 * augmente l xp du perso, le fait monter de lvl autant de fois qu il faut et redefini l xp max et xpCurrent
	 */

	public void setXp(int xpUp){
		this.xpCurrent=this.xpCurrent+xpUp;
		if (this.xpCurrent>=xpMax){
			while(this.xpCurrent>=xpMax){
				this.lvlUp();
				this.xpMax=this.xpMax*2;
				this.setLvlCurrent(this.getLvlCurrent() + 1);
			}
			this.xpCurrent=this.xpCurrent-this.xpMax/2;
		}

	}

	public void setWeapons(String weapons) {
		this.weapons = weapons;
	}

	public String getArmor() {
		return armor;
	}

	public void setArmor(String armor) {
		this.armor = armor;
	}

	public int getXpMax() {
		return xpMax;
	}

	public void setXpMax(int xpMax) {
		this.xpMax = xpMax;
	}

	public int getXpCurrent() {
		return xpCurrent;
	}

	public void setXpCurrent(int xpCurrent) {
		this.xpCurrent = xpCurrent;
	}

	public int getLvlCurrent() {
		return lvlCurrent;
	}

	public void setLvlCurrent(int lvlCurrent) {
		this.lvlCurrent = lvlCurrent;
	}

}
