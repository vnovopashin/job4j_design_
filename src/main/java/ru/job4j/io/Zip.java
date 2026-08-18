package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Класс реализует утилиту для архивации папки.
 * При запуске указывается папка, которую мы хотим архивировать, например: c:\project\job4j\.
 * В качестве ключа передается расширение файлов, которые не нужно включать в архив
 * Например: java -jar pack.jar -d=c:\project\job4j\ -e=class -o=project.zip,
 * где java -jar pack.jar - Это собранный jar.
 * -d - directory - которую мы хотим архивировать
 * -e - exclude - исключить файлы *.class
 * -o - output - во что мы архивируем.
 */
public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            Path path = sources.get(0).getParent();
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getName().replace(path + "\\", "")));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}
