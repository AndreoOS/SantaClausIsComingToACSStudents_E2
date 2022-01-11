package entities;

import java.util.ArrayList;
import java.util.List;

public final class Children {
    private List<OutputChild> children;

    public Children() {
        children = new ArrayList<>();
    }

    public List<OutputChild> getChildren() {
        return children;
    }

    public void setChildren(final List<OutputChild> children) {
        this.children = children;
    }
}
