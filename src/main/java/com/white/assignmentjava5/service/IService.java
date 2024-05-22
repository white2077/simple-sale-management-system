package com.white.assignmentjava5.service;

import java.util.List;

public interface IService<T,K>{
    List<T> getAll();
    T getByMa(K ma);
    T insert(T t);
    T update(T t,String ma);
    void delete(K ma);
}
