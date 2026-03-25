class Snippet {
    private String title;
    private String code;
    private String description;
    private String tags;

    public Snippet(String title, String code, String description, String tags) {
        this.title = title;
        this.code = code;
        this.description = description;
        this.tags = tags;
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

    public String toFileString() {
        return title + "||" + code + "||" + description + "||" + tags;
    }

    public static Snippet fromFileString(String line) {
        String[] parts = line.split("\\|\\|");
        return new Snippet(parts[0], parts[1], parts[2], parts[3]);
    }
}
