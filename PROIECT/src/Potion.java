
// interfata care gestioneaza potiunile
public interface Potion {
    public String name();
    public void usePotion(Character character);
    public int getPrice();
    public int getValueOfRegen();
    public int getWeightOfPotion();
}
