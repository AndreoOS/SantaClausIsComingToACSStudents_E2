package data;

import entities.Gift;
import enums.Category;

import java.util.ArrayList;
import java.util.List;

public final class GiftList {
    private List<Gift> boardGames;
    private List<Gift> books;
    private List<Gift> clothes;
    private List<Gift> sweets;
    private List<Gift> tech;
    private List<Gift> toys;

    public GiftList() {
        boardGames = new ArrayList<>();
        books = new ArrayList<>();
        clothes = new ArrayList<>();
        sweets = new ArrayList<>();
        tech = new ArrayList<>();
        toys = new ArrayList<>();
    }

    /**
     * Method divides the gifts into sub-lists according to their category
     * @param santaGiftList Unsorted list of all gifts
     */
    public void populateGiftList(final List<Gift> santaGiftList) {
        for (Gift gift : santaGiftList) {
            if (gift.getCategory().equals(Category.BOARD_GAMES)) {
                boardGames.add(gift);
            } else if (gift.getCategory().equals(Category.BOOKS)) {
                books.add(gift);
            } else if (gift.getCategory().equals(Category.CLOTHES)) {
                clothes.add(gift);
            } else if (gift.getCategory().equals(Category.SWEETS)) {
                sweets.add(gift);
            } else if (gift.getCategory().equals(Category.TECHNOLOGY)) {
                tech.add(gift);
            } else if (gift.getCategory().equals(Category.TOYS)) {
                toys.add(gift);
            }
        }
        boardGames.sort(this::sortPrices);
        books.sort(this::sortPrices);
        clothes.sort(this::sortPrices);
        sweets.sort(this::sortPrices);
        tech.sort(this::sortPrices);
        toys.sort(this::sortPrices);
    }

    /**
     * Method returns the corresponding list based on category
     * @param category gift category of the list we want
     * @return list with gifts with specified category
     */
    public List<Gift> getSpecifiedList(final Category category) {
        switch (category) {
            case BOARD_GAMES -> {
                return boardGames;
            }
            case TECHNOLOGY -> {
                return tech;
            }
            case TOYS -> {
                return toys;
            }
            case BOOKS -> {
                return books;
            }
            case CLOTHES -> {
                return clothes;
            }
            case SWEETS -> {
                return sweets;
            }
            default -> {
                return null;
            }
        }
    }

    private Integer sortPrices(final Gift o1, final Gift o2) {
        if (o1.getPrice().compareTo(o2.getPrice()) > 0) {
            return Double.compare(o1.getPrice(), o2.getPrice());
        } else {
            return Double.compare(o2.getPrice(), o1.getPrice());
        }
    }

    public List<Gift> getBoardGames() {
        return boardGames;
    }

    public void setBoardGames(final List<Gift> boardGames) {
        this.boardGames = boardGames;
    }

    public List<Gift> getBooks() {
        return books;
    }

    public void setBooks(final List<Gift> books) {
        this.books = books;
    }

    public List<Gift> getClothes() {
        return clothes;
    }

    public void setClothes(final List<Gift> clothes) {
        this.clothes = clothes;
    }

    public List<Gift> getSweets() {
        return sweets;
    }

    public void setSweets(final List<Gift> sweets) {
        this.sweets = sweets;
    }

    public List<Gift> getTech() {
        return tech;
    }

    public void setTech(final List<Gift> tech) {
        this.tech = tech;
    }

    public List<Gift> getToys() {
        return toys;
    }

    public void setToys(final List<Gift> toys) {
        this.toys = toys;
    }
}
