package com.practicas.Practicas.service.impl;

import com.practicas.Practicas.repository.IGeneralRepository;
import com.practicas.Practicas.service.ICRUD;

public abstract class ICRUDimpl <T,ID> implements ICRUD <T,ID> {
    protected abstract IGeneralRepository <T,ID> getRepo();
    @Override
    public T findBy(ID id) {
        return getRepo().findById(id).orElse(null);
    }

    @Override
    public T create(T t) {
        return getRepo().save(t);
    }

    @Override
    public T edit(T t) {
        return getRepo().save(t);
    }

    @Override
    public void deleteBy(ID id) {
        getRepo().deleteById(id);
    }
}
