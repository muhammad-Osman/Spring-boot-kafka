package dev.usman.consumer.repository;

import dev.usman.consumer.entity.Wikimedia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaRepository extends JpaRepository<Wikimedia, Long> {
}
