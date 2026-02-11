package triggerly.common.ddd;

import triggerly.common.entity.BaseEntity;
import triggerly.common.event.domain.BaseDomainEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Base aggregate root with domain event support.
 */
public abstract class AggregateRoot<ID> extends BaseEntity<ID> {

    private final List<BaseDomainEvent> domainEvents = new ArrayList<>();

    protected AggregateRoot(ID id) {
        super(id);
    }

    protected void registerEvent(BaseDomainEvent event) {
        domainEvents.add(event);
    }

    public List<BaseDomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    public void clearDomainEvents() {
        domainEvents.clear();
    }
}
