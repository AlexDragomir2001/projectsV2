import java.util.List;

// clasa care contine coordonatele curente ale personajului, inventarul acestuia si detalii despre el
public abstract class Character extends Entity{
    String name;
    int linie;
    int coloana;
    Inventory potionInventory = null;
    int experience;
    String profesie;
    int level;
    int strength;
    int charisma;
    int dexterity;
    int greutate_inventar;

    public Character(List<Spell> ablities, int currentHealth, int maxHealth, int currentMana, int maxMana, boolean fire_protection,
                     boolean ice_protection, boolean earth_protection, String name, int linie, int coloana, Inventory potionInventory,
                     int experience, int level, int strength, int charisma, int dexterity, int greutate_inventar) {
        super(ablities, currentHealth, maxHealth, currentMana, maxMana, fire_protection, ice_protection, earth_protection);
        this.name = name;
        this.linie = linie;
        this.coloana = coloana;
        this.potionInventory = new Inventory();
        this.experience = experience;
        this.level = level;
        this.strength = strength;
        this.charisma = charisma;
        this.dexterity = dexterity;
        this.greutate_inventar = greutate_inventar;
    }

    public String getProfesie() {
        return profesie;
    }
    public int getLinie() {
        return linie;
    }

    public String getName() {
        return name;
    }

    public int getGreutate_inventar() {
        return greutate_inventar;
    }

    public void setProfesie(String profesie) {
        this.profesie = profesie;
    }

    public void setGreutate_inventar(int greutate_inventar) {
        this.greutate_inventar = greutate_inventar;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setLinie(int linie) {
        this.linie = linie;
    }

    public int getColoana() {
        return coloana;
    }

    public void setColoana(int coloana) {
        this.coloana = coloana;
    }

    public Inventory getPotionInventory() {
        return potionInventory;
    }

    public void setPotionInventory(Inventory potionInventory) {
        this.potionInventory = potionInventory;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Character character = (Character) o;
        return linie == character.linie && coloana == character.coloana && experience == character.experience && level == character.level && strength == character.strength && charisma == character.charisma && dexterity == character.dexterity && greutate_inventar == character.greutate_inventar && name.equals(character.name) && potionInventory.equals(character.potionInventory) && profesie.equals(character.profesie);
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", linie=" + linie +
                ", coloana=" + coloana +
                ", potionInventory=" + potionInventory +
                ", experience=" + experience +
                ", level=" + level +
                ", strength=" + strength +
                ", charisma=" + charisma +
                ", dexterity=" + dexterity +
                '}';
    }

    // metoda care cumpara o potiune
    void buyPotion(Cell cell, int nr) {

        if(this.getPotionInventory().getMoney() >= cell.getWhatsInside().getShop().getMagazinPotiuni().get(nr).getPrice() &&
            this.getGreutate_inventar() >= cell.getWhatsInside().getShop().getMagazinPotiuni().get(nr).getWeightOfPotion()) {

            this.getPotionInventory().getLista_potiuni().add(cell.getWhatsInside().getShop().getMagazinPotiuni().get(nr));
            this.getPotionInventory().setMoney(this.getPotionInventory().getMoney() - cell.getWhatsInside().getShop().getMagazinPotiuni().get(nr).getPrice());
            this.setGreutate_inventar(this.getGreutate_inventar() - cell.getWhatsInside().getShop().getMagazinPotiuni().get(nr).getWeightOfPotion());
        }
    }

}
