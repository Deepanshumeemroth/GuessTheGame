import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        File file = new File("list.txt");
        Scanner fileScanner = new Scanner(file);
        int c = 0;
        while (fileScanner.hasNext()){
            String s = fileScanner.nextLine();
            c++;
        }
        Random random = new Random();
        int r = random.nextInt(c);
        String path = "list.txt";
        Game game = new Game(r, path);
        System.out.println("Welcome to Guess the Movie!");
        System.out.println("The rules are simple, the computer randomly picks a movie title, and shows you how " +
                "many " + "letters it's made up of.");
        System.out.println("Your goal is to try to figure out the movie by guessing one letter at a time.");
        System.out.println("If a letter is indeed in the title the computer will reveal its correct position in" +
                " the " + "word, if not, you lose a point.");
        System.out.println("If you lose 10 points, game over!");
        System.out.println("Let's start!");
        System.out.println("The movie has "+game.getMovieName().length()+" characters including spaces and punctuation.");

        while (!game.gameEnded()){
        System.out.println("Movie = "+game.getHiddenMovieName());
        System.out.println("You have guessed ["+game.getWrongLetters().length()/2+"] wrong letters");
        game.checkInput();
        }

        if(game.isHasWon()){
            System.out.println("Congratulations! You won the game");
        }
        else {
            System.out.println("Sorry! You Lost the game. You have lost all points");
        }
        System.out.println("Movie name is "+ game.getMovieName());
    }
}