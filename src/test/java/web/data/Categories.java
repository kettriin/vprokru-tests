package web.data;

public enum Categories {
    FRUITS("Овощи, фрукты, ягоды"),
    DAIRY("Молоко, сыр, яйца"),
    MEAT("Мясо, птица, колбасы"),
    SELFMADE("Наше производство"),
    NEW("Новинки");

    public final String categoryName;

    Categories(String categoryName) {
        this.categoryName = categoryName;
    }
}
