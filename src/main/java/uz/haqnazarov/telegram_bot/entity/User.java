package uz.haqnazarov.telegram_bot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.haqnazarov.telegram_bot.entity.constants.State;
import uz.haqnazarov.telegram_bot.entity.template.BaseEntity;

@Getter
@Setter
@Builder
@Table(name = "users")
@SQLDelete(sql = "update users set deleted = true where id=?")
@SQLRestriction(value = "deleted = false")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends BaseEntity {
    @Column(nullable = false, unique = true)
    private Long chatId;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;
}
