package com.sbrf.reboot.collections;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionsTest {


    /*
     * Задача.
     * Имеется список лучших студентов вуза.
     *
     * 1. Иванов
     * 2. Петров
     * 3. Сидоров
     *
     * В новом семестре по результатам подсчетов оценок в рейтинг на 1 место добавился новый студент - Козлов.
     * Теперь в рейтинге участвуют 4 студента.
     * (Предполагаем что в рейтинг можно попасть только получив достаточное количество балов, что бы занять 1 место).
     *
     * Вопрос.
     * Какую коллекцию из реализаций интерфейса Collection вы предпочтете для текущего хранения и использования списка студентов?
     *
     * Проинициализируйте students, добавьте в нее 4 фамилии студентов что бы тест завершился успешно.
     */
    @Test
    public void addStudentToRating() {

        List<String> students = null;

        students = new LinkedList<>(Arrays.asList("Иванов", "Петров", "Сидоров"));
        students.add(0, "Козлов");

        assertEquals(4, students.size());
    }

    /*
     * Задача.
     * Вы коллекционируете уникальные монеты.
     * У вас имеется специальный бокс с секциями, куда вы складываете монеты в хаотичном порядке.
     *
     * Вопрос.
     * Какую коллекцию из реализаций интерфейса Collection вы предпочтете использовать для хранения монет в боксе.
     *
     * Проинициализируйте moneyBox, добавьте в нее 10 монет что бы тест завершился успешно.
     */
    @Test
    public void addMoneyToBox() {

        Set<Integer> moneyBox = null;

        moneyBox = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        assertEquals(10, moneyBox.size());
    }

    /*
     * Задача.
     * Имеется книжная полка.
     * Периодически вы берете книгу для чтения, затем кладете ее на свое место.
     *
     * Вопрос.
     * Какую коллекцию из реализаций интерфейса Collection вы предпочтете использовать для хранения книг.
     *
     * Проинициализируйте bookshelf, добавьте в нее 3 книги что бы тест завершился успешно.
     */
    @Test
    public void addBookToShelf() {
        class Book {
        }

        List<Book> bookshelf = null;

        bookshelf = new ArrayList<>(Arrays.asList(new Book(), new Book(), new Book()));

        assertEquals(3, bookshelf.size());
    }

    /*
     * Задача.
     * Имеется очередь в банк. Люди должны обслуживаться в том порядке, в котором пришли.
     *
     * Вопрос.
     * Какую коллекцию из реализаций интерфейса Collection вы предпочтете использовать для организации очереди.
     */

    @Test
    public void addClientToQueue() {
        class Client {
            private final String name;

            public Client(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }
        }

        List<String> names = new ArrayList<>(Arrays.asList("Антон", "Иван", "Сергей", "Анна", "Петр"));
        int clientsServed = names.size() / 2;

        Queue<Client> clientQueue = new LinkedList<>();

        for (String name : names) {
            clientQueue.add(new Client(name));
        }

        for (int i = 0; i < clientsServed; i++) {
            assertEquals(names.get(i), clientQueue.poll().getName());
        }

        assertEquals(names.size() - clientsServed, clientQueue.size());
    }
}