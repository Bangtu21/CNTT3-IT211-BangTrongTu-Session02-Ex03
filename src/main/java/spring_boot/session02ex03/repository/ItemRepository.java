package spring_boot.session02ex03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_boot.session02ex03.model.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
