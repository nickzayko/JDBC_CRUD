package com.andersen;

import com.andersen.dao.DataBase;

/**
 *
 */
public class App {
    public static void main(String[] args) {
//        надо расскомментировать нужные методы!!!!

//        DataBase.createSchema();
//        DataBase.createTable();

        People people = new People();
//        people.setName("one");
//        people.setSurName("oneSur");
//        people.setAge(20);
//        people.setPhone("11111 111");
//
//        DataBase.savePeople(people);
//
//        DataBase.savePeople(people);

//        строка для поиска или удаления
        String name = "e";

//        DataBase.findPeopleByAnyInformation(name);

//        DataBase.deletePeopleByName(name);

//      строка для ввода обновления номера теефона
        String newPhone = "0000";

//        DataBase.updatePeopleInformation(name, newPhone);


    }
}
