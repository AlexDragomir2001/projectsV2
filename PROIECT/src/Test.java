
//clasa care ruleaza testul hardcodat
public class Test {
    public static void main(String[] args) {

        Game game = Game.getSingleObjectOfGame();
        game.initializeList();
        game.initializeMap();
        game.run();
    }
}
