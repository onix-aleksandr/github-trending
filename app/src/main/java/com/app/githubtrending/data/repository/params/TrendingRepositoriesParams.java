package com.app.githubtrending.data.repository.params;

import com.app.githubtrending.data.entity.FilterType;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record TrendingRepositoriesParams(String searchQuery, int perPage, int page,
                                         FilterType filter) {

    public String buildQuery() {
        LocalDateTime createdFrom;

        switch (filter) {
            case LastMonth:
                createdFrom = LocalDateTime.now().minusMonths(1);
                break;
            case LastWeek:
                createdFrom = LocalDateTime.now().minusWeeks(1);
                break;
            case LastDay:
                createdFrom = LocalDateTime.now().minusDays(1);
                break;
            default:
                return searchQuery;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String createdFromFormatted = createdFrom.format(formatter);

        String newQuery = "";

        if (!searchQuery.isBlank()) {
            try {
                String textQuery = URLEncoder.encode(searchQuery.trim(), StandardCharsets.UTF_8.name());
                newQuery = textQuery + "+";
            } catch (Exception ignored) {
            }
        }

        return newQuery + "created:>" + createdFromFormatted;
    }
}
