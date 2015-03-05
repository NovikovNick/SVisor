## SVisor
 
###Информационная система обеспечения управления учебным процессом в структурным подразделении ВУЗа.

Основная функциональность на данный момент - обеспечение тестирования студентов. В будущем проект будет развиваться в направление организации занятий, составлении учебного плана в зависимости от специальности и нагрузки преподавателей. Подразумевается будущее внедрение рейтингования, как студентов так и преподавателей. Приложение SVisor подразумевает использование с правами администратора, преподавателя или студента.

* **Администратор**: отвечает за регистрацию новых преподавателей, дисциплин, специальностей, групп, ученых званий и степеней.
* **Преподаватель**: наполняет базу модулей(отдельные темы с вопросами), составляет тесты, подтверждает результаты.
* **Студенты**: проходят тестирование.

Для типичных сценариев использования SVisor написаны интеграционные тесты Selenium(WebDriver), которые приложение успешно проходит. Поддерживается i18n (rus|en). 
[Документация к классам](http://novikovnick.github.io/SVisor/ "JavaDoc").

