import java.util.*;
import java.io.*;

class SnippetManager {
    private ArrayList<Snippet> snippets = new ArrayList<>();
    private final String FILE_NAME = "data/snippets.txt";

    public void addSnippet(Snippet snippet) {
        snippets.add(snippet);
    }

    public void viewAllSnippets() {
        if (snippets.isEmpty()) {
            System.out.println("No snippets available.");
            return;
        }

        for (int i = 0; i < snippets.size(); i++) {
            Snippet s = snippets.get(i);
            System.out.println("\nSnippet " + (i + 1));
            System.out.println("Title: " + s.getTitle());
            System.out.println("Code: " + s.getCode());
            System.out.println("Description: " + s.getDescription());
            System.out.println("Tags: " + s.getTags());
        }
    }

    public void searchSnippet(String keyword) {
        boolean found = false;

        for (Snippet s : snippets) {
            if (s.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                s.getTags().toLowerCase().contains(keyword.toLowerCase())) {

                System.out.println("\nFound:");
                System.out.println("Title: " + s.getTitle());
                System.out.println("Code: " + s.getCode());
                System.out.println("Description: " + s.getDescription());
                System.out.println("Tags: " + s.getTags());

                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching snippet found.");
        }
    }

    public void deleteSnippet(int index) {
        if (index < 0 || index >= snippets.size()) {
            System.out.println("Invalid index.");
            return;
        }

        snippets.remove(index);
        System.out.println("Snippet deleted successfully.");
    }

    public void saveToFile() {
        try {
            File file = new File(FILE_NAME);
            file.getParentFile().mkdirs();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (Snippet s : snippets) {
                writer.write(s.toFileString());
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    public void loadFromFile() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return;

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                snippets.add(Snippet.fromFileString(line));
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading file.");
        }
    }
}