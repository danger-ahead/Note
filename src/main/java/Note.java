import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Note {
    public static void main(String[] args)throws Exception{

        TemplateForQuery templateForQuery = new TemplateForQuery();

        System.out.println("What do you want to do?\n1. Take a note\n2. View notes from a particular day\n3. View all your notes\n4. Update a note");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int input;
        String inputString;

        switch (n) {
            case 1 -> {
                System.out.print("Enter the date to tag the note with, in ddmmyyyy format: ");
                input = Integer.parseInt(bufferedReader.readLine());
                System.out.println("Type the note");
                inputString = bufferedReader.readLine();
                templateForQuery.addNote(input, inputString);
            }
            case 2 -> {
                System.out.print("Enter the date in ddmmyyyy format: ");
                input = Integer.parseInt(bufferedReader.readLine());
                System.out.println(templateForQuery.getNote(input));
            }
            case 3 -> templateForQuery.getNote();
            case 4 -> {
                System.out.print("Enter the date tagged with the note, in ddmmyyyy format: ");
                input = Integer.parseInt(bufferedReader.readLine());
                System.out.println("Type the note");
                inputString = bufferedReader.readLine();
                templateForQuery.updateNote(input, inputString);
            }
            default -> System.out.println("Enter between 1, 2 or 3");
        }

    }
}
