
//metoda care reprezinta o potiune de viata
public class HealthPotion implements Potion{
    String name;

    public HealthPotion(String name) {
        this.name = name;
    }

    public String name() {
        return this.name;
    }

    //pretul potiunii
    @Override
    public int getPrice() {
        return 30;
    }

    //metoda de utilizare a potiunii
    @Override
    public void usePotion(Character character) {
        character.regenerateHealth(getValueOfRegen());
    }

    //metoda cu valoarea de regen potiunii
    @Override
    public int getValueOfRegen() {
        return 30;
    }

    //metoda cu spatiul pe care il ocupa potiunea in inventar
    @Override
    public int getWeightOfPotion() {
        return 2;
    }
}
