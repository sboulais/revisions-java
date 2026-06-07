package bestpractices.staticfactorymethod;

import java.util.Map;

class Main {
    void main() {

        Author author = Author.of("John Doe", "password123", "john@mail.com");

        Blogpost post = new Blogpost(
                author,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                "My first blog post");

        var testMap = Map.of("author", author, "post", post, "blogpost", post);

        System.out.println(post.getContent());
        System.out.println(post.getAuthor().getEmail());
    }
}
