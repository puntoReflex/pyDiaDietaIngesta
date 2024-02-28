package vEDA.v004;

import java.util.Scanner;

class Intake {

    private String name;
    private FoodNode first;

    public Intake(String name) {
        this.name = name;
        this.first = null;
        this.manage();
    }

    private void addFood(Food food) {
        FoodNode newFoodNode = new FoodNode(food);
        if (first == null) {
            first = newFoodNode;
        } else {
            FoodNode current = first;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newFoodNode);
        }
    }

    private void createFood() {
        boolean creating = true;
        Scanner userInput = new Scanner(System.in);
        while (creating) {
            System.out.println("Nombre del alimento por crear (-1 para terminar)");
            String foodName = userInput.nextLine();
            if (foodName.equals("-1")) {
                creating = !creating;
            } else {
                Food food = new Food(foodName);
                addFood(food);
            }
        }
    }

    private void editFood() {
        boolean editing = true;
        Scanner userInput = new Scanner(System.in);
        while (editing) {
            System.out.println("Nombre del alimento por editar (-1 para terminar)");
            String foodName = userInput.nextLine();
            if (foodName.equals("-1")) {
                editing = !editing;
            } else {
                editFood(foodName);
            }
        }
    }

    private void deleteFood() {
        Scanner userInput = new Scanner(System.in);
        boolean deleting = true;
        while (deleting) {
            System.out.println("Nombre del alimento por eliminar (-1 para terminar)");
            String foodName = userInput.nextLine();
            if (foodName.equals("-1")) {
                deleting = !deleting;
            } else {
                removeFood(foodName);
            }
        }
    }

    private void removeFood(String foodName) {
        if (first.getFood().getName().equals(foodName)) {
            first = first.getNext();
            return;
        } else {
            FoodNode current = first;
            while (current.getNext() != null && !current.getNext().getFood().getName().equals(foodName)) {
                current = current.getNext();
            }
            if (current.getNext() != null) {
                current.setNext(current.getNext().getNext());
            }
        }
    }

    private void editFood(String foodName) {
        Scanner userInput = new Scanner(System.in);
        FoodNode current = first;
        while (current != null) {
            if (current.getFood().getName().equals(foodName)) {
                System.out.println("Nuevo nombre de [" + foodName + "]");
                String newFoodName = userInput.nextLine();
                current.getFood().setName(newFoodName);
                return;
            }
            current = current.getNext();
        }
        System.out.println("Alimento no encontrado");
    }

    private void deleteAllFoods() {
        first = null;
    }

    public void manage() {
        boolean managing = true;
        Scanner userInput = new Scanner(System.in);
        while (managing) {
            System.out.println("Gestionando [" + this.name.toUpperCase() + "]");
            System.out.println("[C]reate / [R]ead / [U]pdate / [D]elete / e[X]it");
            switch (userInput.nextLine().charAt(0)) {
                case 'C':
                case 'c':
                    createFood();
                    break;
                case 'R':
                case 'r':
                    System.out.println(toString());
                    break;
                case 'U':
                case 'u':
                    editFood();
                    break;
                case 'D':
                case 'd':
                    deleteFood();
                    break;
                case 'X':
                case 'x':
                    managing = !managing;
                    break;
                default:
                    System.out.println("El carácter ingresado no es CRUD.");
                    break;
            }
        }
    }

    @Override
    public String toString() {
        String foodListing = "";
        FoodNode current = first;
        while (current != null) {
            foodListing = foodListing + current.getFood().toString() + "\n";
            current = current.getNext();
        }
        return this.name + "\n" + foodListing;
    }

    public static void main(String[] args) {

        Intake breakfast = new Intake("breakfast");
        breakfast.manage();
        System.out.println(breakfast.toString());

    }

    public String getName() {
        return this.name;
    }
}
