## **Инструкция по запуску и тестированию приложения**
1. **Установите необходимые компоненты**
   Java JDK 17 или выше: Убедитесь, что у вас установлен JDK и настроена переменная окружения JAVA_HOME.
   Apache Maven: Установите Maven и проверьте установку с помощью команды:
   mvn -v
2. **Клонируйте или скачайте проект**
   Скачайте проект или клонируйте его из репозитория.
3. **Соберите проект**
   Перейдите в корневую папку проекта и выполните команду:
mvn clean install
4. **Запустите приложение**
   Запустите приложение с помощью команды:
mvn spring-boot:run
5. **Получите доступ к Swagger UI**
   Откройте браузер и перейдите по следующему адресу:
http://localhost:8080/openapi/swagger-ui.html
6. **Тестируйте API**
   Используйте интерфейс Swagger для тестирования эндпоинта /api/max-number. Убедитесь, что передаете корректный путь к файлу Excel и значение N.