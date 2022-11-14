# Приложение "Работа мечты"
[![Java CI with Maven](https://github.com/svoh86/job4j_dreamjob/actions/workflows/maven.yml/badge.svg)](https://github.com/svoh86/job4j_dreamjob/actions/workflows/maven.yml)

+ [О проекте](#О-проекте)
+ [Технологии](#Технологии)
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
+ **Spring Boot**
+ **HTML**, **BOOTSTRAP**, **Thymeleaf**
+ **JDBC**, **PostgreSQL 14**
+ **Тестирование:** **Mockito**, **Liquibase**, **H2**, **AssertJ**
+ **Java 17**
+ **Checkstyle**

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
