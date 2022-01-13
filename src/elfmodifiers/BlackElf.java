package elfmodifiers;

import data.Database;

public final class BlackElf implements Visitable {
    private Database database;

    public BlackElf(final Database database) {
        this.database = database;
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
}
