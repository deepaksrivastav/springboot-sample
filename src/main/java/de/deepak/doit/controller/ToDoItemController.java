package de.deepak.doit.controller;

import de.deepak.doit.model.ToDoItem;
import de.deepak.doit.repository.ToDoItemRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by deepak on 14.12.15.
 */
@RestController
@RequestMapping("/item")
public class ToDoItemController {

    @Autowired
    private ToDoItemRepository itemRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "findItems", nickname = "findItems")
    public List<ToDoItem> findItems() {
        return itemRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ToDoItem addItem(@RequestBody ToDoItem item) {
        item.setId(null);
        return itemRepository.saveAndFlush(item);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ToDoItem getItem(@PathVariable Integer id){
        return itemRepository.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ToDoItem updateItem(@RequestBody ToDoItem item, @PathVariable Integer id) {
        item.setId(id);
        return itemRepository.saveAndFlush(item);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteItem(@PathVariable Integer id){
        itemRepository.delete(id);
    }
}
