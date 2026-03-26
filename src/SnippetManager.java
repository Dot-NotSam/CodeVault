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
                s.getTags().toLowerCase().contains(keyword.toLowerCase()) ||
                s.getDescription().toLowerCase().contains(keyword.toLowerCase()) ||
                s.getCategory().toLowerCase().contains(keyword.toLowerCase())) {

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


    public void toggleFavorite(int index) {
        if (index < 0 || index >= snippets.size()) {
            System.out.println("Invalid index.");
            return;
        }
        snippets.get(index).toggleFavorite();
        System.out.println("Favorite status updated.");
    }


    public void viewFavorites() {
        boolean found = false;

        for (Snippet s : snippets) {
            if (s.isFavorite()) {
                System.out.println("\n⭐ " + s.getTitle());
                System.out.println("Code: " + s.getCode());
                System.out.println("Category: " + s.getCategory());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No favorite snippets.");
        }
    }


    public void viewRecent() {
        int count = Math.min(5, snippets.size());

        for (int i = snippets.size() - count; i < snippets.size(); i++) {
            Snippet s = snippets.get(i);
            System.out.println("\n" + s.getTitle());
            System.out.println("Category: " + s.getCategory());
        }
    }


    public void showStats() {
        System.out.println("Total Snippets: " + snippets.size());

        HashMap<String, Integer> map = new HashMap<>();

        for (Snippet s : snippets) {
            map.put(s.getCategory(), map.getOrDefault(s.getCategory(), 0) + 1);
        }

        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }
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