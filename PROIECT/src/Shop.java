import java.util.ArrayList;
import java.util.List;

// clasa care reprezinta un magazin
public class Shop implements CellElement{
    String type;
    List<Potion> magazinPotiuni;

    public Shop() {
        this.magazinPotiuni = new ArrayList<>();
    }

    public void setMagazinPotiuni(Potion potiune) {
        this.magazinPotiuni.add(potiune);
    }

    public List<Potion> getMagazinPotiuni() {
        return magazinPotiuni;
    }

    public Shop(String type) {
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toCharacter() {
        return type;
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
