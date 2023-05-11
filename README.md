# Приложение "Работа мечты"
[![Java CI with Maven](https://github.com/svoh86/job4j_dreamjob/actions/workflows/maven.yml/badge.svg)](https://github.com/svoh86/job4j_dreamjob/actions/workflows/maven.yml)

+ [О проекте](#О-проекте)
+ [Технологии](#Технологии)
+ [Требования к окружению](#Требования-к-окружению)
+ [Запуск проекта](#Запуск-проекта)
+ [Взаимодействие с приложением](#Взаимодействие-с-приложением)
+ [Контакты](#Контакты)

## О проекте
Web-приложение "Работа мечты".

В системе две модели: вакансии и кандидаты. 
Для использования требуется пройти регистрацию.
Кандидаты будут публиковать резюме. Кадровики будут публиковать вакансии о работе.
Редактировать и удалять можно только свои публикации.

## Технологии
+ **Maven 3.8**
+ **Spring Boot 2.7.4**
+ **HTML 5**, **BOOTSTRAP 5**, **Thymeleaf 3.0.15**
+ **JDBC**, **PostgreSQL 14**
+ **Тестирование:** **Liquibase 3.6.2**, **H2 2.1.214**, **AssertJ 3.23.1**
+ **Java 17**
+ **Checkstyle 3.1.2**

## Требования к окружению
+ **Java 17**
+ **Maven 3.8**
+ **PostgreSQL 14**

## Запуск проекта
Перед запуском проекта необходимо настроить подключение к БД в соответствии с параметрами,
указанными в src/main/resources/db.properties, или заменить на свои параметры.

Варианты запуска приложения:
1. Упаковать проект в jar архив (job4j_dreamjob/target/job4j_dreamjob-1.0.jar):
``` 
mvn package
``` 
Запустить приложение:
```
java -jar job4j_dreamjob-1.0.jar 
```
2. Запуск через IDE

Перейти к папке 
``` src/main/java ``` и файлу ```dreamjob/Main.java```

## Взаимодействие с приложением
<p>Для использования требуется пройти регистрацию.
Если такая почта уже зарегистрирована, то будет выведено предупреждение. Регистрация не произойдет.
</p>

<p>Регистрация:</p>

![alt text](https://github.com/svoh86/job4j_dreamjob/blob/master/src/main/java/dreamjob/image/start.png)
<p>Если почта уже зарегистрирована</p>

![alt text](https://github.com/svoh86/job4j_dreamjob/blob/master/src/main/java/dreamjob/image/failRegistration.png)

<p>Авторизация:</p>

![alt text](https://github.com/svoh86/job4j_dreamjob/blob/master/src/main/java/dreamjob/image/login.png)

<p>После успешной авторизации становятся доступными остальные разделы.</p>
<p>В разделе "Вакансии" можно посмотреть все вакансии:</p>

![alt text](https://github.com/svoh86/job4j_dreamjob/blob/master/src/main/java/dreamjob/image/posts.png)

<p>В разделе "Кандидаты" можно посмотреть всех кандидатов:</p>

![alt text](https://github.com/svoh86/job4j_dreamjob/blob/master/src/main/java/dreamjob/image/candidates.png)

<p>В разделе "Добавить вакансию" добавляются вакансии. Город выбирается из выпадающего списка:</p>

![alt text](https://github.com/svoh86/job4j_dreamjob/blob/master/src/main/java/dreamjob/image/addPost.png)

<p>В разделе "Добавить кандидата" добавляются кандидаты. Есть возможно добавить фото:</p>

![alt text](https://github.com/svoh86/job4j_dreamjob/blob/master/src/main/java/dreamjob/image/addCandidate.png)

<p>В разделе "Мои публикации" отображены все публикации пользователя. Здесь их можно отредактировать или удалить:</p>

![alt text](https://github.com/svoh86/job4j_dreamjob/blob/master/src/main/java/dreamjob/image/myPosts.png)

<p>Для выходы используется кнопка "Выйти":</p>

![alt text](https://github.com/svoh86/job4j_dreamjob/blob/master/src/main/java/dreamjob/image/logout.png)

## Контакты

Свистунов Михаил Сергеевич

[![Telegram](https://img.shields.io/badge/Telegram-blue?logo=telegram)](https://t.me/svoh86)

Email: sms-86@mail.ru
