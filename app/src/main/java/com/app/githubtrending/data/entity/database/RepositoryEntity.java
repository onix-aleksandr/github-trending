package com.app.githubtrending.data.entity.database;

import static com.app.githubtrending.data.entity.database.RepositoryEntity.TABLE_NAME;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.app.githubtrending.data.entity.Repository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Entity(tableName = TABLE_NAME)
public class RepositoryEntity {

    public RepositoryEntity(
        long id,
        String login,
        String name,
        String avatarUrl,
        String description,
        int starsCount,
        String url,
        long createdAtEpochSec,
        long forksCount,
        String language
    ) {

        this.id = id;
        this.login = login;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.description = description;
        this.starsCount = starsCount;
        this.url = url;
        this.createdAtEpochSec = createdAtEpochSec;
        this.forksCount = forksCount;
        this.language = language;
    }

    public static RepositoryEntity toRepositoryEntity(Repository repoDetailed) {
        return new RepositoryEntity(
            repoDetailed.getId(),
            repoDetailed.getOwnerUsername(),
            repoDetailed.getName(),
            repoDetailed.getImageUrl(),
            repoDetailed.getDescription(),
            repoDetailed.getStarsCount(),
            repoDetailed.getUrl(),
            repoDetailed.getCreatedAt().toEpochSecond(ZoneOffset.UTC),
            repoDetailed.getForksCount(),
            repoDetailed.getLanguage()
        );
    }

    public Repository toRepository() {
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(createdAtEpochSec, 0, ZoneOffset.UTC);

        return new Repository(
            this.id,
            this.starsCount,
            this.login,
            this.name,
            this.avatarUrl,
            this.description,
            this.url,
            dateTime,
            forksCount,
            language
        );
    }

    public static final String ID = "id";
    public static final String TABLE_NAME = "repository_table";
    public static final String CREATED_AT = "created_at";
    public static final String STARS_COUNT = "stars_count";
    @PrimaryKey
    @ColumnInfo(name = ID)
    private long id;
    @ColumnInfo(name = STARS_COUNT)
    private int starsCount;
    @ColumnInfo(name = "forks")
    private long forks;
    @ColumnInfo(name = "url")
    private String url;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = CREATED_AT)
    private long createdAtEpochSec;
    @ColumnInfo(name = "forks_count")
    private long forksCount;
    @ColumnInfo(name = "language")
    private String language;
    @ColumnInfo(name = "login")
    private String login;
    @ColumnInfo(name = "avatar_url")
    private String avatarUrl;

    public int getStarsCount() {
        return starsCount;
    }

    public void setStarsCount(int value) {
        this.starsCount = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public long getForks() {
        return forks;
    }

    public void setForks(long value) {
        this.forks = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public long getCreatedAtEpochSec() {
        return createdAtEpochSec;
    }

    public void setCreatedAtEpochSec(long value) {
        this.createdAtEpochSec = value;
    }

    public long getForksCount() {
        return forksCount;
    }

    public void setForksCount(long value) {
        this.forksCount = value;
    }

    public String getUrl() {
        return url;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String value) {
        this.language = value;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}

