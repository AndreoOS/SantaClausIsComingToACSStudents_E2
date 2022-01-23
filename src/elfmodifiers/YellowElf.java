package elfmodifiers;

import data.Database;
import data.GiftList;

public final class YellowElf implements Visitable {
    private Database database;
    private GiftList giftList;

    public YellowElf(final Database database, final GiftList giftList) {
        this.database = database;
        this.giftList = giftList;
    }

    @Override
    public void accept(final Visitor v) {
        v.visit(this);
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(final Database database) {
        this.database = database;
    }

    public GiftList getGiftList() {
        return giftList;
    }

    public void setGiftList(final GiftList giftList) {
        this.giftList = giftList;
    }
}
