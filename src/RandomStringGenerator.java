import java.util.Random;

public class RandomStringGenerator {

    public Random random;
    public String charSet;

    public RandomStringGenerator(String charSet) {
        random = new Random();
        this.charSet = charSet;
    }

    public String getString(int size){
        String output = "";
        for (int i = 0; i < size; i++) {
            output += charSet.charAt(random.nextInt(charSet.length()));
        }
        return output;
    }

    public char getChar() {
        return charSet.charAt(random.nextInt(charSet.length()));
    }
}
