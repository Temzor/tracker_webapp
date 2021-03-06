[![Java CI with Maven](https://github.com/Temzor/tracker_webapp/actions/workflows/maven.yml/badge.svg)](https://github.com/Temzor/job4j_grabber/actions/workflows/maven.yml)

### Описание:
CRM - система для фиксации обращении от пользователя. В идеальном состоянии будет выглядеть так. \
Уже сделано:
* UI;
* Базовое окно заведения заявок с полями;
* Валидация main полей. 

Буду доделывать:
* Доступ к UI CRM по логину и паролю;
* Работа с разными темами web-приложения;
* Возможность приложить файлы и картинки к заявке.

### Технологии:
* Maven, GitHub Actions, Checkstyle;
* Spring Boot;
* Spring Data;
* Vaadin framework (https://vaadin.com/);
* JDBC для конеккта к БД;
* H2 для эмуляции коннекта к БД;
* Приложение собирается в jar.

### Проект состоит из:
* Доменного класса Item в виде модели данных;
* Интерфейса ItemRepo для работы (в т.ч. создание таблиц) с БД в виде запросов HQL extend SQL;
* Класса ItemEditor для агрегации всех изменяемых данных в проекте;
* Класса ItemList для сборки проекта + разрисовка Grid и Layout. 

### Описание UI:
#### Фильтры:
1) По совпадению полей. Достаточно начать вводить ключевые слова:
![Фильтр по ключам](https://github.com/Temzor/tracker_webapp/blob/master/src/Images/Filters_1.png)
2) Фильтры сортировки колонок ASC DESC (по возрастанию и уменьшению, реализована через компаратор JS):
![Фильтры ASC DESC](https://github.com/Temzor/tracker_webapp/blob/master/src/Images/Filters_2.png)

#### Интерфейс создания заявки:
1) Кнопка "New application" - "Cоздания заявки":

![Кнопка "New application" - "Cоздания заявки"](https://github.com/Temzor/tracker_webapp/blob/master/src/Images/Created_1.png)

2) Поле Дата/Время, заполняется автоматически(можно изменить) текущем временем согласно UTC.

![Поле Дата/Время](https://github.com/Temzor/tracker_webapp/blob/master/src/Images/Created_DateTime.png)

3) Поле "Company name" - "Название компании":

![ Поле "Company name" - "Название компании"](https://github.com/Temzor/tracker_webapp/blob/master/src/Images/Created_CompanyName.png)

4) Поле "Select force" - "Влияние на бизнес-процессы", согласно определению в ITIL (https://habr.com/ru/company/muk/blog/265229/):

![Поле "Select force" - "Влияние на бизнес-процессы"](https://github.com/Temzor/tracker_webapp/blob/master/src/Images/Created_Force.png)

5) Поле "Select SLA in hours" - "Время договорных сроков SLA в часах" (https://habr.com/ru/company/dcmiran/blog/468825/):

![Поле "Select SLA in hours" - "Время договорных сроков SLA в часах"](https://github.com/Temzor/tracker_webapp/blob/master/src/Images/Created_SLA.png)

6) Поле "Phone number" - "Номер телефона":

![Поле "Phone number" - "Номер телефона"](https://github.com/Temzor/tracker_webapp/blob/master/src/Images/Created_PhoneNumber.png)

7) Поле "Description" - "Описание обращения":

![Поле "Description" - "Описание обращения"](https://github.com/Temzor/tracker_webapp/blob/master/src/Images/Created_Description.png)

#### Кнопки действий над полями обращения:
1) Кнопка "Save" - "Сохранить", необходима для сохранения или изменения обращения:

![Кнопка "Save"  - "Сохранить"](https://github.com/Temzor/tracker_webapp/blob/master/src/Images/Button_Save.png)

2) Кнопка "Cancel" - "Отмена", необходима для отмены изменений обращения:

![Кнопка "Cancel" - "Отмена"](https://github.com/Temzor/tracker_webapp/blob/master/src/Images/Button_Cancel.png)

3) Кнопка "Delete" - "Удалить", необходима для удаления обращения при изменении или создании обращения:

![Кнопка "Delete" - "Удалить"](https://github.com/Temzor/tracker_webapp/blob/master/src/Images/Button_Delete.png)
