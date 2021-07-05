import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Note {
    public static void main(String[] args)throws Exception{

        TemplateForQuery templateForQuery = new TemplateForQuery();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            System.out.print("Press y or yes to continue... ");
            String pressAnyKeyToContinue = bufferedReader.readLine();

            if(pressAnyKeyToContinue.equalsIgnoreCase("Y") || pressAnyKeyToContinue.equalsIgnoreCase("yes")){
                System.out.println("What do you want to do?\n1. Take a note\n2. View notes from a particular day\n3. View all your notes\n4. Update a note\n5. Delete a note");
                System.out.print("Enter your choice: ");

                int n = Integer.parseInt(bufferedReader.readLine());
                String input;
                String inputString;

                switch (n) {
                    case 1 -> {
                        System.out.print("Enter the date to tag the note with, in dd-mm-yyyy format: ");
                        input = bufferedReader.readLine();
                        System.out.println("Type the note");
                        inputString = bufferedReader.readLine();
                        templateForQuery.addNote(input, inputString);
                    }
                    case 2 -> {
                        System.out.print("Enter the date in dd-mm-yyyy format: ");
                        inputString = bufferedReader.readLine();
                        System.out.println(templateForQuery.getNote(inputString));
                    }
                    case 3 -> templateForQuery.getNote();
                    case 4 -> {
                        System.out.print("Enter the note number: ");
                        input = bufferedReader.readLine();
                        System.out.println("Type the note");
                        inputString = bufferedReader.readLine();
                        templateForQuery.updateNote(input, inputString);
                    }
                    case 5 -> {
                        System.out.print("Enter the note number: ");
                        input = bufferedReader.readLine();
                        templateForQuery.deleteNote(input);
                    }
                    default -> System.out.println("Enter between 1, 2, 3, 4 or 5");
                }
            }
            else{
                System.out.println("exiting...!");
                break;
            }
        }
    }
}
