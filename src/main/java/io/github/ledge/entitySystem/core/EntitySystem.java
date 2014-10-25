package io.github.ledge.entitySystem.core;

public abstract class EntitySystem {

    private boolean isRunning;
    private Priority priority;

    public EntitySystem() {
        this(Priority.NORMAL);
    }

    public EntitySystem(Priority priority) {
        this.isRunning = true;
        this.priority = priority;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public abstract void update(float delta);

    public abstract void onAddedToEngine();

    public abstract void onRemovedFromEngine();

    public boolean isRunning() {
        return this.isRunning;
    }

    public void setRunning(boolean running) {
        this.isRunning = running;
    }
}
