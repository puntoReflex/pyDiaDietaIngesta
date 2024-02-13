package vEDA.v000;

class Intake {
    private FoodNode first;

    public Intake() {
        first = null;
    }

    public void addFood(Food food) {
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

    public void printFoodListing() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        String foodListing = "";
        FoodNode current = first;
        while (current != null) {
            foodListing = foodListing + current.getFood().toString() + "\n";
            current = current.getNext();
        }
        return foodListing;
    }

    public static void main(String[] args) {
        
        Intake intake = new Intake();

        Food alimento1 = new Food("Manzana");
        Food alimento2 = new Food("Banana");
        Food alimento3 = new Food("Naranja");
        Food alimento4 = new Food("Seco de chabelo");
        Food alimento5 = new Food("Ceviche");

        intake.addFood(alimento1);
        intake.addFood(alimento2);
        intake.addFood(alimento3);
        intake.addFood(alimento4);
        intake.addFood(alimento5);

        System.out.println("Alimentos en la ingesta:");
        intake.printFoodListing();
    }
}

