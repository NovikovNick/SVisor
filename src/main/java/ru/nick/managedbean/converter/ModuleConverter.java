package ru.nick.managedbean.converter;

import javax.faces.convert.FacesConverter;

import ru.nick.model.Module;

/**
 * Конвертер модулей
 * 
 * @author NovikovNick
 *
 */
@FacesConverter(forClass = Module.class)
public class ModuleConverter extends AbstractEntityByIdConverter<Module> {

}
