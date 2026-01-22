package com.minisocial.config;

import com.minisocial.domain.Creator;
import com.minisocial.domain.User;
import com.minisocial.domain.Video;
import com.minisocial.application.creator.port.out.CreatorRepository;
import com.minisocial.application.user.port.out.UserRepository;
import com.minisocial.application.video.port.out.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CreatorRepository creatorRepository;
    private final UserRepository userRepository;
    private final VideoRepository videoRepository;

    public DataInitializer(CreatorRepository creatorRepository,
            UserRepository userRepository,
            VideoRepository videoRepository) {
        this.creatorRepository = creatorRepository;
        this.userRepository = userRepository;
        this.videoRepository = videoRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // --- Declaración de variables con alcance global ---
        Creator c1;
        Creator c2;
        Creator c3;

        // --- CREADORES ---
        if (creatorRepository.count() == 0) {
            c1 = creatorRepository
                    .save(new Creator(null, "YoSoyPlex", "yosoyplex@example.com", "Creador famoso de viajes y retos"));
            c2 = creatorRepository
                    .save(new Creator(null, "AnaTech", "anatech@example.com", "Creadora de contenido tech y IA"));
            c3 = creatorRepository
                    .save(new Creator(null, "CarlosVlogs", "carlosvlogs@example.com", "Vlogs de viajes y lifestyle"));
        } else {
            c1 = creatorRepository.findById(1L).orElseThrow();
            c2 = creatorRepository.findById(2L).orElseThrow();
            c3 = creatorRepository.findById(3L).orElseThrow();
        }

        // --- VIDEOS ---
        if (videoRepository.count() == 0) {
            videoRepository.save(new Video(null, c1, "Reto extremo en la montaña", "Superando desafíos épicos", 0));
            videoRepository.save(new Video(null, c1, "Viaje a Islandia", "Aventuras y paisajes increíbles", 0));

            videoRepository.save(new Video(null, c2, "Introducción a IA", "Explicación sencilla", 0));
            videoRepository.save(new Video(null, c2, "Tutorial Spring Boot", "Proyecto demo", 0));

            videoRepository.save(new Video(null, c3, "Viaje a Japón", "Vlog de mis vacaciones", 0));
            videoRepository.save(new Video(null, c3, "Rutina diaria", "Mi día a día", 0));
        }

        // --- USUARIOS ---
        User u1;
        User u2;
        User u3;

        if (userRepository.count() == 0) {
            u1 = userRepository.save(
                    new User(null, "David Martín", "david@example.com", "Fan del contenido tech", new HashSet<>()));
            u2 = userRepository
                    .save(new User(null, "Laura Pérez", "laura@example.com", "Amante de gaming", new HashSet<>()));
            u3 = userRepository
                    .save(new User(null, "Miguel Torres", "miguel@example.com", "Fan de vlogs", new HashSet<>()));

            // --- RELACIONES FOLLOW ---
            u1.getFollowingCreators().add(c1);
            u1.getFollowingCreators().add(c2);
            userRepository.save(u1);

            u2.getFollowingCreators().add(c2);
            userRepository.save(u2);

            u3.getFollowingCreators().add(c3);
            userRepository.save(u3);
        }

        System.out.println("✅ Datos iniciales cargados: 3 creadores, 2 videos por creador, 3 usuarios y follows.");
    }
}
