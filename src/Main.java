import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SnippetManager manager = new SnippetManager();

        manager.loadFromFile();

        while (true) {
            System.out.println("\n===== CodeVault Menu =====");
            System.out.println("1. Add Snippet");
            System.out.println("2. View All Snippets");
            System.out.println("3. Search Snippet");
            System.out.println("4. Delete Snippet");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Enter title: ");
                String title = sc.nextLine();

                System.out.print("Enter code: ");
                String code = sc.nextLine();

                System.out.print("Enter description: ");
                String description = sc.nextLine();

                System.out.print("Enter tags: ");
                String tags = sc.nextLine();

                Snippet snippet = new Snippet(title, code, description, tags);
                manager.addSnippet(snippet);

                System.out.println("Snippet added successfully.");
            }

            else if (choice == 2) {
                manager.viewAllSnippets();
            }

            else if (choice == 3) {
                System.out.print("Enter keyword to search: ");
                String keyword = sc.nextLine();
                manager.searchSnippet(keyword);
            }

            else if (choice == 4) {
                System.out.print("Enter snippet number to delete: ");
                int index = sc.nextInt();
                manager.deleteSnippet(index - 1);
            }

            else if (choice == 5) {
                manager.saveToFile();
                System.out.println("Exiting... Data saved.");
                break;
            }

            else {
                System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}