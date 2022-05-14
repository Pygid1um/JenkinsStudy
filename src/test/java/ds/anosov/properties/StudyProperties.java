package ds.anosov.properties;

import org.junit.jupiter.api.Test;

public class StudyProperties {
    @Test
    void someTest1() {
        String browser = System.getProperty("browser"); // получаем значение переменной "browser"
        System.out.println(browser); // null так как переменная не задана через setProperty
    }

    @Test
    void someTest2() {
        System.setProperty("browser", "kurwa3000"); //задаем значение переменной "browser"
        String browser = System.getProperty("browser"); // получаем значение переменной "browser"
        System.out.println(browser);
    }

    @Test
    void someTest3() {
        System.setProperty("browser", "Kurwa3001");
        String browser = System.getProperty("browser", "Opera"); //второе значение дефолтное
        System.out.println(browser);
    }

    //можно задавать значение сразу через дефолное, не инициализируя раньше через setProperty
    @Test
    void someTest4() {
        String browser = System.getProperty("browser", "Chrome");
        String browserSize = System.getProperty("browserSize", "1920x1080");

        System.out.println(browser);
        System.out.println(browserSize);
    }

}
