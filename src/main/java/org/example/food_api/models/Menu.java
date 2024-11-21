package org.example.food_api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "menu_name", nullable = false)
    private String menuName;

    @Size(max = 255)
    @Column(name = "icon")
    private String icon;

    @Size(max = 255)
    @Column(name = "url")
    private String url;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "status", nullable = false)
    private Byte status;

    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at")
    private Instant createdAt;

    @ColumnDefault("current_timestamp()")
    @Column(name = "updated_at")
    private Instant updatedAt;

}