package org.test;


import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import eu.vaadinonkotlin.vaadin8.jpa.JPADataProvider;
import eu.vaadinonkotlin.vaadin8.jpa.JPADataProviderKt;
import eu.vaadinonkotlin.vaadin8.jpa.JPAFilter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Entity
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("Person{id=%d, name='%s', age=%d}", id, name, age);
    }

    @NotNull
    public static ConfigurableFilterDataProvider<Person, JPAFilter, JPAFilter> createDataProvider() {
        final KClass<Person> aClass = (KClass<Person>) Reflection.getOrCreateKotlinClass(Person.class);
        final JPADataProvider<Person> dataProvider = new JPADataProvider<>(aClass);
        return JPADataProviderKt.configurableFilter(dataProvider);
    }
}
