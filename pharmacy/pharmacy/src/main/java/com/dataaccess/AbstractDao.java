package com.dataaccess;

import java.util.List;

public abstract class AbstractDao<E> {
    public abstract void add(E entity);
    public abstract void edit(E entity);
    public abstract void remove(E entity);

    public abstract List<E> get(int idEntity);

    public abstract List<E> list();
}
