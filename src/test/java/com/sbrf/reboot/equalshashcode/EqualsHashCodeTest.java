package com.sbrf.reboot.equalshashcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class EqualsHashCodeTest {

    class Car {
        String model;
        String color;
        Calendar releaseDate;
        int maxSpeed;

        @Override
        public boolean equals(Object o) {

            //Рефлексивность: объект должен равняться самому себе
            if (o == this)
                return true;

            // Сравнение null: Проверяем, определена ли ссылка
            if (o == null)
                return false;

            // Симметричность: Два любых объекта должны быть равны независимо от того,
            // в каком порядке они будут сравниваться


            // Транзитивность: две пары из трех объектов равны, то в таком случае должны быть равны все три

            // Согласованность: повторный вызов проверки на равенство двух объектов должен выдавать тот же результат,
            // при условии, что объекты не изменялись между вызовами

            // Проверяем соответствие классов
            if (o.getClass() != this.getClass())
                return false;

            // Преобразовываем тип к требуемому объекту
            Car car = (Car) o;

            // Выполняем сравнение всех значимых полей
            return car.model.equals(this.model) && car.color.equals(this.color) &&
                    car.releaseDate.equals(this.releaseDate) && car.maxSpeed == this.maxSpeed;
        }

        @Override
        public int hashCode() {

            // Внутренняя согласованность: значение хеша не меняется при повторном вызове,
            // при условии, что объект не изменялись между вызовами

            // Внешняя согласованность: равные объекты должны иметь одинаковые хеши

            // Коллизии: разные объекты могут иметь один и тот же хеш-код

            // Для уменьшения вероятности возникновения коллизий хеш-коды свойств умножаются на простые числа

            return 29 * model.hashCode() +
                    31 * color.hashCode() +
                    37 * releaseDate.hashCode() +
                    41 * new Integer(maxSpeed).hashCode();
        }
    }

    @Test
    public void assertTrueEquals() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Mercedes";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, 0, 25);
        car2.maxSpeed = 10;


        Assertions.assertTrue(car1.equals(car2));
    }

    @Test
    public void assertFalseEquals() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Audi";
        car2.color = "white";
        car2.releaseDate = new GregorianCalendar(2017, 0, 25);
        car2.maxSpeed = 10;

        Assertions.assertFalse(car1.equals(car2));
    }

    @Test
    public void assertFalseCompareWithNull() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = null;

        Assertions.assertFalse(car1.equals(car2));
    }

    @Test
    public void assertFalseCompareWithAnotherClass() {

        class AnotherCar {
            String model;
            String color;
            Calendar releaseDate;
            int maxSpeed;
        }

        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        AnotherCar car2 = new AnotherCar();
        car2.model = "Mercedes";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, 0, 25);
        car2.maxSpeed = 10;

        Assertions.assertFalse(car1.equals(car2));
    }

    @Test
    public void assertTrueTestSymmetry() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Audi";
        car2.color = "white";
        car2.releaseDate = new GregorianCalendar(2017, 0, 25);
        car2.maxSpeed = 10;

        Assertions.assertTrue(car1.equals(car2) == car2.equals(car1));
    }

    @Test
    public void assertTrueCheckTransitivity() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Mercedes";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, 0, 25);
        car2.maxSpeed = 10;

        Car car3 = new Car();
        car3.model = "Mercedes";
        car3.color = "black";
        car3.releaseDate = new GregorianCalendar(2020, 0, 25);
        car3.maxSpeed = 10;

        Assertions.assertTrue(car1.equals(car2) && car2.equals(car3) && car1.equals(car3));
    }


    @Test
    public void assertTrueTestConsistency() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Mercedes";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, 0, 25);
        car2.maxSpeed = 10;

        Assertions.assertTrue(car1.equals(car2));
        Assertions.assertTrue(car1.equals(car2));
    }

    @Test
    public void assertFalseTestConsistency() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Mercedes";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, 0, 25);
        car2.maxSpeed = 10;

        Assertions.assertTrue(car1.equals(car2));

        car2.maxSpeed = 100;

        Assertions.assertFalse(car1.equals(car2));
    }


    @Test
    public void successEqualsHashCode() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Mercedes";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, 0, 25);
        car2.maxSpeed = 10;

        Assertions.assertEquals(car1.hashCode(), car2.hashCode());

    }

    @Test
    public void failEqualsHashCode() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Audi";
        car2.color = "white";
        car2.releaseDate = new GregorianCalendar(2017, 0, 25);
        car2.maxSpeed = 10;

        Assertions.assertNotEquals(car1.hashCode(), car2.hashCode());

    }

    @Test
    public void failTestInternalConsistency() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        int car1HashCode = car1.hashCode();

        car1.maxSpeed = 100;

        Assertions.assertNotEquals(car1HashCode, car1.hashCode());

    }

    @Test
    public void successTestInternalConsistency() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        int car1HashCode = car1.hashCode();

        Assertions.assertEquals(car1HashCode, car1.hashCode());

    }

}
