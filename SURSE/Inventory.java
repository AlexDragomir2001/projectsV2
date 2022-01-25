import java.util.ArrayList;
import java.util.List;

public class Inventory {
    List<Potion> lista_potiuni;
    int max_weight;
    int money;

    public List<Potion> getLista_potiuni() {
        return lista_potiuni;
    }

    public void createList() {
        this.lista_potiuni = new ArrayList<>();
    }

    public void addPotion(Potion potiune) {
        this.lista_potiuni.add(potiune);
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void deletePotion(Potion potiune) {
        this.lista_potiuni.remove(potiune);
    }

    public int getMax_weight() {
        return max_weight;
    }

    public void setMax_weight(int max_weight) {
        this.max_weight = max_weight;
    }

}
