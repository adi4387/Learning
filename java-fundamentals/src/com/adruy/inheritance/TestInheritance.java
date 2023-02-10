package com.adruy.inheritance;

public class TestInheritance {

    public static void main(String[] args) {
        Animal myAnimal = new Cat();
        Animal.testClassMethod();
        myAnimal.testInstanceMethod();
        myAnimal.finalMethod("animal");
    }
}

class Animal {
    public static void testClassMethod() {
        System.out.println("The static method in Animal");
    }
    public void testInstanceMethod() {
        System.out.println("The instance method in Animal");
    }
    private final void finalMethod() { System.out.println("The final method in Animal"); }
    protected final void finalMethod(String name) { System.out.println("The final method in " + name); }
}

class Cat extends Animal {

    public static void testClassMethod() {
        System.out.println("The static method in Cat");
    }
    public void testInstanceMethod() {
        System.out.println("The instance method in Cat");
    }
    public final void finalMethod() { super.finalMethod("cat"); }
}


