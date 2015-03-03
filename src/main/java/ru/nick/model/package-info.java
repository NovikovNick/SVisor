/**
 * Содержит POJO-объекты, который являются сущностями системы. 
 * <p>
 * Все сущности реализуют {@link Identifiable}, что подразумевает под собой возможность хранения в 
 * базе данных. Взаимоотношения сущностей представляют собой модель ВУЗа.
 * <h4>Пользователи</h4>
 * <p>Собственно те, кто и использует систему
 * <ul>
 * <li><p>
 * {@link Teacher} содержит всю информацию про преподавателя, включая -  свою ученую степень 
 * {@link AcademicDegree} и ученое звание {@link AcademicTitle}, списки дисциплин
 *  {@link Discipline} и групп {@link Group}.
 *  </p></li>
 * <li><p>
 * {@link Student} содержит информацию про студента, включая его группу {@link Group}.
 * </p></li>
 * </ul>
 * 
 * <h4>Структура университета</h4>
 * <p>Основные структурные элементы ВУЗа в котором используется система.
 * <ul>
 * <li><p>{@link Group} - студенческая группа, хранящая информацию о курсе, специальности {@link Speciality} и списке своих студентов</p></li>
 * <li><p>{@link Speciality} - специальность, на которую поступают студенты{@link Student}</p></li>
 * <li><p>{@link Discipline} - предмет, обязательный в изучении для выбранной студентом специальности. </p></li>
 * <li><p>{@link AcademicTitle} - утвержденная в университете ученая степень </p></li>
 * <li><p>{@link AcademicDegree} - утвержденная в университете ученое звание</p></li>
 * </ul>
 * 
 * <h4>Тестирование студентов</h4>
 * <p>Список классов системы, использующийся для тестирования студентов
 * <ul>
 * <li><p>{@link Test} - совокупность определенных вопросов {@link Question} из нескольких модулей {@link Module}</p></li>
 * <li><p>{@link Module} - определенная тема со своим списоком вопросов {@link Question}</p></li>
 * <li><p>{@link Question} - вопрос со списком возможных вариантов ответа {@link Answer}</p></li>
 * <li><p>{@link Answer} - ответ на определенный вопрос {@link Question}</p></li>
 * <li><p>{@link TestAssign} - назначение на тест {@link Test}, определяющее основные условия выполнения и список групп {@link Group}</p></li>
 * <li><p>{@link Result} - результат выполнения (или невыполнения) теста {@link Test}, определенным студентом{@link Student}</p></li>
 * </ul>
 */
package ru.nick.model;