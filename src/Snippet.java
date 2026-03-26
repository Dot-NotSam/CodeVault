class Snippet {
    private String title;
    private String code;
    private String description;
    private String tags;
    private String category;
    private boolean isFavorite;

    public Snippet(String title, String code, String description, String tags, String category, boolean isFavorite) {
        this.title = title;
        this.code = code;
        this.description = description;
        this.tags = tags;
        this.category = category;
        this.isFavorite = isFavorite;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getTags() {
        return tags;
    }

    public String getCategory() {
        return category;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void toggleFavorite() {
        isFavorite = !isFavorite;
    }

    public String toFileString() {
        return title + "||" + code + "||" + description + "||" + tags + "||" + category + "||" + isFavorite;
    }

    public static Snippet fromFileString(String line) {
        String[] parts = line.split("\\|\\|");
        return new Snippet(parts[0], parts[1], parts[2], parts[3], parts[4], Boolean.parseBoolean(parts[5]));
    }
}