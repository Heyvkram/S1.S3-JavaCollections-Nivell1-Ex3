import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner( System.in );
        String countryFile = "src/countries.txt";
        String scoreFile = "src/scores.txt";
        String user = "";
        int points = 0;

        HashMap<String, String> countriesAndCapitals = new HashMap<>();

        try{
            BufferedReader br = new BufferedReader(new FileReader(countryFile));
            String line = "";
            while ( (line = br.readLine()) != null ){
                String [] block = line.split(" ");
                if (block.length > 1) {
                    countriesAndCapitals.put(block[0], block[1]);
                }
            }
            br.close();
        } catch (IOException e){
            System.out.println("Error reading the file " + e.getMessage());
        }

        System.out.print("Insert user name: ");
        user = sc.nextLine();

        for (int i = 0; i < 10; i++){
            String country = randomCountry(countriesAndCapitals);
            System.out.print("What is the capital of " + country + "? ");
            String result = sc.nextLine();

            if (result.equalsIgnoreCase(countriesAndCapitals.get(country))){
                System.out.println("CORRECT!\n");
                points++;
            } else {
                System.out.println("Wrong!! the correct answer is: " + countriesAndCapitals.get(country) + ".\n");
            }
        }

        System.out.println(user + ", your score is: " + points + "/10." );

        try {
            FileWriter writeScore = new FileWriter(scoreFile, true);
            writeScore.write("User: " + user + " || Score: " + points + ".\n");
            writeScore.close();
        } catch (IOException e){
            System.out.println("Error creating the file " + e.getMessage());
        }

        sc.close();
    }

    public static String randomCountry(Map<String, String> countries){
        Random random = new Random();
        int index = random.nextInt(countries.size());
        return (String) countries.keySet().toArray()[index];
    }
}