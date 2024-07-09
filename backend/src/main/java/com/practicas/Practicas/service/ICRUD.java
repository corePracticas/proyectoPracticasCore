package com.practicas.Practicas.service;

import java.util.List;

public interface ICRUD <T,ID>{
    T findBy (ID id);
    T create (T t);
    T edit (T t);
    void deleteBy (ID id);
    List <T> findAll();
}
