import java.util.List;
import java.util.Random;

// clasa care reprezinta profesia unui personaj cu atributele caracteristice
public class Warrior extends Character{

    public Warrior(List<Spell> ablities, int currentHealth, int maxHealth, int currentMana, int maxMana, boolean fire_protection, boolean ice_protection, boolean earth_protection, String name, int linie, int coloana, Inventory potionInventory, int experience, int level, int strength, int charisma, int dexterity, int greutate_inventar) {
        super(ablities, currentHealth, maxHealth, currentMana, maxMana, fire_protection, ice_protection, earth_protection, name, linie, coloana, potionInventory, experience, level, strength, charisma, dexterity, greutate_inventar);
    }

    // damage-ul pe care il primeste de la inamicii in functie de atribute
    @Override
    public void receiveDamage(int damage) {
        Random random = new Random();
        if(this.getDexterity() > 10 && this.getCharisma() > 10) {
            int nr = random.nextInt(100);
            if(nr <= 40) {
                damage = damage / 2;
                System.out.println("Damage-ul dat de inamic a fost injumatatit!");
                this.setCurrentHealth(this.getCurrentHealth() - damage);
            }
            else {
                this.setCurrentHealth(this.getCurrentHealth() - damage);
            }
        }
        else {
            this.setCurrentHealth(this.getCurrentHealth() - damage);
        }
    }

    //damage-ul pe care il da inamicilor in functie de atribute
    @Override
    public int getDamage() {
        Random random = new Random();
        if(this.getStrength() >= 20) {
            int nr = random.nextInt(100);
            if(nr <= 50) {
                System.out.println("L-ai lovit pe inamic cu damage dublu");
                return 40;
            }
            else
                return 20;
        }
        if(this.getStrength() >= 10) {
            int nr = random.nextInt(100);
            if(nr <= 50) {
                System.out.println("L-ai lovit pe inamic cu damage dublu");
                return 30;
            }
            else
                return 15;
        }
        else return 15;
    }
}
