import java.util.Scanner;

public class Textchat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Initialize userInput with an empty string or a default value
        String userInput = "";
        boolean continueConversation = true;

        while (continueConversation) {
            System.out.println("Hi, I'm a simple AI. What's your name?");
            userInput = scanner.nextLine();
            System.out.println("Nice to meet you, " + userInput + "!");

            System.out.println("How old are you?");
            userInput = scanner.nextLine();
            try {
                int age = Integer.parseInt(userInput);
                System.out.println("You are " + age + " years old.");
            } catch (NumberFormatException e) {
                System.out.println("That's not a valid age.");
                continue;
            }

            System.out.println("Do you like programming? (yes/no)");
            userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("yes")) {
                System.out.println("That's great! Programming is a rewarding hobby.");
            } else {
                System.out.println("Programming is a valuable skill, you should consider learning it.");
            }

            System.out.println("Would you like to continue the conversation? (yes/no)");
            userInput = scanner.nextLine();
            if (!userInput.equalsIgnoreCase("yes")) {
                continueConversation = false;
            }
        }
        System.out.println("Goodbye, " + userInput + "!");
        scanner.close();
    }
}

