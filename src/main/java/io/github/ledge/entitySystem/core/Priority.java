package io.github.ledge.entitySystem.core;

/**
 * An Enum which represent the default hierarchy which is used to determine
 * which entity system should be used first. (The EntitySystem is event-driven)
 *
 * The lowest priority will be called first. This means that an EntitySystem with the highest
 * priority will actually define what it should do with the entity.
 *
 * eg; an EntitySystem with Priority.NORMAL changes the name of Entity X
 *  -> another EntitySystem with Priority.HIGHEST does the same but with another name
 *
 *  Result: The Entity will have the name given by the second EntitySystem.
 */
public enum Priority {

    LOWEST(0),
    LOWER(1),
    LOW(2),
    NORMAL(3),
    HIGH(4),
    HIGHER(5),
    HIGHEST(6);

    private final int value;

    private Priority(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
