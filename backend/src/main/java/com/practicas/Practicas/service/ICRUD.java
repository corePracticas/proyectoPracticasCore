package com.practicas.Practicas.service;

public interface ICRUD <T,ID>{
    T findBy (ID id);
    T create (T t);
    T edit (T t);
    void deleteBy (ID id);
}
