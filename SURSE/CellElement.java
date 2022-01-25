
//interfata care intoarce un inamic sau un shop (in functie de tipul celulei) si tipul celulei
public interface CellElement {

    public String toCharacter();
    public Enemy getEnemy();
    public Shop getShop();
}
