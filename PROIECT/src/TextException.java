
//exceptie atunci cand utilizatorul nu introduce o comanda valida pentru a se deplasa pe tabla de joc
public class TextException {

    static void validateInputString (String input) throws InvalidCommandException {
        switch (input) {
            case "north": break;
            case "south": break;
            case "east": break;
            case "west": break;
            default: throw new InvalidCommandException("Invalid Input");
        }
    }
}
