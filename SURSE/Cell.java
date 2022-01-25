
// clasa care contine coordonatele Ox si Oy pe harta, tipul celulei, vizitarea acesteia, si un obiect
// de tip CellElement
public class Cell {
    int x;
    int y;
    enum enumVar {
        EMPTY,
        ENEMY,
        SHOP,
        FINISH
    }
    enumVar type;
    CellElement whatsInside;
    boolean condition;

    public Cell(int x, int y, CellElement whatsInside, boolean condition) {
        this.x = x;
        this.y = y;
        this.whatsInside = whatsInside;
        this.condition = condition;
    }

    public enumVar getType() {
        return type;
    }

    public CellElement getWhatsInside() {
        return whatsInside;
    }

    // functie care intializeaza variabila enum
    public void setType(String type) {
        enumVar empty = enumVar.EMPTY;
        enumVar finish = enumVar.FINISH;
        enumVar enemy  = enumVar.ENEMY;
        enumVar shop = enumVar.SHOP;
        if(type.equals("F"))
            this.type = finish;
        if(type.equals("N"))
            this.type = empty;
        if(type.equals("E"))
            this.type = enemy;
        if(type.equals("S"))
            this.type = shop;
    }

}
