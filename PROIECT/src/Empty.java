
//clasa care reprezinta o celula goala
public class Empty implements CellElement{
    String type;

    public Empty(String type) {
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toCharacter() {
        return "N";
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
