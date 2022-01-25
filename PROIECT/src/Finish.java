
//clasa care reprezinta un tip de celula
public class Finish implements CellElement{
    String type;

    public Finish(String type) {
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


    public void setType(String type) {
        this.type = type;
    }
}
