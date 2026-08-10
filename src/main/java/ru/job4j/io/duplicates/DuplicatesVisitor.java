package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty fileProperty = new FileProperty(
                file.toFile().length(), file.toFile().getName());
        List<Path> paths = files.get(fileProperty);
        if (paths == null) {
            paths = new ArrayList<>();
        }
        files.put(fileProperty, paths);
        paths.add(file.toAbsolutePath());
        return super.visitFile(file, attributes);
    }

    public List<Path> getDuplicatesList() {
        return files.values()
                .stream()
                .filter(s -> s.size() > 1)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
