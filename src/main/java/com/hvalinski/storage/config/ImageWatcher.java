package com.hvalinski.storage.config;

import java.io.IOException;
import java.nio.file.*;

public class ImageWatcher {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Создание WatchService
        WatchService watchService = FileSystems.getDefault().newWatchService();

        // Регистрация директории для отслеживания изменений
        Path directory = Paths.get("file:images/");
        directory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

        // Бесконечный цикл ожидания событий
        while (true) {
            WatchKey key = watchService.take();

            // Обработка событий
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                // Проверка, что произошло событие создания файла
                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    // Обновление обработчика ресурсов
                    // ...
                }
            }

            // Сброс ключа и продолжение ожидания событий
            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
    }
}