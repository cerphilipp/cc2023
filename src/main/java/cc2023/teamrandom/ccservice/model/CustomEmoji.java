package cc2023.teamrandom.ccservice.model;

public record CustomEmoji(String shortcode,
                          String url,
                          String static_url,
                          String visible_in_picker,
                          String category) {
}