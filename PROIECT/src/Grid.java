import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//clasa care alcatuieste tabla de joc sub forma unei liste de liste
// are metode statice, intrucat nu putem instantia direct un obiect de tip Grid
public class Grid extends ArrayList {
    static int length;
    static int width;
    static Character personaj = null;
    static Cell current_position;
    static ArrayList<ArrayList<Cell>> tablaExplicit;
    static ArrayList<ArrayList<String>> tablaUnknown; // lista de lista cu semnul intrebarii (?)

    public static ArrayList<ArrayList<Cell>> getTablaExplicit() {
        return tablaExplicit;
    }

    public static void setTablaExplicit(ArrayList<ArrayList<Cell>> tablaExplicit) {
        Grid.tablaExplicit = tablaExplicit;
    }

    public static ArrayList<ArrayList<String>> getTablaUnknown() {
        return tablaUnknown;
    }

    public static void setTablaUnknown(ArrayList<ArrayList<String>> tablaUnknown) {
        Grid.tablaUnknown = tablaUnknown;
    }

    //afiseaza tabla aratand doar celulele in care personajul a fost deja, iar cele nevizitate raman "?"
    public static void displayTableUnknown() {
        for(int i = 0; i < Grid.length; i++) {
            for(int j = 0; j < Grid.width; j++) {
                System.out.print(Grid.tablaUnknown.get(i).get(j));
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    //metoda care initializeaza listele de liste
    //cea unknown e cu semne de intrebare
    //cea explicita este cea care se genereaza random
    public static void setTable(ArrayList<Cell> tablaExplicit, ArrayList<Cell> tablaUnknown) {
        Grid.tablaExplicit = new ArrayList<>();
        Grid.tablaUnknown = new ArrayList<>();

        for(int i = 0; i < Grid.length; i++)
                Grid.tablaExplicit.add(new ArrayList<>());

        for(int i = 0; i < Grid.length; i++) {
            Grid.tablaUnknown.add(new ArrayList<>());
        }
    }

    public static void setLength(int length1) {
        Grid.length = length1;
    }

    public static int getLength() {
        return length;
    }

    public static int getWidth() {
        return width;
    }

    public static void setWidth(int width1) {
        Grid.width = width1;
    }

    public static Character getPersonaj() {
        return personaj;
    }

    public static void setPersonaj(Character personaj1) {
        Grid.personaj = personaj1;
    }

    public static Cell getCurrent_position() {
        return current_position;
    }

    public static void setCurrent_position(Cell current_position1) {
        Grid.current_position = current_position1;
    }

    //metoda care imi genereaza o tabla de joc cu celule random
    public static void generate_map_explicitly(int length, int width) {
        Random rand = new Random();
        int upperbound = 100;
        int random_;
        for(int i = 0; i < Grid.length; i++) {
            for (int j = 0; j < Grid.width; j++) {
                random_ = rand.nextInt(upperbound);
                if(random_ < 10) {
                    CellElement insideCell = new CellElement() {
                        @Override
                        public String toCharacter() {
                            return "F";
                        }

                        @Override
                        public Enemy getEnemy() {
                            return null;
                        }

                        @Override
                        public Shop getShop() {
                            return null;
                        }
                    };
                    Cell celula = new Cell(i, j, insideCell, false);
                    celula.setType("F");
                    Grid.tablaExplicit.get(i).add(celula);
                    continue;
                }
                if(random_ >= 10 && random_ < 50) {
                    CellElement insideCell = new CellElement() {
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
                    };
                    Cell celula = new Cell(i, j, insideCell, false);
                    celula.setType("N");
                    Grid.tablaExplicit.get(i).add(celula);
                    continue;
                }
                if(random_ >= 50 && random_ < 75) {
                    Random random = new Random();
                    int ok = 3;
                    List<Spell> abilities = null;
                    List <Spell> retine = new ArrayList<>();
                    int currentHealth = random.nextInt(30);
                    currentHealth = currentHealth + 70;
                    int currentMana = random.nextInt(30);
                    currentMana = currentMana + 70;
                    boolean fire_protection = random.nextBoolean();
                    boolean earth_protection = random.nextBoolean();
                    boolean ice_protection = random.nextBoolean();
                    Enemy enemy = new Enemy(abilities, currentHealth, 100, currentMana, 100, fire_protection, ice_protection, earth_protection);
                    Spell ice = new Ice();
                    Spell fire = new Fire();
                    Spell earth = new Earth();
                    Spell ice1 = new Ice();
                    Spell fire1 = new Fire();
                    Spell earth1 = new Earth();
                    retine.add(fire);
                    retine.add(ice);
                    retine.add(earth);
                    retine.add(fire);
                    retine.add(ice);
                    retine.add(earth);
                    int randomitem = random.nextInt(retine.size());
                    while (ok > 0) {
                        Spell randomElement = retine.get(randomitem);
                        enemy.setAblities(randomElement);
                        ok--;
                    }
                    CellElement insideCell = new CellElement() {
                        @Override
                        public String toCharacter() {
                            return "E";
                        }

                        @Override
                        public Enemy getEnemy() {
                            return enemy;
                        }
                        @Override
                        public Shop getShop() {
                            return null;
                        }
                    };
                    Cell celula = new Cell(i, j, insideCell, false);
                    celula.setType("E");
                    Grid.tablaExplicit.get(i).add(celula);
                    continue;
                }
                if(random_ >= 75 && random_ < 100) {
                    int ok = 3;
                    Potion healthP1 = new HealthPotion("HealthPotion");
                    Potion manaP1 = new ManaPotion("ManaPotion");
                    Potion manaP2 = new HealthPotion("HealthPotion");
                    List <Potion> potiuni = new ArrayList<>();
                    potiuni.add(healthP1);
                    potiuni.add(manaP1);
                    potiuni.add(manaP2);
                    Shop magazin = new Shop();
                    Random r = new Random();
                    int randomitem = r.nextInt(potiuni.size());

                    while(ok > 0) {
                        Potion randomElement = potiuni.get(randomitem);
                        magazin.setMagazinPotiuni(randomElement);
                        ok--;
                    }
                    CellElement insideCell = new CellElement() {
                        @Override
                        public String toCharacter() {
                            return "S";
                        }

                        @Override
                        public Enemy getEnemy() {
                            return null;
                        }

                        @Override
                        public Shop getShop() {
                            return magazin;
                        }
                    };
                    Cell celula = new Cell(i, j, insideCell, false);
                    celula.setType("S");
                    Grid.tablaExplicit.get(i).add(celula);
                    continue;
                }
            }
        }
    }

    // metoda care genereaza mapa hardcodata pentru test
    public static void generateHardocodatedMap(int length, int width) {
        CellElement insideCell = null;
        addHardcodateNothing(insideCell, 0, 0);
        addHardcodateNothing(insideCell, 0, 1);
        addHardcodateNothing(insideCell, 0, 2);
         addHardocateShop(insideCell, 0, 3);
        addHardcodateNothing(insideCell, 0, 4);
        addHardcodateNothing(insideCell, 1, 0);
        addHardcodateNothing(insideCell, 1, 1);
        addHardcodateNothing(insideCell, 1, 2);
        addHardocateShop(insideCell, 1, 3);
        addHardcodateNothing(insideCell, 1, 4);
        addHardcodateNothing(insideCell, 2, 0);
        addHardcodateNothing(insideCell, 2, 1);
        addHardcodateNothing(insideCell, 2, 2);
        addHardcodateNothing(insideCell, 2, 3);
        addHardcodateNothing(insideCell, 2, 4);
        addHardcodateNothing(insideCell, 3, 0);
        addHardcodateNothing(insideCell, 3, 1);
        addHardcodateNothing(insideCell, 3, 2);
        addHardcodateNothing(insideCell, 3, 3);
        addHarcodateEnemy(insideCell, 3, 4);
        addHardcodateNothing(insideCell, 4, 0);
        addHardcodateNothing(insideCell, 4, 1);
        addHardcodateNothing(insideCell, 4, 2);
        addHardcodateNothing(insideCell, 4, 3);
        addHarcodateFinal(insideCell, 4, 4);

    }

    //metoda care creeaza un inamic si il adauga in tabla de joc
    public static void addHarcodateEnemy(CellElement insideCell, int i, int j) {
        Random random = new Random();
        int ok = 3;
        List<Spell> abilities = null;
        List<Spell> retine = new ArrayList<>();
        int currentHealth = random.nextInt(30) + 70;
        int currentMana = random.nextInt(30) + 70;
        boolean fire_protection = random.nextBoolean();
        boolean earth_protection = random.nextBoolean();
        boolean ice_protection = random.nextBoolean();
        Enemy enemy = new Enemy(abilities, currentHealth, 100, currentMana, 100, fire_protection, ice_protection, earth_protection);
        Spell ice = new Ice();
        Spell fire = new Fire();
        Spell earth = new Earth();
        Spell ice1 = new Ice();
        Spell fire1 = new Fire();
        Spell earth1 = new Earth();
        retine.add(fire);
        retine.add(ice);
        retine.add(earth);
        retine.add(fire);
        retine.add(ice);
        retine.add(earth);
        int randomitem = random.nextInt(retine.size());
        while (ok > 0) {
            Spell randomElement = retine.get(randomitem);
            enemy.setAblities(randomElement);
            ok--;
        }
            insideCell = new CellElement() {
            @Override
            public String toCharacter() {
                return "E";
            }

            @Override
            public Enemy getEnemy() {
                return enemy;
            }

            @Override
            public Shop getShop() {
                return null;
            }
        };
        Cell celula = new Cell(i, j, insideCell, false);
        celula.setType("E");
        Grid.tablaExplicit.get(i).add(celula);
    }

    //metoda care creeaza un shop si il adaug in tabla de joc
    public static void addHardocateShop(CellElement insideCell, int i, int j) {
        int ok = 4;
        Potion healthP1 = new HealthPotion("HealthPotion");
        Potion manaP1 = new ManaPotion("ManaPotion");
        List <Potion> potiuni = new ArrayList<>();
        potiuni.add(healthP1);
        potiuni.add(manaP1);
        Shop magazin = new Shop();
        Random r = new Random();
        int randomitem = r.nextInt(potiuni.size());
        while(ok > 0) {
            Potion randomElement = potiuni.get(randomitem);
            magazin.setMagazinPotiuni(randomElement);
            ok--;
            randomitem = r.nextInt(potiuni.size());
        }

            insideCell = new CellElement() {
            @Override
            public String toCharacter() {
                return "S";
            }

            @Override
            public Enemy getEnemy() {
                return null;
            }

            @Override
            public Shop getShop() {
                return magazin;
            }
        };
        Cell celula = new Cell(i, j, insideCell, false);
        celula.setType("S");
        Grid.tablaExplicit.get(i).add(celula);
    }

    //metoda care creeaza o celula empty si o adauga in tabla de joc
    public static void addHardcodateNothing(CellElement insideCell, int i, int j) {
            insideCell = new CellElement() {
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
        };
        Cell celula = new Cell(i, j, insideCell, false);
        celula.setType("N");
        Grid.tablaExplicit.get(i).add(celula);
    }

    //metoda care adauga o celula de finish in tabla de joc
    public static void addHarcodateFinal(CellElement insideCell, int i, int j) {
            insideCell = new CellElement() {
            @Override
            public String toCharacter() {
                return "F";
            }

            @Override
            public Enemy getEnemy() {
                return null;
            }

            @Override
            public Shop getShop() {
                return null;
            }
        };
        Cell celula = new Cell(i, j, insideCell, false);
        celula.setType("F");
        Grid.tablaExplicit.get(i).add(celula);
    }

    //metoda care genereaza lista de liste cu "?"
    public static void generate_map_unknown(int length, int width) {
        for (int i = 0; i < Grid.length; i++) {
            for (int j = 0; j < Grid.width; j++) {
                Grid.tablaUnknown.get(i).add("?");
            }
        }

    }

    //metodele cu care utilizatorul deplaseaza personajul pe tabla de joc
    public static void goNorth() {
        Grid.getPersonaj().setLinie(Grid.getPersonaj().getLinie() - 1);
    }

    public static void goSouth() {
        Grid.getPersonaj().setLinie(Grid.getPersonaj().getLinie() + 1);
    }

    public static void goEast() {
        Grid.getPersonaj().setColoana(Grid.getPersonaj().getColoana() + 1);
    }

    public static void goWest() {
        Grid.getPersonaj().setColoana(Grid.getPersonaj().getColoana() - 1);
    }
}