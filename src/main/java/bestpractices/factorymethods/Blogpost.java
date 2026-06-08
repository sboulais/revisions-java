package bestpractices.factorymethods;

class Blogpost {
    private String title;
    private String content;
    private Author author;

    public Blogpost(final Author author, final String content, final String title) {
        this.author = author;
        this.content = content;
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }
}
