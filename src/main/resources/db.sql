DROP DATABASE IF EXISTS svisor_db;
CREATE DATABASE svisor_db;
use svisor_db;
DROP TABLE IF EXISTS discipline;
CREATE TABLE discipline (
	id BIGINT NOT NULL AUTO_INCREMENT,
	title TEXT NOT NULL,
	PRIMARY KEY(id)
);
INSERT INTO discipline (title)
	VALUES 
('Теория и методы прогнозирования'),
('Математические методы системного анализа и теории принятия решений'),
('Информационные технологии обработки данных и процесс принятия решений'),
('Управление в системах диагностики'),
('Адаптивные операционные системы в прогнозировании'),
('Вероятностные методы прогнозирования сложных систем'),
('Ситуационное управление ресурсами, процессами и технологиями'),
('Структурный анализ и синтез систем'),
('Теория автоматического управления'),
('Теория принятия решений (доп. главы)'),
('Математическое моделирование'),
('Методы многокритериальной оптимизации'),
('Теория больших систем'),
('Теория автоматического управления'),
('Моделирование систем'),
('Программирование и основы алгоритмизации'),
('Прикладное программирование'),
('Математические основы теории систем'),
('Вычислительные машины, системы и сети'),
('Основы научного предвидения'),
('Исследование операций в задачах оптимизации'),
('Теория информационных систем'),
('Программное обеспечение теории моделирования и принятия решений'),
('Математические методы физики'),
('Теория и технология программирования'),
('Современные компьютерные технологии в науке'),
('Базы данных'),
('Операционные системы');

DROP TABLE IF EXISTS academicTitle;
CREATE TABLE academicTitle (
	id BIGINT NOT NULL AUTO_INCREMENT,
	fullTitle VARCHAR(50) NOT NULL,
	reducTitle VARCHAR(50) NOT NULL,
	PRIMARY KEY(id)
);
INSERT INTO academicTitle (fullTitle, reducTitle)
	VALUES 
('доцент', 'доц.'),
('профессор', 'проф.');

DROP TABLE IF EXISTS academicDegree;
CREATE TABLE academicDegree (
	id BIGINT NOT NULL AUTO_INCREMENT,
	fullDegree VARCHAR(50) NOT NULL,
	reducDegree VARCHAR(50) NOT NULL,
	PRIMARY KEY(id)
);
INSERT INTO academicDegree (fullDegree, reducDegree)
	VALUES 
('кандидат технических наук', 'к.т.н.'),
('доктор технических наук', 'д.т.н.');

DROP TABLE IF EXISTS speciality;
CREATE TABLE speciality (
	id BIGINT NOT NULL ,
	title TEXT NOT NULL,
	PRIMARY KEY(id)
);
INSERT INTO speciality (id, title)
	VALUES 
(220400, 'Системный анализ и управление'),
(220100, 'Информационные технологии в управлении');


DROP TABLE IF EXISTS teacher;
CREATE TABLE teacher (
	id BIGINT NOT NULL,
	
	fstName VARCHAR(50) NOT NULL,
	sndName VARCHAR(50),
	surname VARCHAR(50) NOT NULL,
	
	login VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
	
	INN BIGINT ,
	pensionInsurance BIGINT ,
	
	id_academicDegree BIGINT,
	id_academicTitle BIGINT,
	
	PRIMARY KEY(id),
	FOREIGN KEY (id_academicDegree) REFERENCES academicDegree(id),
	FOREIGN KEY (id_academicTitle) REFERENCES academicTitle(id)
);

INSERT INTO teacher (id, fstName, sndName, surname, login, password, id_academicDegree, id_academicTitle)
	VALUES 
(1, 'Александр', 'Александрович', 'Клавдиев', 'sansan', 'sansan', 1, 1);

DROP TABLE IF EXISTS groups;
CREATE TABLE groups (
	id BIGINT NOT NULL AUTO_INCREMENT,
	title VARCHAR(50) NOT NULL,
	course INT NOT NULL,
	id_speciality BIGINT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (id_speciality) REFERENCES speciality(id)
);
INSERT INTO groups (id, title, course, id_speciality)
	VALUES 
(1, 'САМ-11', 4, 220400),
(2, 'ИТУ-14', 1, 220100);

DROP TABLE IF EXISTS teacher_discipline;
CREATE TABLE teacher_discipline (
	id_teacher BIGINT NOT NULL,
	id_discipline BIGINT NOT NULL,
	FOREIGN KEY (id_teacher) REFERENCES teacher(id),
	FOREIGN KEY (id_discipline) REFERENCES discipline(id)
);
INSERT INTO teacher_discipline (id_teacher, id_discipline)
	VALUES 
	(1, 1),
	(1, 3),
	(1, 4),
	(1, 5),
	(1, 6);

DROP TABLE IF EXISTS teacher_groups;
CREATE TABLE teacher_groups (
	id_teacher BIGINT NOT NULL,
	id_group BIGINT NOT NULL,
	FOREIGN KEY (id_teacher) REFERENCES teacher(id),
	FOREIGN KEY (id_group) REFERENCES groups(id)
);
INSERT INTO teacher_groups (id_teacher, id_group)
	VALUES 
	(1, 1),
	(1, 2);
	
	
	
