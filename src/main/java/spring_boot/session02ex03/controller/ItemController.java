package spring_boot.session02ex03.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_boot.session02ex03.model.entity.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {
    private List<Item> items = new ArrayList<>();

    // GET ITEM BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                return new ResponseEntity<>(
                        item,
                        HttpStatus.OK
                );
            }
        }

        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }

    // Tạo
    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        item.setId((long) (items.size() + 1));
        items.add(item);
        return new ResponseEntity<>(
                item,
                HttpStatus.CREATED
        );
    }

    // Cập nhật
    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(
            @PathVariable Long id,
            @RequestBody Item newItem
    ) {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                item.setName(newItem.getName());
                item.setQuantity(newItem.getQuantity());
                item.setPrice(newItem.getPrice());
                return new ResponseEntity<>(
                        item,
                        HttpStatus.OK
                );
            }
        }
        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }

    // Xóa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                items.remove(item);
                return new ResponseEntity<>(
                        HttpStatus.NO_CONTENT
                );
            }
        }
        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }
}
