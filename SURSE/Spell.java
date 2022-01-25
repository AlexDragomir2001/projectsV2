
//clasa abastracta care gestioneaza utilizare potiunilor
public abstract class Spell {
    int damage;
    int mana_cost;

    abstract String getName();
    abstract int damageToCharacter(Character character);
    abstract int manaNeededEnemy();
    abstract int damageToEnemy(Character character);
    abstract int manaNeededCharac();

}
