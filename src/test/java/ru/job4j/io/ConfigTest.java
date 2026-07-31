package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithCommentThenNull() {
        String path = "./data/pair_with_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isNull();
    }

    @Test
    void whenPairBroken() {
        String path = "./data/pair_broken.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid config file");
    }
}
