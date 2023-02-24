package com.itproger.shop.repo;

import com.itproger.shop.models.Item;
import org.springframework.data.repository.CrudRepository;
// Создание репозитория CRUD: C - create, R- Read, U - Update, D - Delete
// предоставляет базовый функционал для чтения, обновления, удаления, создания
public interface  ItemRepository extends CrudRepository<Item, Long> {
}
