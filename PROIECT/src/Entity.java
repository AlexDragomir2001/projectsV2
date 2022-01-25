import java.util.ArrayList;
import java.util.List;

// clasa care reprezinta o entitate din joc (atat personajul utilizatorului, cat si inamicii)
public abstract class Entity {
    List <Spell> ablities;
    int currentHealth;
    int maxHealth;
    int currentMana;
    int maxMana;
    boolean fire_protection;
    boolean ice_protection;
    boolean earth_protection;

    public Entity(List<Spell> ablities, int currentHealth, int maxHealth, int currentMana, int maxMana, boolean fire_protection, boolean ice_protection, boolean earth_protection) {
        this.ablities = ablities;
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;
        this.currentMana = currentMana;
        this.maxMana = maxMana;
        this.ablities = new ArrayList<>();
        this.fire_protection = fire_protection;
        this.ice_protection = ice_protection;
        this.earth_protection = earth_protection;
    }


    public List<Spell> getAblities() {
        return ablities;
    }

    public void setAblities(Spell abilitate) {
        this.ablities.add(abilitate);
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public boolean isFire_protection() {
        return fire_protection;
    }

    public void setFire_protection(boolean fire_protection) {
        this.fire_protection = fire_protection;
    }

    public boolean isIce_protection() {
        return ice_protection;
    }

    public void setIce_protection(boolean ice_protection) {
        this.ice_protection = ice_protection;
    }

    public boolean isEarth_protection() {
        return earth_protection;
    }

    public void setEarth_protection(boolean earth_protection) {
        this.earth_protection = earth_protection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return currentHealth == entity.currentHealth && maxHealth == entity.maxHealth && currentMana == entity.currentMana && maxMana == entity.maxMana && fire_protection == entity.fire_protection && ice_protection == entity.ice_protection && earth_protection == entity.earth_protection && ablities.equals(entity.ablities);
    }


    @Override
    public String toString() {
        return "Entity{" +
                "ablities=" + ablities +
                ", currentHealth=" + currentHealth +
                ", maxHealth=" + maxHealth +
                ", currentMana=" + currentMana +
                ", maxMana=" + maxMana +
                ", fire_protection=" + fire_protection +
                ", ice_protection=" + ice_protection +
                ", earth_protection=" + earth_protection +
                '}';
    }

    //functie de regenerare a vietii cu o potiune
    void regenerateHealth(int n) {
        int newHealth = this.getCurrentHealth() + n;
        if(newHealth > 100)
                this.setCurrentHealth(100);
        else
            this.setCurrentHealth(newHealth);
    }

    //functie de regenare a manei cu o potiune
    void regenerateMana(int n) {
        int newMana = this.getCurrentMana() + n;
        if(newMana > 100)
            this.setCurrentMana(100);
        else
            this.setCurrentMana(newMana);
    }

    //functie de utilizare a unui spell (utilizata si de inamici, dar si de personaj)
    void useSpell(Spell abilitate, Enemy inamic, Character character, int ok) {
        if(ok == 1) {
            if (character.getCurrentMana() >= abilitate.manaNeededCharac()) {
                inamic.receiveDamage(abilitate.damageToEnemy(character));
            }
        }
        else {
            if(inamic.getCurrentMana() >= abilitate.manaNeededEnemy()) {
                character.receiveDamage(abilitate.damageToCharacter(character));
            }
            else {
                System.out.println("Inamicul nu are suficenta mana pentru abilitate! Va ataca normal!");
                character.receiveDamage(inamic.getDamage());
            }
        }
    }

    //metode abstracte implementate de Warrior, Rogue si Mage
    abstract public void receiveDamage(int damage);
    abstract public int getDamage();
}
