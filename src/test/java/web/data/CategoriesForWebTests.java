package web.data;

public enum CategoriesForWebTests {
    FRUITS("Овощи, фрукты, ягоды"),
    DAIRY("Молоко, сыр, яйца"),
    MEAT("Мясо, птица, колбасы"),
    SELFMADE("Наше производство"),
    NEW("Новинки");

    public final String categoryName;

    CategoriesForWebTests(String categoryName) {
        this.categoryName = categoryName;
    }
}