DROP TABLE IF EXISTS module;
CREATE TABLE module(
	id BIGINT NOT NULL AUTO_INCREMENT,
	title TEXT NOT NULL,
	_date DATE NOT NULL,
	PRIMARY KEY(id)
);
DROP TABLE IF EXISTS question;
CREATE TABLE question(
	id BIGINT NOT NULL AUTO_INCREMENT,
	content TEXT NOT NULL,
	id_module BIGINT NOT NULL,
	difficult ENUM('EASY', 'MEDIUM', 'HARD'),
	PRIMARY KEY(id),
	FOREIGN KEY (id_module) REFERENCES module(id)
);
DROP TABLE IF EXISTS answer;
CREATE TABLE answer(
	id BIGINT NOT NULL AUTO_INCREMENT,
	content TEXT NOT NULL,
	id_question BIGINT NOT NULL,
	correct BOOLEAN  NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (id_question) REFERENCES question(id)
);

INSERT INTO module (id, title, _date) VALUES (1, 'Сложная техническая система: ее свойства и особенности управления', 1111111111111);
INSERT INTO question (id, content, id_module, difficult) VALUES (1, 'Кем является основной субъект системы?', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('исполнительным элементом', 1, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('управляющим звеном', 1, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('топменеджером', 1, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('лицом, принимающим решения', 1, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (2, 'В чем заключается цель управления системой в операции?', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('в решении поставленной задачи', 2, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('в выработке стратегии', 2, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('в формировании управляющихвоздействий', 2, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('в максимизации эффективности', 2, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (3, 'Как называется идеальное представление в сознании руководителя желаемого результата операции?', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('стратегия', 3, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('план', 3, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('обстановка', 3, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('цель', 3, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (4, 'Что понимается под процессом смены состояний системы?', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('скачкообразный процесс', 4, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('функционирование системы', 4, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('реализация тактики', 4, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('нестационарность', 4, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (5, 'Что называют условиями обстановки?', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('воздействие внешней среды', 5, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('совокупность внутренних факторов', 5, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('совокупность внешних факторов', 5, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('совокупность существенных факторов', 5, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (6, 'Как называется способность системы изменять свою структуру, параметры, ориентацию поведения в целях повышения эффективности?', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('самоорганизация', 6, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('быстродействие', 6, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('адаптация', 6, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('мобильность', 6, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (7, 'Операция как процесс функционироЛвания системы описывается набором определенных параметров. Как называется совокупность конкретных значений этих параметров в фиксированный момент времени?', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('состояние системы', 7, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('функционирование системы', 7, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('поведение системы', 7, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('расположение системы', 7, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (8, 'Как называется способность системы без искажений воспринимать и передавать по каналам сообщений информационные потоки?', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('помехоустойчивость', 8, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('информативность', 8, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('устойчивость', 8, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('прочность', 8, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (9, 'Как называется способность системы переходить за конечное время из одного состояния в другое под влиянием управляющих воздействий?', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('устойчивость', 9, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('управляемость', 9, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('быстродействие', 9, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('эмерджентность', 9, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (10, 'Процесс смены состояний системы определяет...', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('динамику системы', 10, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('функционирование системы', 10, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('поведение системы', 10, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('быстродействие системы', 10, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (11, 'Как называется качество системы, которое определяет ее возможности решать те или иные задачи, достигать тех или иных результатов в своей деятельности (производить в соответствующие сроки определенную продукцию, осуществить определенный объем транспортных перевозок и т. д.)?', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('самоорганизация', 11, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('быстродействие', 11, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('способность', 11, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 11, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (12, 'При объединении элементов в систему последняя приобретает специфические системные свойства, не присущие ни одному из элементов. Как называются эти свойства?', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('предсказуемость', 12, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('толерантность', 12, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('синергетичность', 12, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('эмерджентные', 12, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (13, 'К каким системам относятся системы со слабопредсказуемым поведением и способностью принимать решения?', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('к простым', 13, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('к смешанным', 13, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('к сложным', 13, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('к критическим', 13, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (14, 'Система называется сингулярной, если её поведение...', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('Является устойчивым', 14, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Не имеет особенностей', 14, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Описывается монотонной функцией', 14, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Обладает неустойчивостью', 14, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (15, 'Как называется система целенаправленных действий, объединенных общим замыслом и единой целью?', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('стратегия', 15, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('операция', 15, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('тактика', 15, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('процесс', 15, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (16, 'Какая процедура применяется для оценивания факторов, описываемых переменными с неизвестными функциями принадлежности?', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('моделирование', 16, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('экспертное оценивание', 16, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('экстраполяция', 16, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('интерполяция', 16, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (17, 'Что является основной исследовательской концепцией анализа эффективности?', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('аналитический расчет', 17, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('моделирование', 17, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('стратегия', 17, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('тактика', 17, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (18, 'Как называется операция по искусственному введению случайности в ситуацию, где она отсутствует?', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('рандомизация', 18, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('информатизация', 18, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('эквивалентность', 18, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('гармонизация', 18, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (19, 'Ресурсы – это...', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('комплекс средств, обеспечивающих успешное проведение исследования', 19, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('упорядоченный набор средств', 19, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('условия', 19, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('адаптационная способность', 19, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (21, 'Гипотеза исследования системы – это...', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('выбранный метод исследования системы управления', 21, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('предположение о возможном действии системы управления', 21, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('план проведения исследований системы управления', 21, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('способ выражения предпочтения субъективными вероятностями', 21, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (22, 'Динамическим свойством системы является:', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('адаптивность', 22, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('горизонтальная обособленность', 22, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('централизация', 22, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('вертикальная целостность', 22, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (23, 'Под живучестью системы понимается:', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('приспособляемость к внешним воздействиям', 23, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('способностью оставаться в области устойчивости', 23, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('сохранение свойств во времени', 23, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('изменение деятельности в ответ на воздействие', 23, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (24, 'Система, которая предполагает разбиение на более простые подсистемы, называется...', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('высокоорганизованными системами', 24, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('сложной системой', 24, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('вышестоящими системами', 24, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('эргатическими системами', 24, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (25, 'К каким системам относятся системы со слабопредсказуемым поведением и способностью принимать решения?', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('к простым', 25, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('к смешанным', 25, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('к сложным', 25, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('к критическим', 25, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (26, 'Технический облик образца (комплекса, системы) – это...', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('совокупность физических свойств и характеристик образца (комплекса, системы)', 26, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('совокупность структурных и параметрических данных, отражающих наиболее существенные технические решения и особенности образца (комплекса, системы), состав и способ объединения его функционально связанных элементов между собой', 26, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('совокупность доминирующих свойств и характеристик образца (комплекса, системы)', 26, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('совокупность факторов, оказывающих существенное влияние на характеристики образца (комплекса, системы)', 26, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (27, 'Комплексом называется:', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('множество состояний системы', 27, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('отношение между элементами системы', 27, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('отношение между системами', 27, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('множество элементов со связями', 27, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (28, 'Как называется движущая сила какого-либо процесса (явления) или условие, которое влияет на тот или иной процесс (явление)?', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('моделирование', 28, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('управление', 28, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('условие', 28, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('фактор', 28, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (29, 'Факторы внешней среды, принимаемые во внимание при исследовании систем – это...', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('все то, что находится за пределами рассматриваемой системы', 29, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('люди и организации, с которыми взаимодействует рассматриваемая система', 29, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('природные и климатические условия, в которых функционирует система', 29, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('нестационарность', 29, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (30, 'Факторный анализ системы (объекта) – это...', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('описание функционирования отдельных звеньев системы', 30, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('анализ воздействия на работу системы внешних и внутренних факторов', 30, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('анализ работы исполнительных звеньев системы', 30, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('способ выражения предпочтения субъективными вероятностями', 30, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (31, 'Условия неопределенности характеризуются...', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('возможностью получения отрицательных результатов', 31, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('неизбежностью получения отрицательных результатов', 31, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('исключением возможности получения отрицательных результатов', 31, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('скоротечностью процессов', 31, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (32, 'Как называется свойство среднего результата операции, если он равен сумме средних частных результатов?', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('мультипликативность', 32, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('аддитивность', 32, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('интегративность', 32, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('эквивалентность', 32, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (33, 'Структурным свойством системы является:', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('Адаптивность', 33, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Оптимизация', 33, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Иерархическая упорядоченность', 33, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('Прогрессирующая системация', 33, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (34, 'Наиболее характерным свойством организационно-технических систем является:', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('Стабильность', 34, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('Иерархическая упорядоченность', 34, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Целостность', 34, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Оптимизация', 34, 0);

INSERT INTO module (id, title, _date) VALUES (2, 'Методы исследования сложных технических систем', 1111111111111);
INSERT INTO question (id, content, id_module, difficult) VALUES (35, 'Как называется результат сознательного выбора одной из множества допустимых стратегий?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('альтернатива', 35, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('обоснование', 35, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('оценивание', 35, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('решение', 35, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (36, 'Как называется математическое понятие для обозначения подмножества прямого декартова произведения множеств?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('отношение', 36, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('сравнение', 36, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('эквивалентность', 36, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('рандомизация', 36, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (37, 'Как называется способ выражения предпочтения путем представления элементов в виде последовательности в соответствии с возрастанием или убыванием их предпочтительности?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('сортировка', 37, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('попарное выражение предпочтения как доли суммарной интенсивности', 37, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('ранжирование', 37, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('априорное выражение предпочтений', 37, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (38, 'Как называется способ выражения предпочтений, когда ЛПР предъявляется исходное множество элементов, которые оно должно разделить на некоторое количество классов?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('способ выражения предпочтений лингвистическими переменными', 38, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('попарное выражение предпочтения как доли относительной интенсивности', 38, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('ранжирование', 38, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('сортировка', 38, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (39, 'Как называется задача выбора решения в ситуации, когда имеется возможность накопления информации о принятых решениях?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('задача апостериорного выявления предпочтений', 39, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('задача априорного выявления предпочтений', 39, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('интегральная задача', 39, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('информационная задача', 39, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (40, 'Что определяет функция принадлежности?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('эффективность операции', 40, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('качество системы', 40, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('диапазон изменения переменной', 40, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('закон распределения', 40, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (41, 'Как называется форма упорядочения элементов множества, то есть устранение неопределенности в выборе некоторого элемента (подмножества)?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('предпочтение', 41, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('толерантность', 41, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('симметричность', 41, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('ранжирование', 41, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (42, 'Как называется отношение, связывающее между собой три объекта?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('бинарное', 42, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('троичное', 42, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('тернарное', 42, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('единичное', 42, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (43, 'Что означают бинарные отношения?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('Множество упорядоченных пар элементов', 43, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Отношения (связи) между парами объектов', 43, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('Графическое разбиение, в котором каждая последующая фигура должна иметь одну общую область с каждой из ранее построенных фигур', 43, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Объединение элементов множеств за исключением тех, которые встречаются дважды', 43, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (44, 'В каком виде вводится функция принадлежности в аппарате нечетких множеств?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('в виде константы', 44, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('в виде лингвистической переменной', 44, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('в виде исходных данных', 44, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('в графическом виде', 44, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (45, 'Как называется отношение, связывающее между собой n объектов?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('альтернативное', 45, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('критериальное', 45, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('n-нарное', 45, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('бинарное', 45, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (46, 'Что представляет собой диаграмма Венна?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('Множество упорядоченных пар элементов, считающихся равномощными', 46, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Графическое выполнение тождественных преобразований уравнений с фиксированным количеством подмножеств', 46, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Графическое разбиение, в котором каждая последующая фигура должна иметь одну и только одну общую область с каждой из ранее построенных фигур', 46, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('Порядок следования пар элементов в соответствии с порядком следования перемножаемых множеств', 46, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (47, 'В чем заключается сущность метода морфологического анализа?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('в переборе возможных рациональных вариантов технических систем и выборе наиболее предпочтительного из них', 47, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('в детальном описании всех существующих и возможных (допустимых) технических систем исследуемого класса с последующим     поиском на этом множестве системы, наиболее полно отвечающей поставленной цели', 47, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('в исследовании технической системы с использованием математических моделей', 47, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('в исследовании технической системы с использованием экстраполяционных методов', 47, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (48, 'Как называется способ, при котором ЛПР просит указать степень влияния изменения значения частного показателя эффективности на результат операции?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('способ выражения предпочтения субъективными вероятностями', 48, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('способ выражения предпочтений коэффициентами важности', 48, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('способ попарного выражения предпочтения как доли относительной интенсивности', 48, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('способ попарного выражения предпочтения как доли суммарной', 48, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (49, 'Как называется среднее арифметическое номеров элементов в ранжированном ряду, являющихся одинаковыми по предпочтительности?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('коэффициент ранговой корреляции', 49, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('общий ранг', 49, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('стандартизированный ранг', 49, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('коэффициент Спирмена', 49, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (50, 'В каких пределах изменяется энтропийный коэффициент согласия?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('от – 1 до 1', 50, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('от 0 до + ∞.', 50, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('от – ∞ до 0', 50, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('от 0 до 1', 50, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (51, 'На какие два изначальных класса подразделяются методы прогнозирования?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('на математические и описательные', 51, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('на фактографические и экспертные', 51, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('на графические и символические', 51, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('на точечные и интервальные', 51, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (52, 'Что такое участок упреждения в прогнозировании?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('участок, на который «опирается» прогноз', 52, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('участок времени предыстории', 52, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('участок «обучения» выборки исходных данных', 52, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('участок, на который строится прогноз', 52, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (53, 'Что представляет собой участок ретроспекции в прогнозировании?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('участок времени на который строится прогноз', 53, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('участок времени, завершающий точечным прогнозом', 53, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('участок, на котором формируется прогноз', 53, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('участок времени, завершающийся интервальным прогнозом', 53, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (54, 'Как  называется компонента, входящая в комплексный критерий предпочтения, характеризующая специфику прогнозного исследования как формирования вероятностных оценок возможности появления определенных элементов системы к фиксированному моменту времени в будущем?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('компонента перспективности', 54, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('компонента применимости', 54, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('компонента риска внедрения', 54, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('компонента адаптивности', 54, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (55, 'Как  называется метод, сущность которого заключается в проведении экспертами интуитивно-логического анализа проблемы с количественной оценкой суждений и формальной обработкой результатов?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('метод экспертных оценок', 55, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('метод  экспоненциального сглаживания', 55, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('метод  морфологического анализа', 55, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('метод количественного анализа', 55, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (56, 'Что понимается под комплексом логических и математических процедур, направленных на получение от специалистов-экспертов информации, ее анализ и обобщение с целью выбора рациональных решений?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('методы принятия рационального решения', 56, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('методы обоснования решений', 56, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('методы экспертного оценивания', 56, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('методы экспресс-оценивания', 56, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (57, 'Как  называется метод, предполагающий, что эксперт-прогнозист выполняет самостоятельно аналитическую работу с оценкой состояния и путей развития, излагая свои соображения письменно, при этом для выявления важности проблем и решений используют метод предпочтения, метод рангов?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('метод экспоненциального сглаживания', 57, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('метод «мозговой атаки».', 57, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('метод интервью', 57, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('метод аналитических докладных записок', 57, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (58, 'Как  называется метод, представляющий собой свободный, неструктурированный процесс генерирования всевозможных идей по поставленной проблеме, спонтанно предлагаемых участниками?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('метод интервью', 58, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('метод «мозговой атаки»', 58, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('метод аналитических докладных записок', 58, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('метод Дельфи', 58, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (59, 'Назовите метод экспертного оценивания, когда опрос экспертов осуществляется анонимно и проводится в несколько туров.', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('метод аналитических докладных записок', 59, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('метод интервью', 59, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('метод комиссий', 59, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('метод анкетирования', 59, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (60, 'К какой группе методов относятся методы: интервью, аналитических докладных записок, сценария?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('методы группового экспертного оценивания', 60, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('методы индивидуального экспертного оценивания', 60, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('аналитические методы', 60, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('объективные методы', 60, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (61, 'К какой группе методов относятся методы: анкетирования, комиссий, коллективной генерации идей, комплексные?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('аналитические методы', 61, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('методы группового экспертного оценивания', 61, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('коллективные методы', 61, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('субъективные методы', 61, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (62, 'Назовите метод экспертного оценивания, предполагающий свободное обсуждение проблемы между экспертами.', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('метод аналитических докладных записок', 62, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('метод интервью', 62, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('метод анкетирования', 62, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('метод комиссий', 62, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (63, 'Как  называется метод, применяемый при достаточно большом составе группы (около 20 человек), когда вопрос касается всей ситуации (процесса), которой можно дать количественную оценку на основе интуиции или здравого смысла, и когда требуется групповое обсуждение или взаимодействие?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('метод «мысленного группового анализа реальной ситуации»', 63, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('метод «мозговой атаки»', 63, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('метод аналитических докладных записок', 63, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('метод Дельфи', 63, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (64, 'Как  называется групповой метод, при котором проводится индивидуальный анонимный опрос группы экспертов с помощью специальных анкет относительно их предположений о будущих событиях в различных областях, где ожидаются новые открытия или усовершенствования?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('метод «мозговой атаки»', 64, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('метод «мысленного группового анализа реальной ситуации».', 64, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('метод Дельфи', 64, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('метод аналитических докладных записок', 64, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (65, 'Назовите метод экспертного оценивания, основанный на выдвижении большого числа идей и их последующего анализа.', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('метод интервью', 65, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('аналитический метод', 65, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('метод коллективной генерации идей', 65, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('метод комиссий', 65, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (66, 'Как называются измерения, производные от первичных?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('производные', 66, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('прямые', 66, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('вторичные', 66, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('косвенные', 66, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (67, 'Качественные методы исследования систем – это...', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('экономико-математические методы, позволяющие оценить параметры системы за истекший период работы', 67, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('формально-логические обоснования оценок и прогнозов развития организации на основе собственного опыта и мнений экспертов', 67, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('мнение и обоснованное решение руководителя организации', 67, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('научно обоснованные способы и методы исследования приёмы проверки истинности знаний и заключений', 67, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (68, 'Надежность системы, состоящей из трех последовательных элементов, определяется операцией.', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('Умножение', 68, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('Сложение', 68, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Ограниченное суммирование', 68, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Максимум', 68, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (69, 'Проблема “черного ящика” состоит в определении:', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('структуры системы по ее поведению', 69, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('поведения системы по ее структуре', 69, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('условий функционирования системы по входам и выходам', 69, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('поведения и структуры системы по входам и выходам', 69, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (70, 'Сколько решений имеет, как правило, проблема “черного ящика”?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('Одно', 70, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Два', 70, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Несколько', 70, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Множество', 70, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (71, 'Какая задача формулируется в следующем виде: “Найти распределение элементов системы по порядку следования”?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('Проектирование', 71, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Планирование', 71, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Ранжирование', 71, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('Управление', 71, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (72, 'Проблема синтеза состоит в определении:', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('Структуры системы по её поведению', 72, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('Поведения системы по её структуре', 72, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Условий функционирования системы по её характеристикам', 72, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Поведения по входам и выходам', 72, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (73, 'Какая задача формулируется в следующем виде: «Найти распределение ресурсов и очередность работ для выполнения проекта за минимальное время»?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('Транспортная', 73, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Обеспечение потребностей', 73, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Синтеза', 73, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Составление расписаний', 73, 1);
	
INSERT INTO module (id, title, _date) VALUES (3, 'Методы анализа данных при исследовании сложных технических систем', 1111111111111);
INSERT INTO question (id, content, id_module, difficult) VALUES (74, 'Существенный вклад в развитие математической теории систем внесли...', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('Д. Гильберт', 74, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('К. Гаусс', 74, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Н. Винер', 74, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('Г. Минковский', 74, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (75, 'Выборка – это...', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('предположение (суждение), подтверждаемое или опровергаемое в ходе статистического исследования', 75, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('совокупность отобранных единиц в ходе наблюдения', 75, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('столь малая вероятность, что событие с такой вероятностью является практически невозможным', 75, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('несколько значений анализируемого показателя, отобранных для исследований', 75, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (76, 'Уровень значимости – это...', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('предположение (суждение), подтверждаемое или опровергаемое в ходе статистического исследования', 76, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('доверительная вероятность принятия решения', 76, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('столь малая вероятность, что событие с такой вероятностью является практически невозможным', 76, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('величина доверительного интервала', 76, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (77, 'Статистическая сводка – это...', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('научно организованная обработка материалов наблюдения, включающая в себя систематизацию, группировку данных, составление таблиц, подсчет групповых и общих итогов, расчет производных показателей (средних, относительных величин)', 77, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('совокупность отобранных единиц в ходе наблюдения', 77, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('краткий информационно-статистический  бюллетень об анализируемом явлении', 77, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('несколько значений анализируемого показателя, отобранных для исследований', 77, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (78, 'Статистическая группировка – это...', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('процесс образования новых групп на основе ранее осуществленной группировки', 78, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('упорядоченное распределение единиц изучаемой совокупности на группы по определенному варьирующему признаку', 78, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('процесс образования однородных групп на основе разбиения статистической совокупности на части или объединения изучаемых единиц в частные совокупности по существенным для них признакам', 78, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('совокупность отобранных единиц в ходе наблюдения', 78, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (79, 'Статистическая гипотеза – это...', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('предположение (суждение), подтверждаемое или опровергаемое в ходе статистического исследования', 79, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('столь малая вероятность, что событие с такой вероятностью является практически невозможным', 79, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('научно обоснованные способы и методы исследования приёмы проверки истинности знаний и заключений', 79, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('способ выражения предпочтения субъективными вероятностями', 79, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (80, 'В ходе исследования обычно формируют ... гипотезы', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('решающую и эквивалентную', 80, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('главную и второстепенную', 80, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('основную и альтернативную', 80, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('положительную и отрицательную', 80, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (81, 'График какой функции используется в графическом методе моделирования случайных величин?', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('функции распределения', 81, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('линейной функции', 81, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('гиперболы', 81, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('полигон частот', 81, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (82, 'Статические модели – это...', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('физические модели материально-технической базы организации', 82, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('модели объекта в фиксированный момент времени', 82, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('модели, характеризующие переходные процессы в организации', 82, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('совокупность физических свойств и  характеристик образца (комплекса, системы)', 82, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (82, 'Укажите закон распределения, у которого математическое ожидание равно дисперсии', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('нормальный', 82, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('экспоненциальный', 82, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('равномерный', 82, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Парето', 82, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (83, 'Средняя статистическая величина (статистическая средняя, средняя) – это...', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('обобщающий показатель, характеризующий типичный уровень явления в конкретных условиях места и времени, отражающий величину варьирующего признака в расчете на единицу качественно однородной совокупности', 83, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('значение случайной величины, встречающееся с наибольшей вероятностью в дискретном вариационном ряду', 83, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('значение случайной величины, находящееся в середине вариационного ряда', 83, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('вариант, имеющий наибольшую частоту', 83, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (84, 'Структурными средними исследуемого признака (процесса) являются...', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('средняя и средняя взвешенная', 84, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('среднее квадратическое и срединное вероятное отклонения', 84, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('асимметрия и эксцесс', 84, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('мода и медиана', 84, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (85, 'При исследовании рассеивания параметров (характеристик) систем какая формула имеет более высокие искажения? ', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('формула абсолютного отклонения', 85, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('формула дисперсии', 85, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('формула среднего квадратического отклонения', 85, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('формула показателя вариации', 85, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (86, 'Коэффициент доверия представляет собой', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('нормированное отклонение, зависящее от вероятности, с которой гарантируется предельная ошибка выборки', 86, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('показатель качества предметов или явлений, по которому можно определить их сходство или различие', 86, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('статистика критерия, по которой судят о справедливости статистической гипотезы', 86, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('столь малая вероятность, что событие с такой вероятностью является практически невозможным', 86, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (87, 'В каком случае в качестве меры тесноты связи используется коэффициент ранговой корреляции Спирмена?', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('при наличии факторов, оказывающих существенное влияние на характеристики образца (комплекса, системы)', 87, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('при нестрогом ранжировании оцениваемых элементов', 87, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('при использовании метода анкетирования', 87, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('при строгом ранжировании оцениваемых элементов', 87, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (89, 'Оценки Фишберна применяются для...', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('оценки значимости показателей (характеристик) систем', 89, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('разбиения систем и их элементов на классы.', 89, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('декомпозиции систем', 89, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('определения общего ранга системы', 89, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (90, 'Метрика Евклида применяется при оценке вариантов систем...', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('по совокупности качественных показателей', 90, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('по совокупности количественных показателей', 90, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('по совокупности зависимых показателей', 90, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('по экспертным оценкам', 90, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (91, 'Основное отличие (достоинство) интегрального показателя качества при оценке альтернативных вариантов систем', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('позволяет выполнять сравнение нескольких вариантов систем одновременно', 91, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('связывает основные технические характеристики систем (образцов) и средства, затраченные для их достижения', 91, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('позволяет формировать мнение руководителя о перспективах развития организации', 91, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('возможность реализации с помощью пакетов прикладных программ', 91, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (92, 'Сумма весов отдельных показателей равна...', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('нулю', 92, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('максимуму', 92, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('бесконечности', 92, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('единице', 92, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (93, 'Что представляет собой динамический ряд параметров?', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('модифицированный ряд Фурье', 93, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('упорядоченные во времени исходные данные', 93, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('упорядоченные по величине исходные данные', 93, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('ряд «n» первых членов динамики', 93, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (94, 'Что представляет собой функция тренд-модели?', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('кривую со скачками на уровнях динамического ряда', 94, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('функцию с разрывами на участке ретроспекции', 94, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('гладкую кривую без скачков и разрывов', 94, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('монотонно возрастающий (убывающий) ступенчатый ряд', 94, 0);

INSERT INTO module (id, title, _date) VALUES (4, 'Принятие решений в сложных технических системах и оценка эффективности их функционирования', 1111111111111);
INSERT INTO question (id, content, id_module, difficult) VALUES (95, 'Кто несет ответственность за принятие решения?', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('управляющее звено', 95, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('исполнительный элемент', 95, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('лицо, принимающее решение', 95, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('высший уровень иерархии', 95, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (96, 'Как называется математический аппарат, предназначенный для принятия оптимальных решений в условиях неопределенности?', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('теория принятия решений', 96, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('теория прогнозирования', 96, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('теория игр', 96, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('динамическое программирование', 96, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (97, 'Как называется результат сознательного выбора одной из множества допус-тимых стратегий?', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('альтернатива', 97, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('обоснование', 97, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('оценивание', 97, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('решение', 97, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (98, 'В чем заключается цель исследования эффективности?', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('в решении поставленной задачи', 98, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('в выработке рекомендаций ЛПР', 98, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('в формировании управляющих воздействий', 98, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('в выработке оптимального плана', 98, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (99, 'Что обуславливает сочетание рацио-нального способа использования ак-тивных средств и благоприятно сло-жившихся условий обстановки?', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('самоорганизацию', 99, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('высокую эффективность', 99, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('адаптационную способность', 99, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('мобильность', 99, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (100, 'Чем является выработка оценочного суждения относительно пригодности заданного способа действий или приспособленности технических средств к решению задач?', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('аналитическим расчетом', 100, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('оценкой эффективности', 100, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('разработкой стратегии', 100, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('разработкой тактики', 100, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (101, 'Выбор элементов системы определяется главным образом:', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('Предпочтением ЛПР', 101, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('Внешними системами', 101, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Целью анализа', 101, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Информационной средой задачи', 101, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (102, 'Как называется функция, показывающая степень достижения цели операции?', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('принадлежности', 102, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('соответствия', 102, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('целевая', 102, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('оперативная', 102, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (103, 'Что понимается под степенью различия между реальным и желаемым результатом операции?', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('неопределенность', 103, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('эмерджентность', 103, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('эффективность', 103, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('достоверность', 103, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (104, 'Как называется эффективность операции при идеальном способе использования активных средств?', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('реальная эффективность', 104, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('потенциальная эффективность', 104, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('оценочная эффективность', 104, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('модельная эффективность', 104, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (105, 'Как называется мера степени соответствия реального результата операции требуемому?', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('критерий эффективности', 105, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('степень эффективности', 105, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('показатель эффективности.', 105, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('потенциальная эффективность', 105, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (106, 'Как называется правило выбора рационального способа использования активных средств (стратегий) в операции?', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('альтернатива', 106, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('принятие решения', 106, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('показатель эффективности', 106, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('критерий эффективности', 106, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (107, 'Минимаксный критерий иногда называют...', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('критерием азартного игрока', 107, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('критерием осторожного наблюдателя', 107, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('критерием Сэвиджа', 107, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('критерием произведений', 107, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (108, 'Критерий Лапласа является частным случаем критерия...', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('минимаксного', 108, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('среднего выигрыша', 108, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('Сэвиджа', 108, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Ходжа-Лемана', 108, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (109, 'Сумма весовых коэффициентов в критерии Гурвица равна...', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('-1', 109, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('локальному максимуму', 109, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('бесконечности', 109, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('1', 109, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (110, 'К числу так называемых «осторожных» критериев относятся...', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('критерий минимакса и критерий Сэвиджа', 110, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('критерии Гурвица и Лапласа', 110, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('критерии Сэвиджа и Гермейера', 110, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('критерии Вальда и Ходжа-Лемана', 110, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (111, 'Что позволяет учесть норматив приведения E?', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('позволяет выполнять сравнение нескольких вариантов систем одновременно', 111, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('связывает основные технические характеристики систем (образцов) и средства', 111, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('учет влияния фактора времени на затраты', 111, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('позволяет формировать мнение руководителя о перспективах развития организации', 111, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (112, 'В классическом подходе к принятию решений наилучшая альтернатива определяется', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('Множеством последствий', 112, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Функцией выигрыша', 112, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('Целевой функцией', 112, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Функцией стоимости', 112, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (113, 'Если важность одного из критериев в 5 раз больше остальных, то следует применять:', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('Метод пороговых критериев', 113, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Метод расстояния', 113, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('Метод главного критерия', 113, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('Мультипликативную свертку', 113, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (114, 'Как называется ориентированный граф, в котором существует лишь одна вершина, не имеющая входящих дуг, и лишь одна вершина, не имеющая выходящих дуг?', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('гистограмма', 114, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('паутина', 114, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('сеть', 114, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('система', 114, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (115, 'Как называется последовательность однородных событий, следующих одно за другим в случайные моменты времени?', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('набор событий', 115, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('череда событий', 115, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('объединение событий', 115, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('поток событий', 115, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (116, 'Как называется процесс, требующий затрат времени и ресурсов?', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('фиктивная работа', 116, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('производительность', 116, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('функционирование', 116, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('действительная работа', 116, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (117, 'Как называется логическая связь между двумя или несколькими работами, указывающая на то, что начало одной работы зависит от результатов другой?', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('действие', 117, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('фиктивная работа', 117, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('функционирование', 117, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('производительность', 117, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (118, 'Как называется последовательность работ в сетевой модели, в которой конечное событие данной работы совпадает с начальным событием следующей за ней работы?', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('путь', 118, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('траектория', 118, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('дуга', 118, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('граф', 118, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (119, 'Чему равна продолжительность фиктивной работы?', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('единице', 119, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('нулю', 119, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('бесконечности', 119, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('фиксированной величине', 119, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (120, 'Как называется сумма длин последовательности дуг, составляющих данный путь?', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('длина участка', 120, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('длина пути', 120, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('длина дуги', 120, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('полный путь', 120, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (121, 'Как называется событие, которым заканчивается весь комплекс работ?', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('исходное', 121, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('окончательное', 121, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('завершающее', 121, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('финальное', 121, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (122, 'Какое из событий: 1) исходное; 2) завершающее или 3) все события, лежащие на критическом пути, располагают резервами времени?', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('только завершающее', 122, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('все', 122, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('только исходное', 122, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('ни одно не располагает', 122, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (123, 'Как называется минимальное время, за которое может быть выполнен весь комплекс работ?', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('директивный срок', 123, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('продолжительность', 123, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('критическое время', 123, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('минимальный временной порог', 123, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (124, 'Какую продолжительность имеет критический путь?', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('оптимальную', 124, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('минимальную', 124, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('номинальную', 124, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('максимальную', 124, 1);
INSERT INTO question (id, content, id_module, difficult) VALUES (125, 'Реакции систем (вариантов) в различных условиях воздействия окружающей среды удобно представлять в виде...', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('дерева альтернативных решений', 125, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('платежной матрицы', 125, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('матрицу решений', 125, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('матрицы эффективности', 125, 1);
	
DROP TABLE IF EXISTS student;
CREATE TABLE student (
	id BIGINT NOT NULL,
	
	fstName VARCHAR(50) NOT NULL,
	sndName VARCHAR(50),
	surname VARCHAR(50) NOT NULL,
	
	login VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
		
	id_group BIGINT NOT NULL,
	
	PRIMARY KEY(id),
	FOREIGN KEY (id_group) REFERENCES groups(id)
);

INSERT INTO student (id, fstName, sndName, surname, login, password, id_group)
	VALUES 
(1130030028, 'Николай', 'Сергеевич', 'Новиков', 'nick', 'nick', 1);

DROP TABLE IF EXISTS test;
CREATE TABLE test(
	id BIGINT NOT NULL AUTO_INCREMENT,
	title VARCHAR(50) NOT NULL,
	_date DATE NOT NULL,
	PRIMARY KEY(id)
);

DROP TABLE IF EXISTS test_question;
CREATE TABLE test_question (
	id_test BIGINT NOT NULL,
	id_question BIGINT NOT NULL,
	FOREIGN KEY (id_test) REFERENCES test(id),
	FOREIGN KEY (id_question) REFERENCES question(id)
);

DROP TABLE IF EXISTS testassign;
CREATE TABLE testassign (
	id BIGINT NOT NULL AUTO_INCREMENT,
	title VARCHAR(50) NOT NULL,
	description TEXT,
	date_start DATE NOT NULL,
	date_end DATE NOT NULL,
	passing_score INT NOT NULL,
	completion_time INT NOT NULL,
	attempts INT NOT NULL,
	author BIGINT NOT NULL,
	id_test BIGINT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (author) REFERENCES teacher(id),
	FOREIGN KEY (id_test) REFERENCES test(id)
);
DROP TABLE IF EXISTS testassign_groups;
CREATE TABLE testassign_groups (
	id_testassign BIGINT NOT NULL,
	id_group BIGINT NOT NULL,
	FOREIGN KEY (id_testassign) REFERENCES testassign(id),
	FOREIGN KEY (id_group) REFERENCES groups(id)
);

DROP TABLE IF EXISTS result;
CREATE TABLE result(
	id BIGINT NOT NULL AUTO_INCREMENT,
	id_student BIGINT NOT NULL,
	id_test BIGINT NOT NULL,
	attempt INT NOT NULL,
	result INT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (id_test) REFERENCES test(id),
	FOREIGN KEY (id_student) REFERENCES student(id)
);

desc result;

