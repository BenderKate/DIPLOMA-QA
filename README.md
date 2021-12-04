### Бизнес часть

Приложение представляет из себя веб-сервис.

![](reports/service.png)

Приложение предлагает купить тур по определённой цене с помощью двух способов:
1. Обычная оплата по дебетовой карте
1. Уникальная технология: выдача кредита по данным банковской карты

Само приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:
* сервису платежей (далее - Payment Gate)
* кредитному сервису (далее - Credit Gate)

Приложение должно в собственной СУБД сохранять информацию о том, каким способом был совершён платёж и успешно ли он был совершён (при этом данные карт сохранять не допускается).

### Запуск SUT
1. Клонировать репозиторий командой git clone
2. Открыть проект в IntelliJ IDEA Ultimate
3. Запустить контейнеры БД командой docker-compose up -d
4. Ввести в терминале команду "java -jar artifacts/aqa-shop.jar"
5. Открыть в браузере адрес "localhost:8080"

### Запуск тестов и отчетов Allure
1. В терминале ввести команду ./gradlew clean test
2. В терминале ввести команду ./gradlew allureReport


