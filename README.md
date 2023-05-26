# Farm Project
![image info](./logo.png)

# BACKEND

# Другие репозитории
Frontend: https://github.com/brash-ram/frontend-farm-project

Backend для телеграмм бота: https://github.com/LZTD1/iFarm_telegramBot

Service для статистики: https://github.com/ArtemkaOffical/IFermerAnalyticsService

`Project deployed on http://farm.null.moe/#/`

## Стек технологий

- Java 17
- Spring boot
- Spring Web
- Spring Data
- Spring Security
- Hibernate
- Swagger
- PostgreSQL
- Docker

swagger: /swagger/swagger-ui.html

## Реализовано:

### Фермерам

- `Электронные полки товаров:`

Товар представлен следующими данными - картинка, наименование, 
категория товара, регион продажи (область, республика), список оценок, 
цена, оптовая цена, минимальная оптовая цена, единица измерения
(кг или шт), дата создания, начало продаж, конец продаж

Действия:

Добавление, удаление, обновление, свои товары для фермера,
получение всех записей

В swagger - product-controller

- `Личный кабинет`

Пользователь представлен следующими данными - роли, пароль, 
email, фио, о себе, дата регистрации, рейтинг

В swagger - user-controller

Действия:

Регистрация с подтверждением почты, авторизация, редактирование данных, 
редактирование пароля

- `Выбор формы доставки`

Доставка представлена следующими данными - тип доставки
(самовывоз, доставка от нашей организации, доставка фермером), 
дата и время доставки, адрес откуда доставить и куда, периодичность 
повторения заказа, количество (единиц измерения), тип оплаты 
(картой, сбп, наличкой)

Действия:

Добавить, удалить, обновить, получить список текущих доставок

В swagger - delivery-controller

- `составление календаря отправки`

Представлено в виде полей у товара - дата ожидаемых начала и конца продаж

- `доступ к образовательным материалам`

Образовательные материалы представлены следующими данными - 
Название статьи, краткое описание, ссылка на сторонний источник. Фермеры могут получить
доступ ко всем статьям в виде списка. Добавление и редактирование материалов осуществляется
администратором.


В swagger - course-controller


### Клиентам

- `Личный кабинет`

Личный кабинет представлен как и для фермера, 
только с возможностью просмотра товаров в корзине

Действия:

Просмотреть список товаров в корзине, регистрация с подтверждением почты, 
авторизация, редактирование данных, редактирование пароля

- `Система скидок`

Система скидок представлена оптовой ценой и возможностью 
связаться с фермером по его email для обсуждения
деталей

- `Выбор удобной системы оплаты`

При заказе товара можно выбрать систему оплаты (картой, сбп, наличкой)

- `Доступ к системе рейтинга фермеров`

Пользователю можно добавить, изменить, удалить оценку. 
У пользователя есть отображение средней оценки.

- `Доступ к оптовым заказам`

Оптовые заказы доступны при заказе от определенной суммы, которую 
указывают фермеры. При таком заказе сумма рассчитывается из 
оптовой цены, которая указывается отдельно

- `Подписка на продуктовую корзину`

Подписка реализована через указание периода повторения заказа 
в днях для заказа.

### Администраторам
Работа администратора осуществляется через тг бот. 
- `возможность вносить изменения в работу`

Администратор имеет право вносить изменение в любые поля
и объекты. Поэтому в случае ошибочного ввода или иных проблем
администратор системы сможет все решить
- `статистика`
При добавлении, изменении, удалении продукта мы отправляем на 
сервис расчета статистики следующие данные: категория товара, наименование, 
локация(регион), дата создания продукта.


При добавлении, изменении, удалении заказа мы отправляем на
сервис расчета статистики следующие данные: продукт, дата ожидаемой доставки,
количество, единица измерения

