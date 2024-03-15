package com.app.githubtrending.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.app.githubtrending.data.entity.network.ApiRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Repository implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(starsCount);
        dest.writeString(name);
        dest.writeString(ownerUsername);
        dest.writeString(imageUrl);
        dest.writeString(description);
        dest.writeString(url);
        dest.writeString(createdAt.toString());
        dest.writeLong(forksCount);
        dest.writeString(language);
    }

    protected Repository(Parcel in) {
        id = in.readLong();
        starsCount = in.readInt();
        name = in.readString();
        ownerUsername = in.readString();
        imageUrl = in.readString();
        description = in.readString();
        url = in.readString();
        createdAt = LocalDateTime.parse(in.readString());
        forksCount = in.readLong();
        language = in.readString();
    }

    public static final Creator<Repository> CREATOR = new Creator<Repository>() {
        @Override
        public Repository createFromParcel(Parcel in) {
            return new Repository(in);
        }

        @Override
        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository yourClass = (Repository) o;
        return id == yourClass.id &&
            starsCount == yourClass.starsCount &&
            forksCount == yourClass.forksCount &&
            Objects.equals(name, yourClass.name) &&
            Objects.equals(ownerUsername, yourClass.ownerUsername) &&
            Objects.equals(imageUrl, yourClass.imageUrl) &&
            Objects.equals(description, yourClass.description) &&
            Objects.equals(url, yourClass.url) &&
            Objects.equals(createdAt, yourClass.createdAt) &&
            Objects.equals(language, yourClass.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, starsCount, name, ownerUsername, imageUrl, description, url, createdAt, forksCount, language);
    }
}
