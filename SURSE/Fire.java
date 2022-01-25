
// abilitate din joc
public class Fire extends Spell{

    @Override
    String getName() {
        return "Fire";
    }

    //damage-ul pe care il da inamicul personajului in lupta
    public int damageToCharacter(Character character) {
        if(character.getProfesie().equals("Warrior")) {
            System.out.println("Personajul tau este imun la Fire! N-ai primit damage");
            return 0;
        }
        else
            return 10;
    }

    //mana necesara inamicului pentru a folosi o abilitate
    public int manaNeededEnemy() {
        this.mana_cost = 20;
        return 20;
    }

    //damage-ul pe care il da personajul inamicului in lupta
    public int damageToEnemy(Character character) {
        if(character.getProfesie().equals("Warrior")) {
            this.damage = 40;
            return 40;
        }
        else {
            this.damage = 30;
            return 30;
        }
    }

    //mana necesara personajului pentru a folosit o abilitate
    public int manaNeededCharac() {
        this.mana_cost = 40;
        return 40;
    }
}
