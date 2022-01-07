[![codecov](https://codecov.io/gh/Temzor/tracker_webapp/branch/master/graph/badge.svg?token=yehN6sL6mU)](https://codecov.io/gh/Temzor/tracker_webapp)
[![Java CI with Maven](https://github.com/Temzor/tracker_webapp/actions/workflows/maven.yml/badge.svg)](https://github.com/Temzor/job4j_grabber/actions/workflows/maven.yml)

### Описание:
CMR- система для фиксации обращении от пользователя. В идеальном состоянии будет выглядеть так. \
Уже сделано:
* UI
* Базовое окно заведения заявок с полями. 
* Валидация main полей. 

Буду доделывать:
* Доступ к UI CRM по логину и паролю.
* Работа с разными темами web-приложения.
* Возможность приложить файлы и картинки к заявке.

### Технологии:
* Maven, GitHub Actions, Checkstyle
* Spring Boot.
* Spring Data.
* Vaadin framework (https://vaadin.com/).
* JDBC для конеккта к БД.
* H2 для эмуляции коннекта к БД.
* Приложение собирается в jar.

Проект состоит из:
* Доменный класс Item в виде модели данных.
* Интерфейс ItemRepo для работы (в т.ч. создание таблиц) с БД в виде запросов HQL extend SQL.
* Класс ItemEditor для агрегации всех изменяемых данных в проекте.
* Класс ItemList для сборки проекта + разрисовка Grid и Layout. 
