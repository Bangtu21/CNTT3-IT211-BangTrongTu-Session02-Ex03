package spring_boot.session02ex03.service;

import org.springframework.stereotype.Service;
import spring_boot.session02ex03.model.entity.Item;
import spring_boot.session02ex03.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Optional<Item> updateItem(Long id, Item newItem) {

        return itemRepository.findById(id)
                .map(item -> {

                    item.setName(newItem.getName());
                    item.setQuantity(newItem.getQuantity());
                    item.setPrice(newItem.getPrice());

                    return itemRepository.save(item);
                });
    }

    public boolean deleteItem(Long id) {

        if (!itemRepository.existsById(id)) {
            return false;
        }

        itemRepository.deleteById(id);

        return true;
    }
}
