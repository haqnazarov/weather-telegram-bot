package uz.haqnazarov.telegram_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.haqnazarov.telegram_bot.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByChatIdAndDeletedFalse(Long chatId);
}
