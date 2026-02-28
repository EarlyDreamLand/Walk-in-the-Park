package dev.efnilite.ip.leaderboard;

import org.jetbrains.annotations.Nullable;

/**
 * Represents a record, used to keep track of the score a player may achieve.
 *
 * @param name       The name of the player
 * @param score      The score achieved
 * @param time       The time it took to achieve this score
 * @param difficulty The difficulty of this run
 * @param texture    The Base64 texture value of the player's skin (Nullable)
 */
public record Score(String name, String time, String difficulty, int score, @Nullable String texture) {

    /**
     * Gets a {@link Score} instance from a string
     *
     * @param string The string
     * @return a {@link Score} instance based off the provided string
     */
    public static Score fromString(String string) {
        String[] parts = string.split(",");

        String texture = null;
        if (parts.length > 4) {
            texture = parts[4];
            if (texture.equals("null") || texture.isEmpty()) {
                texture = null;
            }
        }

        return new Score(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), texture);
    }

    /**
     * @return This score's time in millis.
     */
    public int getTimeMillis() {
        String[] split = time.split(":");

        int m = Integer.parseInt(split[0]);
        int s = Integer.parseInt(split[1]);
        int ms = Integer.parseInt(split[2]);

        return m * 60 * 1000 + s * 1000 + ms;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s", name, time, difficulty, score, texture == null ? "null" : texture);
    }
}