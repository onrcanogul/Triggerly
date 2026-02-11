package triggerly.common.entity;

import java.util.Objects;

/**
 * Base entity abstraction.
 * Equality is based on identifier.
 */
public abstract class BaseEntity<ID> {

    protected ID id;

    protected BaseEntity(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity<?> that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

