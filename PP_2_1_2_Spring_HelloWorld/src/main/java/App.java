import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class  App {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        HelloWorld bean =
                (HelloWorld) applicationContext.getBean("helloworld");
        System.out.println(bean.getMessage());
        HelloWorld bean2 = applicationContext.getBean("helloworld", HelloWorld.class);

        Cat catBean = applicationContext.getBean("cat", Cat.class);
        Cat catBean2 = applicationContext.getBean("cat", Cat.class);

        System.out.println("Do HelloWorld beans refer to the same object?" + (bean == bean2));
        System.out.println("Do Cat beans refer to the same object?" + (catBean == catBean2));

        ((AnnotationConfigApplicationContext) applicationContext).close();
    }
}