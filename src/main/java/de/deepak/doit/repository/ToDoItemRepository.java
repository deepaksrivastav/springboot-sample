package de.deepak.doit.repository;

import de.deepak.doit.model.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by deepak on 14.12.15.
 */

public interface ToDoItemRepository extends JpaRepository<ToDoItem, Integer> {
}
