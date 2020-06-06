package ru.otus.hw07.pojo;

import java.util.Set;
import java.util.TreeSet;

public class ATMState {
    private final Set<Container> containers = new TreeSet<>();

    public void setContainers(Set<Container> containers) {
        containers.forEach(container -> this.containers.add(container.copy()));
    }

    public Set<Container> getContainers() {
        return containers;
    }
}
