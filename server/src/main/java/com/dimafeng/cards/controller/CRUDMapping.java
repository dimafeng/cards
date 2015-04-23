package com.dimafeng.cards.controller;


import com.dimafeng.cards.exception.OperationNotPermited;
import com.dimafeng.cards.model.Model;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CRUDMapping<T extends Model, R extends T> {

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    default List<T> getAll(Authentication authentication) {
        //return getRepository().findAll();
        throw new OperationNotPermited();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    default T get(@PathVariable("id") String id, Authentication authentication) {
        T one = getRepository().findOne(id);
        checkRight(one, authentication);
        return one;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    default T save(@PathVariable("id") String id, @RequestBody R item, Authentication authentication) throws Exception {
        item.setId(id);
        return saveUpdate(item, authentication);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    default T saveUpdate(@RequestBody R item, Authentication authentication) throws Exception {
        checkRight(item, authentication);
        processBeforeSave(item, authentication);
        return getRepository().save(item);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    default void delete(@PathVariable("id") String id, Authentication authentication) {
        checkRight(getRepository().findOne(id), authentication);
        getRepository().delete(id);
    }

    void processBeforeSave(R item, Authentication authentication) throws Exception;

    MongoRepository<T, String> getRepository();

    default void checkRight(T element, Authentication authentication) {

    }
}
