import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class Game {
    private String movieToGuess;
    private String guessedletter;
    private String rightLetters;
    private String wrongLetters;
    private int pointLost;
    private boolean hasWon;

    public Game(int randomNumber, String pathName) throws IOException {
        movieToGuess = Files.readAllLines(Paths.get(pathName)).get(randomNumber);
        rightLetters = "";
        wrongLetters = "";
        pointLost = 0;
        hasWon = false;
    }

    public String getWrongLetters(){
        return wrongLetters;
    }

    public boolean gameEnded(){
        if(pointLost>=10){
            return true;
        }
        if (!getHiddenMovieName().contains("_")){
            hasWon = true;
            return true;
        }
        return false;
    }

    public String getMovieName(){
        return movieToGuess;
    }

    public boolean isHasWon(){
        return hasWon;
    }

    public String getHiddenMovieName(){
        if(rightLetters.equals("")){
        return movieToGuess.replaceAll("[A-Za-z]","_");
        }
        else {
            return movieToGuess.replaceAll("[a-zA-Z&&[^" + rightLetters +"]]","_");
        }
    }

    public void checkInput(){
        guessedletter = inputLetter();
        if(movieToGuess.toLowerCase().contains(guessedletter)){
            rightLetters += guessedletter + guessedletter.toUpperCase();
            System.out.println("You guessed it right! "+guessedletter);
        }
        else {
            wrongLetters += guessedletter + guessedletter.toUpperCase();
            System.out.println("You guessed it wrong! "+guessedletter);
            pointLost++;
        }
    }

    private String inputLetter(){
        System.out.println("Guess a letter ");
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        if(rightLetters.contains(inputString) || wrongLetters.contains(inputString)){
            System.out.println("You have guessed it already!");
            return inputLetter();
        }
        else if(!inputString.matches("[a-z]")){
            System.out.println("This is not a character");
            return inputLetter();
        }
        else {
            return inputString;
        }
    }
}
