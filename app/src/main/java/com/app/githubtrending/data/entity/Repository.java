package com.app.githubtrending.data.entity;

import androidx.annotation.NonNull;

import com.app.githubtrending.data.entity.network.ApiRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Repository {

    public Repository(
        long id,
        String ownerUsername,
        String name,
        String imageUrl,
        String description,
        String url,
        LocalDateTime createdAt,
        int starsCount,
        long forksCount,
        String language
    ) {
        this.id = id;
        this.ownerUsername = ownerUsername;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.starsCount = starsCount;
        this.url = url;
        this.createdAt = createdAt;
        this.forksCount = forksCount;
        this.language = language;
    }

    public Repository(ApiRepository repository) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");

        LocalDateTime dateTime = repository.getCreatedAt() != null ?
            LocalDateTime.parse(repository.getCreatedAt(), formatter) : LocalDateTime.now();

        id = repository.getId();
        ownerUsername = (repository.getOwner() != null && repository.getOwner().getLogin() != null) ?
            repository.getOwner().getLogin() : "";
        name = (repository.getName() != null) ? repository.getName() : "";
        imageUrl = (repository.getOwner() != null && repository.getOwner().getAvatarUrl() != null) ?
            repository.getOwner().getAvatarUrl() : "";
        description = (repository.getDescription() != null) ? repository.getDescription() : "";
        starsCount = repository.getStargazersCount();
        url = (repository.getUrl() != null) ? repository.getUrl() : "";
        createdAt = dateTime;
        forksCount = repository.getForks();
        language = (repository.getLanguage() != null) ? repository.getLanguage() : "";
    }

    private final long id;

    private final int starsCount;

    private final String name;

    private final String ownerUsername;
    private final String imageUrl;
    private final String description;
    private final String url;
    private final LocalDateTime createdAt;
    private final long forksCount;
    private final String language;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public int getStarsCount() {
        return starsCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public long getForksCount() {
        return forksCount;
    }

    public String getUrl() {
        return url;
    }

    public String getLanguage() {
        return language;
    }

    @NonNull
    @Override
    public String toString() {
        return "Repository{" +
            "id=" + id +
            ", starsCount=" + starsCount +
            ", name='" + name + '\'' +
            ", ownerUsername='" + ownerUsername + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            ", description='" + description + '\'' +
            ", url='" + url + '\'' +
            ", createdAt=" + createdAt +
            ", forksCount=" + forksCount +
            ", language='" + language + '\'' +
            '}';
    }
}
