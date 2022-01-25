import java.io.FileReader;
import java.util.*;

// clasa in care este implementat mare parte din joc
public class Game {
    List<Account> listAccounts;
    HashMap<String, List <String>> map;

    private static Game SingleObjectOfGame = new Game();

    private Game(){}

    public static Game getSingleObjectOfGame() {
        return SingleObjectOfGame;
    }

    public List<Account> getListAccounts() {
        return listAccounts;
    }

    public void initializeList() {
        this.listAccounts = new ArrayList<>();
    }

    public void initializeMap() {
        this.map = new HashMap<>();
    }

    public void setList(Account account) {
        this.listAccounts.add(account);
    }

    public HashMap <String, List<String>> getMap() {
        return map;
    }

    public void setMap(String type, List<String> value) {
        this.map.put(type, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return listAccounts.equals(game.listAccounts) && map.equals(game.map);
    }

    @Override
    public String toString() {
        return "Game{" +
                "list=" + listAccounts +
                ", map=" + map +
                '}';
    }

    /*metoda care preia din JSON-urile story si accounts toate informatiile, creeaza obiectele de tip Account,
    * Information si Credentials cu toate campurile aferente, realizeaza autentificare jucatorului si alegerea
    * unui personaj din contul respectiv si apeleaza metoda playTheGame unde utilizatorul incepe sa joace jocul */
    public void run () {
        try {
            Credentials credentials1;
            Account.Information info;
            Account account;
            org.json.simple.parser.JSONParser jsonparser = new org.json.simple.parser.JSONParser();
            FileReader reader = new FileReader("C:\\Users\\User\\IdeaProjects\\Tema_2_POO\\src\\accounts.json");
            Object obj = jsonparser.parse(reader);
            org.json.simple.JSONObject empjsonobj = (org.json.simple.JSONObject) obj;
            org.json.simple.JSONArray array = (org.json.simple.JSONArray) empjsonobj.get("accounts");

            //parcurg obiectul "accounts"
            for (int i = 0; i < array.size(); i++) {
                org.json.simple.JSONObject accounts = (org.json.simple.JSONObject) array.get(i);
                String name = (String) accounts.get("name");
                String country = (String) accounts.get("country");
                String maps_colected = (String) accounts.get("maps_completed");
                org.json.simple.JSONObject credentials = (org.json.simple.JSONObject) accounts.get("credentials");
                String email = (String) credentials.get("email");
                String password = (String) credentials.get("password");
                org.json.simple.JSONArray array_games = (org.json.simple.JSONArray) accounts.get("favorite_games");

                List<String> jocuri_sortate = new ArrayList<>();

                //introduc intr-o lista jocurile
                for (int k = 0; k < array_games.size(); k++) {
                    jocuri_sortate.add((String) array_games.get(k));
                }
                Collections.sort(jocuri_sortate);

                //introduc in liste personajele si informatiile despre fiecare
                org.json.simple.JSONArray array_2 = (org.json.simple.JSONArray) accounts.get("characters");
                List<String> personaje_cont = new ArrayList<>();
                List <Long> experienta = new ArrayList<>();
                List <String> nivel = new ArrayList<>();
                List <String> profesie = new ArrayList<>();

                //preiau informatiile despre fiecare personaj
                for (int j = 0; j < array_2.size(); j++) {
                    org.json.simple.JSONObject characters = (org.json.simple.JSONObject) array_2.get(j);
                    String name_charct = (String) characters.get("name");
                    String profession = (String) characters.get("profession");
                    String level = (String) characters.get("level");
                    Long experience = (Long) characters.get("experience");
                    personaje_cont.add(name_charct);
                    experienta.add(experience);
                    nivel.add(level);
                    profesie.add(profession);
                }

                // creez in final obiectele claselor ajutandu-ma de listele formate mai sus
                int ceva = Integer.parseInt(maps_colected);
                credentials1 = new Credentials(email, password);
                info = new Account.Information(credentials1, jocuri_sortate, name, country);
                account = new Account(info, ceva);

                //adaug in listele din clasa Accounts
                for (int l = 0; l < personaje_cont.size(); l++) {
                    account.setPersonaje(personaje_cont.get(l));
                    account.setExperience(experienta.get(l));
                    account.setNivele(nivel.get(l));
                    account.setProfesii(profesie.get(l));
                }

                //adaug in clasa Game toate conturile cu informatii complete in clasele Account, Information si Credentials
                this.setList(account);
                FileReader reader2 = new FileReader("C:\\Users\\User\\IdeaProjects\\Tema_2_POO\\src\\stories.json");
                Object obj2 = jsonparser.parse(reader2);
                org.json.simple.JSONObject empjsonobj2 = (org.json.simple.JSONObject) obj2;
                org.json.simple.JSONArray array2 = (org.json.simple.JSONArray) empjsonobj2.get("stories");

                //introduc povestile fiecarui tip in liste
                List<String> shop = new ArrayList<>();
                List<String> empty = new ArrayList<>();
                List<String> enemy = new ArrayList<>();
                List<String> finish = new ArrayList<>();

                for (int m = 0; m < array2.size(); m++) {
                    org.json.simple.JSONObject stories = (org.json.simple.JSONObject) array2.get(m);
                    String type = (String) stories.get("type");
                    String value = (String) stories.get("value");

                    if (Objects.equals(type, "EMPTY")) {
                        empty.add(value);
                    }
                    if (Objects.equals(type, "SHOP")) {
                        shop.add(value);
                    }
                    if (Objects.equals(type, "ENEMY")) {
                        enemy.add(value);
                    }
                    if (Objects.equals(type, "FINISH")) {
                        finish.add(value);
                    }
                }
                //introduc povestile in map-ul din Game
                this.setMap("SHOP", shop);
                this.setMap("EMPTY", empty);
                this.setMap("ENEMY", enemy);
                this.setMap("FINISH", finish);
            }

            // incep autentificare utilizatorului
            Scanner myObj = new Scanner(System.in);
            System.out.println("Enter your email:");
            String email = myObj.nextLine();
            boolean ver = false;
            boolean ver_2 = false;

            for(int p = 0; p < this.getListAccounts().size(); p++) {
                if(email.equals(this.getListAccounts().get(p).getInformationAccount().getCredentialsAccount().getEmail())) {
                    ver = true;
                    System.out.println("Email verified!");
                    System.out.println("Enter your password:");
                    String password = myObj.nextLine();
                    if(password.equals(this.getListAccounts().get(p).getInformationAccount().getCredentialsAccount().getPassword())) {
                        System.out.println("Password verified!");
                        System.out.println("Choose your character!");
                        String character = myObj.nextLine();
                            for(int s = 0; s < this.getListAccounts().get(p).getPersonaje().size(); s++) {

                                //utilizatorul si-a ales un personaj cu din contul sau
                                if(character.equals(this.getListAccounts().get(p).getPersonaje().get(s))) {
                                    System.out.println("Your character has been chosen! Good luck in your adventure!");
                                        ver_2 = true;
                                        List <Spell> abilitatiCharacter = null;
                                        int currentHealth = 100;
                                        int maxHealth = 100;
                                        int currentMana = 100;
                                        int maxMana = 100;
                                        Inventory potionInventory = null;
                                        boolean fire_protection;
                                        boolean earth_protection;
                                        boolean ice_protection;
                                        String name = null;
                                        int linie = 0;
                                        int coloana = 0;
                                        int experience = 0;
                                        int level = 0;
                                        int strenght = 1;
                                        int charsima = 1;
                                        int dexterity = 1;
                                        int greutate_inventar = 1;
                                        ice_protection = false;
                                        earth_protection = false;
                                        fire_protection = false;
                                        /*daca si a ales un personaj de tip mage, il cream cu atribute
                                        * in functie de profesia si nivelul lui */
                                        if(this.getListAccounts().get(p).getProfesii().get(s).equals("Mage")) {
                                            Spell ice = new Ice();
                                            Spell fire = new Fire();
                                            Spell earth = new Earth();
                                            Character character1 = new Mage(abilitatiCharacter, currentHealth, maxHealth, currentMana, maxMana,
                                                    fire_protection, ice_protection,  earth_protection, name, linie, coloana, potionInventory,
                                                    experience, level, strenght, charsima, dexterity, greutate_inventar);
                                            character1.setProfesie(this.getListAccounts().get(p).getProfesii().get(s));
                                            character1.getPotionInventory().setMax_weight(2);
                                            character1.getPotionInventory().setMoney(100);
                                            character1.getPotionInventory().createList();
                                            character1.setAblities(ice);
                                            character1.setAblities(fire);
                                            character1.setAblities(earth);
                                            character1.setLevel(Integer.parseInt(this.getListAccounts().get(p).getNivele().get(s)));
                                            character1.setExperience(Integer.parseInt(String.valueOf(this.getListAccounts().get(p).getExperience().get(s))));
                                            setAtributesMage(character1);
                                            System.out.println("Informatii despre player-ul ales:");
                                            System.out.println("Profesie: " + character1.getProfesie());
                                            System.out.println("Level: "  + character1.getLevel());
                                            System.out.println("Experienta " + character1.getExperience());
                                            System.out.println("strenght " + character1.getStrength());
                                            System.out.println("dexterity " + character1.getDexterity());
                                            System.out.println("charisma " + character1.getCharisma());
                                            System.out.println("Apasati *start* pentru a incepe jocul sau *exit* pentru a renunta!");
                                            Scanner r = new Scanner(System.in);
                                            String decision = r.nextLine();
                                            //dupa crearea personajului, utilizatorul tasteaza start si incepe jocul
                                            if(decision.equals("start")) {
                                                playTheGame(character1);
                                            }
                                            else {
                                                break;
                                            }
                                        }
                                        /*daca si a ales un personaj de tip warrior, il cream cu atribute
                                        * in functie de profesia si nivelul lui */
                                        if(this.getListAccounts().get(p).getProfesii().get(s).equals("Warrior")) {
                                            Spell ice = new Ice();
                                            Spell fire = new Fire();
                                            Spell earth = new Earth();
                                            Character character1 = new Warrior(abilitatiCharacter, currentHealth, maxHealth, currentMana, maxMana,
                                                    fire_protection, ice_protection,  earth_protection, name, linie, coloana, potionInventory,
                                                    experience, level, strenght, charsima, dexterity, greutate_inventar);
                                            character1.setProfesie(this.getListAccounts().get(p).getProfesii().get(s));
                                            character1.getPotionInventory().setMax_weight(4);
                                            character1.getPotionInventory().setMoney(100);
                                            character1.getPotionInventory().createList();
                                            character1.setAblities(ice);
                                            character1.setAblities(fire);
                                            character1.setAblities(earth);
                                            character1.setLevel(Integer.parseInt(this.getListAccounts().get(p).getNivele().get(s)));
                                            character1.setExperience(Integer.parseInt(String.valueOf(this.getListAccounts().get(p).getExperience().get(s))));
                                            setAtributesWarrior(character1);
                                            System.out.println("Informatii despre player-ul ales:");
                                            System.out.println("Profesie: " + character1.getProfesie());
                                            System.out.println("Level: " + character1.getLevel());
                                            System.out.println("Experienta " + character1.getExperience());
                                            System.out.println("strenght " + character1.getStrength());
                                            System.out.println("dexterity " + character1.getDexterity());
                                            System.out.println("charisma " + character1.getCharisma());
                                            System.out.println("Apasati *start* pentru a incepe jocul sau *exit* pentru a renunta!");
                                            Scanner r = new Scanner(System.in);
                                            String decision = r.nextLine();
                                            //dupa crearea personajului, utilizatorul tasteaza start si incepe jocul
                                            if(decision.equals("start")) {
                                                playTheGame(character1);
                                            }
                                            else {
                                                break;
                                            }
                                        }
                                        /*daca si a ales un personaj de tip rogue, il cream cu atribute
                                        * in functie de profesia si nivelul lui */
                                        if(this.getListAccounts().get(p).getProfesii().get(s).equals("Rogue")) {
                                            Spell ice = new Ice();
                                            Spell fire = new Fire();
                                            Spell earth = new Earth();
                                            Character character1 = new Rogue(abilitatiCharacter, currentHealth, maxHealth, currentMana, maxMana,
                                                    fire_protection, ice_protection,  earth_protection, name, linie, coloana, potionInventory,
                                                    experience, level, strenght, charsima, dexterity, greutate_inventar);
                                            character1.setProfesie(this.getListAccounts().get(p).getProfesii().get(s));
                                            character1.getPotionInventory().setMax_weight(3);
                                            character1.getPotionInventory().setMoney(100);
                                            character1.getPotionInventory().createList();
                                            character1.setAblities(ice);
                                            character1.setAblities(fire);
                                            character1.setAblities(earth);
                                            character1.setLevel(Integer.parseInt(this.getListAccounts().get(p).getNivele().get(s)));
                                            character1.setExperience(Integer.parseInt(String.valueOf(this.getListAccounts().get(p).getExperience().get(s))));
                                            setAtributesRogue(character1);
                                            System.out.println("Informatii despre player-ul ales:");
                                            System.out.println("Profesie: " + character1.getProfesie());
                                            System.out.println("Level: "+ character1.getLevel());
                                            System.out.println("Experienta " + character1.getExperience());
                                            System.out.println("strenght " + character1.getStrength());
                                            System.out.println("dexterity " + character1.getDexterity());
                                            System.out.println("charisma " + character1.getCharisma());
                                            System.out.println("Apasati *start* pentru a incepe jocul sau *exit* pentru a renunta!");
                                            Scanner r = new Scanner(System.in);
                                            String decision = r.nextLine();
                                            //dupa crearea personajului, utilizatorul tasteaza start si incepe jocul
                                            if(decision.equals("start")) {
                                                playTheGame(character1);
                                            }
                                            else {
                                                break;
                                            }
                                        }
                                }
                            }
                            if(!ver_2) {
                                System.out.println("You don't have this character! Sorry");
                                break;
                            }
                    } else {
                        System.out.println("Wrong password! Sorry!");
                    }
                }
            }
            if(!ver)
                System.out.println("Wrong email! Sorry!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //metoda care seteaza atributele personajului, in functie de nivelul acestuia
    public void setAtributesMage(Character character) {
        character.setGreutate_inventar(4);
        character.setIce_protection(true);
        if(character.getLevel() == 1) {
            character.setCharisma(5);
            character.setStrength(3);
            character.setDexterity(3);
        }
        if (character.getLevel() > 1 && character.getLevel() <= 5) {
            character.setCharisma(10);
            character.setStrength(8);
            character.setDexterity(8);
        }
        if (character.getLevel() > 5 && character.getLevel() <= 10) {
            character.setCharisma(20);
            character.setStrength(12);
            character.setDexterity(12);
        }
        if (character.getLevel() > 10 && character.getLevel() <= 15) {
            character.setCharisma(25);
            character.setStrength(16);
            character.setDexterity(16);
        }
    }

    //metoda care seteaza atributele personajului, in functie de nivelul acestuia
    public void setAtributesWarrior(Character character) {
        character.setGreutate_inventar(8);
        character.setFire_protection(true);
        if(character.getLevel() == 1) {
            character.setCharisma(3);
            character.setStrength(5);
            character.setDexterity(3);
        }
        if (character.getLevel() > 1 && character.getLevel() <= 5) {
            character.setCharisma(8);
            character.setStrength(10);
            character.setDexterity(8);
        }
        if (character.getLevel() > 5 && character.getLevel() <= 10) {
            character.setCharisma(12);
            character.setStrength(20);
            character.setDexterity(12);
        }
        if (character.getLevel() > 10 && character.getLevel() <= 15) {
            character.setCharisma(16);
            character.setStrength(25);
            character.setDexterity(16);
        }
    }

    //metoda care seteaza atributele personajului, in functie de nivelul acestuia
    public void setAtributesRogue(Character character) {
        character.setGreutate_inventar(6);
        character.setEarth_protection(true);
        if(character.getLevel() == 1) {
            character.setCharisma(3);
            character.setStrength(3);
            character.setDexterity(5);
        }
        if (character.getLevel() > 1 && character.getLevel() <= 5) {
            character.setCharisma(8);
            character.setStrength(8);
            character.setDexterity(10);
        }
        if (character.getLevel() > 5 && character.getLevel() <= 10) {
            character.setCharisma(12);
            character.setStrength(12);
            character.setDexterity(20);
        }
        if (character.getLevel() > 10 && character.getLevel() <= 15) {
            character.setCharisma(16);
            character.setStrength(16);
            character.setDexterity(25);
        }
    }

    //metoda care afiseaza un story random in functie de tipul celulei
    public String getStory(Cell celula) {
        if(celula.getWhatsInside().toCharacter().equals("F")) {
            Random random = new Random();
            int randomItem = random.nextInt(this.getMap().get("FINISH").size());
            String randomStory = this.getMap().get("FINISH").get(randomItem);
            return randomStory;
        }
        if(celula.getWhatsInside().toCharacter().equals("N")) {
            Random random = new Random();
            int randomItem = random.nextInt(this.getMap().get("EMPTY").size());
            String randomStory = this.getMap().get("EMPTY").get(randomItem);
            return randomStory;
        }
        if(celula.getWhatsInside().toCharacter().equals("E")) {
            Random random = new Random();
            int randomItem = random.nextInt(this.getMap().get("ENEMY").size());
            String randomStory = this.getMap().get("ENEMY").get(randomItem);
            return randomStory;
        }
        if(celula.getWhatsInside().toCharacter().equals("S")) {
            Random random = new Random();
            int randomItem = random.nextInt(this.getMap().get("SHOP").size());
            String randomStory = this.getMap().get("SHOP").get(randomItem);
            return randomStory;
        }
        return null;
    }

    //metoda care marcheaza cu P celula curenta a personajului
    public void markCurrentCell() {
        Grid.getTablaUnknown().get(Grid.getPersonaj().getLinie()).set(Grid.getPersonaj().getColoana(), "P" + Grid.getTablaExplicit().get(Grid.getPersonaj().getLinie()).get(Grid.getPersonaj().getColoana()).getWhatsInside().toCharacter());
    }

    //metoda care sterge P-ul din celula in care tocmai a fost personajul
    public void demarkCurrentCell() {
        Grid.getTablaUnknown().get(Grid.getPersonaj().getLinie()).set(Grid.getPersonaj().getColoana(), Grid.getTablaExplicit().get(Grid.getPersonaj().getLinie()).get(Grid.getPersonaj().getColoana()).getWhatsInside().toCharacter());
    }

    //metoda care iti ofera 20% sansa sa gasesti monede intr-o celula empty
    public void getGoldFromEmptyCell(Character character) {
        Random random = new Random();
        int nr = random.nextInt(100);
        if(nr <= 20) {
            System.out.println("Felicitari! In aceasta celula ai gasit 10 monede!");
            character.getPotionInventory().setMoney(character.getPotionInventory().getMoney() + 10);
            System.out.println("Acum ai " + character.getPotionInventory().getMoney() + " monede");
        }
        else System.out.println("Bad Luck! Aceasta celula nu contine monede!");
    }

    /*metoda care in functie de protectiile random ale unui inamic (ice, fire sau earth) aplica sau nu damage-ul
    asupra acestuia*/
    void enemyBlockingSpell(Character character, int nr, Enemy enemy, Spell abilitate, int ok) {
        if(character.getAblities().get(nr).getName().equals("Ice")) {
            if(enemy.isIce_protection()) {
                System.out.println("Inamicul tau este imun la " + character.getAblities().get(nr).getName() + "!");
            }
            else
                enemy.receiveDamage(character.getAblities().get(nr).damageToEnemy(character));

        }
        if(character.getAblities().get(nr).getName().equals("Fire")) {
            if(enemy.isFire_protection()) {
                System.out.println("Inamicul tau este imun la " + character.getAblities().get(nr).getName() + "!");
            }
            else
                enemy.receiveDamage(character.getAblities().get(nr).damageToEnemy(character));

        }
        if(character.getAblities().get(nr).getName().equals("Earth")) {
            if(enemy.isEarth_protection()) {
                System.out.println("Inamicul tau este imun la " + character.getAblities().get(nr).getName() + "!");
            }
            else
                enemy.receiveDamage(character.getAblities().get(nr).damageToEnemy(character));
        }
    }

    //metoda care semnaleaza utilizatorlui ca nu a intros o metoda valida de parcurgere a tablei de joc
    public void catchErrorString(String string) {
        try {
            TextException.validateInputString(string);
        } catch (InvalidCommandException e) {
            System.out.println("Invalid Input: Introduceti din nou o comanda!");
        }
    }

    //metoda care primeste urmatoarea comanda a utilizatorului de a se misca pe tabla de joc
    public void getNextCommand()  {
        System.out.println("Algeti in ce directie doriti sa continuati!");
        Scanner ceva = new Scanner(System.in);
        String comanda = ceva.nextLine();
        catchErrorString(comanda);

        if (comanda.equals("north")) {
                Grid.goNorth();
            }
            if (comanda.equals("east")) {
                Grid.goEast();
            }
            if (comanda.equals("west")) {
                Grid.goWest();
            }
            if (comanda.equals("south")) {
                Grid.goSouth();
            }
    }

    //metoda in care se parcurge tabla de joc si se joaca jocul
    public void playTheGame(Character character) {
        // se creaza tabla de joc hardcodata
        ArrayList<Cell> tablaExplicit = new ArrayList<>();
        ArrayList<Cell> tablaUnknown = new ArrayList<>();
        Grid.setLength(5);
        Grid.setWidth(5);
        Grid.setTable(tablaExplicit, tablaUnknown);
        Grid.generateHardocodatedMap(Grid.getLength(), Grid.getWidth());
        Grid.generate_map_unknown(Grid.getLength(), Grid.getWidth());
        boolean ok = true;
        Grid.setPersonaj(character);
        Grid.getPersonaj().setLinie(0);
        Grid.getPersonaj().setColoana(0);
        while (ok) {
            int i = Grid.getPersonaj().getLinie();
            int j = Grid.getPersonaj().getColoana();
            Cell currentCell = Grid.getTablaExplicit().get(i).get(j);
            markCurrentCell();
            Grid.displayTableUnknown();
            demarkCurrentCell();
            //cazul in care avem o celula goala
            if(Grid.getTablaExplicit().get(i).get(j).getWhatsInside().toCharacter().equals("N")) {
                //afisam o poveste
                System.out.println(getStory(Grid.getTablaExplicit().get(i).get(j)));

                //personajul are o sansa sa primeasca monede
                getGoldFromEmptyCell(character);
            }

            //cazul in care avem celula finish
            if(Grid.getTablaExplicit().get(i).get(j).getWhatsInside().toCharacter().equals("F")) {
                //afisam o poveste si se termina jocul
                System.out.println(getStory(Grid.getTablaExplicit().get(i).get(j)));
                System.out.println("Congrats! You won this game!");
                    ok = false;
            }
            if(!ok) {
                break;
            }


            //cazul in care avem o celula inamic
            if(Grid.getTablaExplicit().get(i).get(j).getWhatsInside().toCharacter().equals("E")) {
                boolean check = true;
                System.out.println(getStory(Grid.getTablaExplicit().get(i).get(j)));

                //cazul in care am invins deja inamicul din aceasta celula
                if(currentCell.getWhatsInside().getEnemy().getCurrentHealth() <= 0) {
                    System.out.println("Ati invins deja acest inamic!");
                    check = false;
                    markCurrentCell();
                    Grid.displayTableUnknown();
                    demarkCurrentCell();
                }
                Scanner input = new Scanner(System.in);
                if(check) {
                    System.out.println("Prepare for battle! You attack first!");

                    //se afiseaza viata generata random a inamicului
                    System.out.println("Inamicul are " + currentCell.getWhatsInside().getEnemy().getCurrentHealth() + " viata!");
                    input = new Scanner(System.in);
                }
                int abilitati = 3;
                while (check) {

                    //jucatorul alege daca vrea sa atace normal sau cu abilitate
                    System.out.println("Type *normal* if you want a normal attack or *spell* to choose an ablity");
                    Enemy enemy = currentCell.getWhatsInside().getEnemy();
                    String decision = input.nextLine();

                    //cazul in care alege sa atace normal
                    if(decision.equals("normal")) {
                        currentCell.getWhatsInside().getEnemy().receiveDamage(character.getDamage());

                        //cazul in care l-a invins pe inamic
                        if (enemy.getCurrentHealth() <= 0) {
                            System.out.println("Ai invins inamicul!");
                            int newLvl = character.getLevel() + 1;
                            //cand invinge un inamic, player-ul face level up
                            System.out.println("Level up! Personajul tau este acum lvl " + newLvl + " !");
                            markCurrentCell();
                            Grid.displayTableUnknown();
                            demarkCurrentCell();
                            break;
                        }
                        //in caz ca nu a fost invins inamicul, afisam cat viata mai are
                        System.out.println("Inamicul mai are " + enemy.getCurrentHealth() + " viata");
                        Random rrr = new Random();
                        int nrrr = rrr.nextInt(100);
                        int ok2 = 0;

                        //sansa de 20% ca inamicul sa atace cu abilitate
                        if (nrrr <= 20) {
                            if (abilitati > 1) {
                                System.out.println("Inamicul te-a atacat cu o abilitate!");
                                enemy.useSpell(enemy.getAblities().get(0), enemy, character, ok2);
                                enemy.getAblities().remove(0);
                                abilitati--;
                            } else {
                                System.out.println("Inamicul nu mai are mana pentru abilitate! Te va ataca cu un atac normal!");
                                character.receiveDamage(enemy.getDamage());
                            }
                            // daca nu, inamicul ataca normal
                        } else {
                            System.out.println("Inamicul te-a atacat cu un atac normal!");
                            character.receiveDamage(enemy.getDamage());
                        }

                        //cazul in care inamicul ne-a invins
                        if (character.getCurrentHealth() <= 0) {
                            System.out.println("Ai pierdut! Inamicul te-a invins!");
                            ok = false;
                            break;
                        }

                        //cata viata mai are personajul dupa ce a fost atacat
                        System.out.println("Personajul vostru mai are " + character.getCurrentHealth() + " viata");
                        boolean var;

                        //daca avem potiuni in inventar, dupa fiecare runda de atac, vom fi
                        //intrebati daca vrem sa folosim potiune sau nu
                        if (!character.getPotionInventory().getLista_potiuni().isEmpty()) {
                            System.out.println("Doresti sa folosesti potiuni?");
                            Scanner rr = new Scanner(System.in);
                            String decision2 = rr.nextLine();
                            if (decision2.equals("da")) {
                                var = true;
                            }
                            else {
                                var = false;
                            }

                            //ni se afiseaza potiunile din inventar
                            while (!character.getPotionInventory().getLista_potiuni().isEmpty() && var) {
                                System.out.println("In inventar aveti potiunile");
                                int p = 0;
                                for (p = 0; p < character.getPotionInventory().getLista_potiuni().size(); p++) {
                                    System.out.println(character.getPotionInventory().getLista_potiuni().get(p).name());
                                }
                                Scanner r = new Scanner(System.in);
                                int nr = r.nextInt();
                                r.nextLine();

                                //alegem potiune si verificam ce tip este
                                if (character.getPotionInventory().getLista_potiuni().get(nr).name().equals("HealthPotion")) {

                                    character.getPotionInventory().getLista_potiuni().get(nr).usePotion(character);
//                                    character.regenerateHealth(character.getPotionInventory().getLista_potiuni().get(nr).getValueOfRegen());
                                    character.getPotionInventory().getLista_potiuni().remove(nr);
                                    System.out.println("Ai folosit potiune de regenerare a vietii! Viata ta este acum " + character.getCurrentHealth());
                                } else {
                                    character.getPotionInventory().getLista_potiuni().get(nr).usePotion(character);
//                                    character.regenerateMana(character.getPotionInventory().getLista_potiuni().get(nr).getValueOfRegen());
                                    character.getPotionInventory().getLista_potiuni().remove(nr);
                                    System.out.println("Ai folosit o potiune de ragenerare a manei! Mana ta aste acum " + character.getCurrentMana());
                                }

                                //putem folosi mai multe potiuni la rand
                                if(!character.getPotionInventory().getLista_potiuni().isEmpty()) {
                                    System.out.println("Mai doresti sa folosesti inca o potiune din inventar?");
                                    String question = r.nextLine();
                                    if (question.equals("nu")) {
                                        var = false;
                                    } else continue;
                                }
                                //mesaj cand ramanem fara potiuni
                                else System.out.println("Nu mai aveti potiuni in inventar!");
                            }
                        }
                        else {
                            System.out.println("Nu mai aveti potiuni in inventar!");
                        }
                    }

                    //alegem sa atacam inamicul cu abiliate
                    if(decision.equals("spell")) {
                        if(character.getAblities().isEmpty()) {
                            System.out.println("Personajul nu mai are abilitati! Puteti ataca doar *normal*!");
                            continue;
                        }

                        //afisam cata mana avem in acel moment
                        System.out.println("Mai aveti " + character.getCurrentMana() + " mana");
                        System.out.println("Alegeti una dintre urmatoarele abilitati!");

                        //alegem o abilitate din cele pe care le are personajul
                        for(int o = 0; o < character.getAblities().size(); o++) {
                            System.out.println(character.getAblities().get(o).getName() + " necesita " + character.getAblities().get(o).manaNeededCharac() + " mana");
                        }
                        int nr = input.nextInt();
                        input.nextLine();
                        int newCurrMana = character.getCurrentMana() - character.getAblities().get(nr).manaNeededCharac();

                        // ni se micsoreaza mana
                        character.setCurrentMana(newCurrMana);
                        int ok3 = 1;
                        enemyBlockingSpell(character, nr, enemy, character.getAblities().get(nr), ok3);
                        ok3 = 0;
                        character.getAblities().remove(nr);

                        //caz in care am invins inamicul
                        if(enemy.getCurrentHealth() <= 0) {
                            System.out.println("Ai invins inamicul!");
                            int newLvl = character.getLevel() + 1;
                            System.out.println("Level up! Personajul tau este acum lvl " + newLvl + " !");
                            markCurrentCell();
                            Grid.displayTableUnknown();
                            demarkCurrentCell();
                            break;
                        }
                        System.out.println("Inamicul mai are " + enemy.getCurrentHealth() + " viata");
                        Random rrr = new Random();
                        int nrrr = rrr.nextInt();

                        //sansa de 20% sa fim atacati cu abilitate de inamic
                        if(nrrr <= 20) {
                            if(abilitati > 1) {
                                System.out.println("Inamicul te-a atacat cu o abilitate!");
                                enemy.useSpell(enemy.getAblities().get(0), enemy, character, ok3);
                                enemy.getAblities().remove(0);
                                abilitati--;
                            }
                            else {
                                System.out.println("Inamicul nu mai are mana pentru abilitate! Te va ataca cu un atac normal!");
                                character.receiveDamage(enemy.getDamage());
                            }
                        }
                        else {
                            System.out.println("Inamicul te-a atacat cu un atac normal!");
                            character.receiveDamage(enemy.getDamage());
                        }

                        //cazul in care am pierdut lupa
                        if(character.getCurrentHealth() <= 0) {
                            System.out.println("Ai pierdut! Inamicul te-a invins!");
                            ok = false;
                            break;
                        }

                        //vedem cata viata si mana mai avem
                        System.out.println("Personajul vostru mai are " + character.getCurrentHealth() + " viata");
                        System.out.println("Mai aveti " + character.getCurrentMana() + " mana");

                        boolean var;

                        //daca avem potiuni, vom fi intrebati dupa fiecare atat daca dorim sa le utilizam
                        if(!character.getPotionInventory().getLista_potiuni().isEmpty()) {
                            System.out.println("Doresti sa folosesti potiuni?");
                            Scanner rr = new Scanner(System.in);
                            String decision2 = rr.next();
                            if (decision2.equals("da")) {
                                var = true;
                            }
                            else {
                                var = false;
                            }
                                while (!character.getPotionInventory().getLista_potiuni().isEmpty() && var) {
                                    System.out.println("In inventar aveti potiunile");
                                    int p = 0;
                                    for (p = 0; p < character.getPotionInventory().getLista_potiuni().size(); p++) {
                                        System.out.println(character.getPotionInventory().getLista_potiuni().get(p).name());
                                    }
                                    Scanner r = new Scanner(System.in);
                                    int nrr = r.nextInt();
                                    r.nextLine();
                                    if (character.getPotionInventory().getLista_potiuni().get(nrr).name().equals("HealthPotion")) {
                                        character.getPotionInventory().getLista_potiuni().get(nrr).usePotion(character);
                                        character.getPotionInventory().getLista_potiuni().remove(nrr);
                                        System.out.println("Ai folosit potiune de regenerare a vietii! Viata ta este acum " + character.getCurrentHealth());
                                    } else {
                                        character.getPotionInventory().getLista_potiuni().get(nrr).usePotion(character);
                                        character.getPotionInventory().getLista_potiuni().remove(nrr);
                                        System.out.println("Ai folosit o potiune de ragenerare a manei! Mana ta aste acum " + character.getCurrentMana());
                                    }
                                    if(!character.getPotionInventory().getLista_potiuni().isEmpty()) {
                                        System.out.println("Mai doresti sa folosesti inca o potiune din inventar?");
                                        String question = r.nextLine();
                                        if (question.equals("nu")) {
                                            var = false;
                                        } else continue;
                                    }
                                    else System.out.println("Nu mai aveti potuni in inventar!");
                                }
                        } else {
                            System.out.println("Nu mai aveti potiuni in inventar!");
                            var = false;
                        }
                    }
                }
            }

            //daca celula e de tip shop
            if(Grid.getTablaExplicit().get(i).get(j).getWhatsInside().toCharacter().equals("S")) {
                Scanner s = new Scanner(System.in);
                System.out.println(getStory(Grid.getTablaExplicit().get(i).get(j)));
                i = Grid.getPersonaj().getLinie();
                j = Grid.getPersonaj().getColoana();
                currentCell = Grid.getTablaExplicit().get(i).get(j);
                boolean varr = true;
                System.out.println("Bine ai venit la magazinul de potiuni! Doresti sa cumperi? Introduce *da* sau *nu*");
                String decizie = s.nextLine();

                //tastam "da" daca dorim sa cumparam ceva
                if(decizie.equals("da")) {
                    while (varr) {
                        System.out.println("Ai " + character.getPotionInventory().getMoney() + " bani");
                        System.out.println("Mai aveti diponibil in invetar o greutate de " + character.getGreutate_inventar());
                        for (int w = 0; w < currentCell.getWhatsInside().getShop().getMagazinPotiuni().size(); w++) {
                            System.out.println(currentCell.getWhatsInside().getShop().getMagazinPotiuni().get(w).name() + " costa " + currentCell.getWhatsInside().getShop().getMagazinPotiuni().get(w).getPrice());
                        }
                        Scanner input = new Scanner(System.in);
                        int nr = input.nextInt();
                        input.nextLine();

                        //alegem potiunea pe care o dorim
                        character.buyPotion(currentCell, nr);
                        System.out.println("Ati achizitionat o potiune de tip " + currentCell.getWhatsInside().getShop().getMagazinPotiuni().get(nr).name());
                        currentCell.getWhatsInside().getShop().getMagazinPotiuni().remove(nr);
                        if (!currentCell.getWhatsInside().getShop().getMagazinPotiuni().isEmpty()) {
                            System.out.println("Mai doriti sa cumparati inca o potiune?");
                            String decizie1 = input.nextLine();

                            // daca nu dorimm, ne sunt afisate informatii
                            if (decizie1.equals("nu")) {
                                System.out.println("Mai aveti " + character.getPotionInventory().getMoney() + " monede");
                                System.out.println("Mai aveti o greutate disponibila de " + character.getGreutate_inventar() + " in inventar");
                                System.out.println("In inventar aveti urmatoarele potiuni: ");
                                for (int m = 0; m < character.getPotionInventory().getLista_potiuni().size(); m++) {
                                    System.out.println(character.getPotionInventory().getLista_potiuni().get(m).name());
                                }
                                varr = false;
                                markCurrentCell();
                                Grid.displayTableUnknown();
                                demarkCurrentCell();
                            } else continue;
                        } else {
                            //daca am cumparat toate potiunile dintr-un shop
                            System.out.println("Nu mai exista potiuni in shop!");
                            varr = false;
                            markCurrentCell();
                            Grid.displayTableUnknown();
                            demarkCurrentCell();
                        }
                    }
                }else {
                    markCurrentCell();
                    Grid.displayTableUnknown();
                    demarkCurrentCell();
                    getNextCommand();
                    continue;
                }
            }
            if(ok == false)
                break;
            getNextCommand();
        }
    }
}
