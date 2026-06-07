package bestpractices.staticfactorymethod;

class Author {
    private String name;
    private String email;
    private String password;

    private Author(final String name, final String password, final String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public static Author of(final String name, final String password, final String email) {
        return new Author(name, password, email);
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
