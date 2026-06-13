package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name)
                .isNotNull()
                .isNotEmpty()
                .isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name)
                .isNotNull()
                .isNotEmpty()
                .isEqualTo("Tetrahedron");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(-1, 10);
        String name = box.whatsThis();
        assertThat(name)
                .isNotNull()
                .isNotEmpty()
                .isEqualTo("Unknown object");
    }

    @Test
    void isExist() {
        Box box = new Box(8, 10);
        boolean boxExist = box.isExist();
        assertThat(boxExist).isTrue();
    }

    @Test
    void isNotExist() {
        Box box = new Box(-1, 10);
        boolean boxExist = box.isExist();
        assertThat(boxExist).isFalse();
    }

    @Test
    void getNumberOfVerticesEqualsFour() {
        Box box = new Box(4, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex)
                .isNotNegative()
                .isEqualTo(4);
    }

    @Test
    void getAreaTetrahedron() {
        Box box = new Box(4, 10);
        double area = box.getArea();
        assertThat(area)
                .isNotNegative()
                .isEqualTo(173.205, withPrecision(0.006d));
    }

    @Test
    void getAreaSphere() {
        Box box = new Box(0, 10);
        double area = box.getArea();
        assertThat(area)
                .isNotNegative()
                .isEqualTo(1256.637, withPrecision(0.006d));
    }
}
