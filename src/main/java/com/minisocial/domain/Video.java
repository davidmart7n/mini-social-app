package com.minisocial.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación ManyToOne con Creator
    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private Creator creator;

    private String title;

    private String description;

    private int likes = 0;
}
