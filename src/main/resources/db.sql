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
INSERT INTO answer (content, id_question, correct) VALUES ('комплекс средств, обеспечивающих ус-пешное проведение исследования', 19, 1);
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
INSERT INTO answer (content, id_question, correct) VALUES ('способностью оставаться в области устой-чивости', 23, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('сохранение свойств во времени', 23, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('изменение деятельности в ответ на воздей-ствие', 23, 0);
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
INSERT INTO answer (content, id_question, correct) VALUES ('совокупность структурных и параметрических данных, отражающих наиболее существенные технические реше-ния и особенности образца (комплекса, системы), состав и способ объединения его функционально связанных элементов между собой', 26, 1);
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
INSERT INTO answer (content, id_question, correct) VALUES ('люди и организации, с которыми взаимо-действует рассматриваемая система', 29, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('природные и климатические условия, в ко-торых функционирует система', 29, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('нестационарность', 29, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (30, 'Факторный анализ системы (объекта) – это...', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('описание функционирования отдельных звеньев системы', 30, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('анализ воздействия на работу системы внешних и внутренних факторов', 30, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('анализ работы исполнительных звеньев системы', 30, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('способ выражения предпочтения субъективными вероятностями', 30, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (31, 'Условия неопределенности характеризуются...', 1, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('возможностью получения отрицательных результатов', 31, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('неизбежностью получения отрицательных результатов', 31, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('исключением возможности получения от-рицательных результатов', 31, 0);
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
INSERT INTO answer (content, id_question, correct) VALUES ('', 35, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 35, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 35, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 35, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (36, 'Как называется математическое понятие для обозначения подмножества прямого декартова произведения множеств?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 36, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 36, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 36, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 36, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (37, 'Как называется способ выражения предпочтения путем представления элементов в виде последовательности в соответствии с возрастанием или убыванием их предпочтительности?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 37, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 37, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 37, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 37, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (38, 'Как называется способ выражения предпочтений, когда ЛПР предъявляется исходное множество элементов, которые оно должно разделить на некоторое количество классов?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 38, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 38, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 38, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 38, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (39, 'Как называется задача выбора решения в ситуации, когда имеется возможность накопления информации о принятых решениях?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 39, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 39, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 39, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 39, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (40, 'Что определяет функция принадлежности?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 40, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 40, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 40, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 40, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (41, 'Как называется форма упорядочения элементов множества, то есть устранение неопределенности в выборе некоторого элемента (подмножества)?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 41, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 41, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 41, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 41, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (42, 'Как называется отношение, связывающее между собой три объекта?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 42, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 42, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 42, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 42, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (43, 'Что означают бинарные отношения?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 43, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 43, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 43, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 43, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (44, 'В каком виде вводится функция принадлежности в аппарате нечетких множеств?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 44, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 44, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 44, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 44, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (45, 'Как называется отношение, связывающее между собой n объектов?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 45, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 45, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 45, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 45, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (46, 'Что представляет собой диаграмма Венна?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 46, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 46, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 46, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 46, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (47, 'В чем заключается сущность метода морфологического анализа?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 47, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 47, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 47, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 47, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (48, 'Как называется способ, при котором ЛПР просит указать степень влияния изменения значения частного показателя эффективности на результат операции?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 48, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 48, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 48, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 48, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (49, 'Как называется среднее арифметическое номеров элементов в ранжированном ряду, являющихся одинаковыми по предпочтительности?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 49, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 49, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 49, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 49, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (50, 'В каких пределах изменяется энтропийный коэффициент согласия?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 50, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 50, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 50, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 50, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (51, 'На какие два изначальных класса подразделяются методы прогнозирования?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 51, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 51, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 51, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 51, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (52, 'Что такое участок упреждения в прогнозировании?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 52, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 52, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 52, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 52, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (53, 'Что представляет собой участок ретроспекции в прогнозировании?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 53, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 53, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 53, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 53, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (54, 'Как  называется компонента, входящая в комплексный критерий предпочтения, характеризующая специфику прогнозного исследования как формирования вероятностных оценок возможности появления определенных элементов системы к фиксированному моменту времени в будущем?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 54, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 54, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 54, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 54, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (55, 'Как  называется метод, сущность которого заключается в проведении экспертами интуитивно-логического анализа проблемы с количественной оценкой суждений и формальной обработкой результатов?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 55, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 55, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 55, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 55, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (56, 'Что понимается под комплексом логических и математических процедур, направленных на получение от специалистов-экспертов информации, ее анализ и обобщение с целью выбора рациональных решений?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 56, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 56, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 56, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 56, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (57, 'Как  называется метод, предполагающий, что эксперт-прогнозист выполняет самостоятельно аналитическую работу с оценкой состояния и путей развития, излагая свои соображения письменно, при этом для выявления важности проблем и решений используют метод предпочтения, метод рангов?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 57, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 57, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 57, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 57, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (58, 'Как  называется метод, представляющий собой свободный, неструктурированный процесс генерирования всевозможных идей по поставленной проблеме, спонтанно предлагаемых участниками?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 58, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 58, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 58, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 58, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (59, 'Назовите метод экспертного оценивания, когда опрос экспертов осуществляется анонимно и проводится в несколько туров.', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 59, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 59, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 59, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 59, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (60, 'К какой группе методов относятся методы: интервью, аналитических докладных записок, сценария?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 60, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 60, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 60, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 60, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (61, 'К какой группе методов относятся методы: анкетирования, комиссий, коллективной генерации идей, комплексные?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 61, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 61, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 61, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 61, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (62, 'Назовите метод экспертного оценивания, предполагающий свободное обсуждение проблемы между экспертами.', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 62, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 62, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 62, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 62, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (63, 'Как  называется метод, применяемый при достаточно большом составе группы (около 20 человек), когда вопрос касается всей ситуации (процесса), которой можно дать количественную оценку на основе интуиции или здравого смысла, и когда требуется групповое обсуждение или взаимодействие?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 63, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 63, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 63, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 63, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (64, 'Как  называется групповой метод, при котором проводится индивидуальный анонимный опрос группы экспертов с помощью специальных анкет относительно их предположений о будущих событиях в различных областях, где ожидаются новые открытия или усовершенствования?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 64, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 64, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 64, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 64, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (65, 'Назовите метод экспертного оценивания, основанный на выдвижении большого числа идей и их последующего анализа.', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 65, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 65, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 65, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 65, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (66, 'Как называются измерения, производные от первичных?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 66, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 66, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 66, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 66, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (67, 'Качественные методы исследования систем – это...', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 67, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 67, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 67, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 67, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (68, 'Надежность системы, состоящей из трех последовательных элементов, определяется операцией.', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 68, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 68, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 68, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 68, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (69, 'Проблема “черного ящика” состоит в определении:', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 69, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 69, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 69, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 69, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (70, 'Сколько решений имеет, как правило, проблема “черного ящика”?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 70, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 70, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 70, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 70, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (71, 'Какая задача формулируется в следующем виде: “Найти распределение элементов системы по порядку следования”?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 71, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 71, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 71, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 71, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (72, 'Проблема синтеза состоит в определении:', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 72, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 72, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 72, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 72, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (73, 'Какая задача формулируется в следующем виде: «Найти распределение ресурсов и очередность работ для выполнения проекта за минимальное время»?', 2, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 73, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 73, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 73, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 73, 0);
	
INSERT INTO module (id, title, _date) VALUES (3, 'Методы анализа данных при исследовании сложных технических систем', 1111111111111);
INSERT INTO question (id, content, id_module, difficult) VALUES (74, 'Существенный вклад в развитие математической теории систем внесли...', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 74, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 74, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 74, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 74, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (75, 'Выборка – это...', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 75, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 75, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 75, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 75, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (76, 'Уровень значимости – это...', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 76, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 76, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 76, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 76, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (77, 'Статистическая сводка – это...', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 77, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 77, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 77, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 77, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (78, 'Статистическая группировка – это...', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 78, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 78, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 78, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 78, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (79, 'Статистическая гипотеза – это...', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 79, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 79, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 79, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 79, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (80, 'В ходе исследования обычно формируют ... гипотезы', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 80, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 80, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 80, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 80, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (81, 'График какой функции используется в графическом методе моделирования случайных величин?', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 81, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 81, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 81, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 81, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (82, 'Статические модели – это...', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 82, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 82, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 82, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 82, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (82, 'Укажите закон распределения, у которого математическое ожидание равно дисперсии', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 82, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 82, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 82, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 82, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (83, 'Средняя статистическая величина (статистическая средняя, средняя) – это...', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 83, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 83, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 83, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 83, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (84, 'Структурными средними исследуемо-го признака (процесса) являются...', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 84, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 84, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 84, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 84, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (85, 'При исследовании рассеивания пара-метров (характеристик) систем какая формула имеет более высокие искажения? ', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 85, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 85, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 85, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 85, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (86, 'Коэффициент доверия представляет собой', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 86, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 86, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 86, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 86, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (87, 'В каком случае в качестве меры тесно-ты связи используется коэффициент ранговой корреляции Спирмена?', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 87, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 87, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 87, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 87, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (89, 'Оценки Фишберна применяются для...', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 89, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 89, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 89, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 89, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (90, 'Метрика Евклида применяется при оценке вариантов систем...', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 90, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 90, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 90, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 90, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (91, 'Основное отличие (достоинство) интегрального показателя качества при оценке альтернативных вариантов систем', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 91, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 91, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 91, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 91, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (92, 'Сумма весов отдельных показателей равна...', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 92, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 92, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 92, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 92, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (93, 'Что представляет собой динамический ряд параметров?', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 93, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 93, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 93, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 93, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (94, 'Что представляет собой функция тренд-модели?', 3, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 94, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 94, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 94, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 94, 0);

INSERT INTO module (id, title, _date) VALUES (4, 'Принятие решений в сложных технических системах и оценка эффективности их функционирования', 1111111111111);
INSERT INTO question (id, content, id_module, difficult) VALUES (95, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 95, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 95, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 95, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 95, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (96, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 96, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 96, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 96, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 96, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (97, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 97, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 97, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 97, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 97, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (98, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 98, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 98, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 98, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 98, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (99, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 99, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 99, 1);
INSERT INTO answer (content, id_question, correct) VALUES ('', 99, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 99, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (100, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 100, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 100, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 100, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 100, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (101, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 101, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 101, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 101, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 101, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (102, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 102, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 102, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 102, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 102, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (103, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 103, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 103, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 103, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 103, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (104, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 104, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 104, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 104, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 104, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (105, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 105, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 105, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 105, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 105, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (106, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 106, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 106, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 106, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 106, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (107, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 107, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 107, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 107, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 107, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (108, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 108, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 108, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 108, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 108, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (109, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 109, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 109, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 109, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 109, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (110, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 110, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 110, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 110, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 110, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (111, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 111, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 111, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 111, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 111, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (112, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 112, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 112, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 112, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 112, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (113, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 113, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 113, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 113, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 113, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (114, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 114, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 114, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 114, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 114, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (115, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 115, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 115, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 115, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 115, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (116, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 116, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 116, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 116, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 116, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (117, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 117, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 117, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 117, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 117, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (118, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 118, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 118, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 118, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 118, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (119, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 119, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 119, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 119, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 119, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (120, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 120, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 120, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 120, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 120, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (121, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 121, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 121, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 121, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 121, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (122, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 122, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 122, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 122, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 122, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (123, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 123, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 123, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 123, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 123, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (124, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 124, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 124, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 124, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 124, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (125, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 125, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 125, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 125, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 125, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (126, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 126, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 126, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 126, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 126, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (127, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 127, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 127, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 127, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 127, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (128, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 128, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 128, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 128, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 128, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (129, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 129, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 129, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 129, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 129, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (130, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 130, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 130, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 130, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 130, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (131, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 131, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 131, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 131, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 131, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (132, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 132, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 132, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 132, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 132, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (133, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 133, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 133, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 133, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 133, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (134, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 134, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 134, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 134, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 134, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (135, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 135, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 135, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 135, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 135, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (136, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 136, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 136, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 136, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 136, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (137, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 137, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 137, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 137, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 137, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (138, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 138, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 138, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 138, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 138, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (139, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 139, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 139, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 139, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 139, 0);
INSERT INTO question (id, content, id_module, difficult) VALUES (140, '', 4, 'MEDIUM');
INSERT INTO answer (content, id_question, correct) VALUES ('', 140, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 140, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 140, 0);
INSERT INTO answer (content, id_question, correct) VALUES ('', 140, 0);
	
	


	
	
	


	
	
	
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

