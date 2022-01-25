public class ManaPotion implements Potion{
    String name;

    public ManaPotion(String name) {
        this.name = name;
    }

    public String name() {
        return this.name;
    }

    //metoda de utilizare a potiunii
    @Override
    public void usePotion(Character character) {
        character.regenerateMana(getValueOfRegen());
    }


    //pretul potiunii
    @Override
    public int getPrice() {
        return 20;
    }

    //metoda cu valoare de regen a potiunii
    @Override
    public int getValueOfRegen() {
        return 20;
    }

    //metoda cu spatiul pe care il ocupa potiunea in inventar
    @Override
    public int getWeightOfPotion() {
        return 1;
    }
}
