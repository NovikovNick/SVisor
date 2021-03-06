/**
 * <b>Первый</b> слой приложения, обрабатвающий запросы от пользователей. 
 * 
 * <p>
 * Пакет содержит JSF Backing bean'ы - классы-контроллеры, обрабатывающие запросы с 
 * отдельных страниц с последующей передачей на уровень BO(Business Object - 
 * {@link ru.nick.bo}). Большинство классов (заканчивающиеся на <b>'...Bean'</b>) 
 * наследуют {@link AbstarctManagedBean} - абстрактную реализацию JSF-bean'а. Пакет 
 * спроектирован с учетом будущего добавления подобных классов JSF-bean'ов для обработки 
 * новых страниц с новыми сущностями.
 * 
 * <p>
 * Остальные классы, являются дополнительными инструментами
 * <ul>
 * <li><p>Аннотация {@link FormField} - указывает на то, что поле является отображением 
 * поля на форме</p></li>
 * <li><p>Bean {@link LocateChange} - смена языковой локали</p></li>
 * <li><p>Bean {@link Navigator} - обеспечивает навигацию по приложению</p></li>
 * <li><p>{@link Messages} - утилитарный класс, инкапсулирующий работу с 
 * {@link FacesMessage}. </p></li>
 * </ul>
 */
package ru.nick.managedbean;