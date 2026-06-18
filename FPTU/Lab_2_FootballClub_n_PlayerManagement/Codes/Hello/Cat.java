package Hello;

public class Cat extends Animal implements IAnimal {

    @Override
    public void eating() {
        System.out.println("An shit");
    }

    @Override
    public void drinking() {
        System.out.println("Uong Sting");
    }
}