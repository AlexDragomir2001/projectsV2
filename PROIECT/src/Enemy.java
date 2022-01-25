import java.util.List;
import java.util.Random;

//clasa inamicilor din joc
public class Enemy extends Entity implements CellElement{
    String type;

    public Enemy(List<Spell> ablities, int currentHealth, int maxHealth, int currentMana, int maxMana, boolean fire_protection, boolean ice_protection, boolean earth_protection) {
        super(ablities, currentHealth, maxHealth, currentMana, maxMana, fire_protection, ice_protection, earth_protection);
    }


    //functie care gestioneaza damage-ul primit de un inamic
    @Override
    public void receiveDamage(int damage) {
        Random random = new Random();
        int nr = random.nextInt(100);
        if(nr >= 50) {
            int viataCurr = this.getCurrentHealth();
            int nouaViata = viataCurr - damage;
            this.setCurrentHealth(nouaViata);
        }
        else {
            System.out.println("Atacul tau a fost blocat de catre inamic!");
        }
    }

    //functie care gestioneaza damage-ul dat de inamic
    @Override
    public int getDamage() {
        int damage = 5;
        Random random = new Random();
        int nr = random.nextInt(100);
        if(nr < 50) {
            damage = damage*2;
            System.out.println("Inamicul a dat damage dublu!");
            return damage;
        }
        else
            return damage;
    }

    //functii abstracte din interfata
    @Override
    public String toCharacter() {
        return "E";
    }

    @Override
    public Enemy getEnemy() {
        return null;
    }

    @Override
    public Shop getShop() {
        return null;
    }

}
