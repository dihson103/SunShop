package com.dinhson.sunshop.confirmToken;

import com.dinhson.sunshop.appUser.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ConfirmToken")
public class ConfirmToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String confirmToken;

    @Column(nullable = false)
    private LocalDateTime createAt;

    @Column(nullable = false)
    private LocalDateTime endAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "user_id"
    )
    private User user;

    public ConfirmToken(String confirmToken,
                        LocalDateTime createAt,
                        LocalDateTime endAt,
                        User user) {
        this.confirmToken = confirmToken;
        this.createAt = createAt;
        this.endAt = endAt;
        this.user = user;
    }
}
