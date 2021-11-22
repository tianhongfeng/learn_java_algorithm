package enums;

import java.util.*;
import java.util.stream.Collectors;

public class Pizza {

//    private static EnumSet<PizzaStatus> undeliveredPizzaStatuses = EnumSet.allOf(PizzaStatus.class);

    private static EnumSet<PizzaStatus> undeliveredPizzaStatuses =
            EnumSet.of(PizzaStatus.ORDERED, PizzaStatus.READY);

    private PizzaStatus status;

    public PizzaStatus getStatus() {
        return status;
    }

    public void setStatus(PizzaStatus status) {
        this.status = status;
    }

    public enum PizzaStatus {
        ORDERED (5){
            @Override
            public boolean isOrdered() {
                return true;
            }
        },
        READY (2){
            @Override
            public boolean isReady() {
                return true;
            }
        },
        DELIVERED (0){
            @Override
            public boolean isDelivered() {
                return true;
            }
        };

        private int timeToDelivery;

        public boolean isOrdered() {return false;}

        public boolean isReady() {return false;}

        public boolean isDelivered(){return false;}

        public int getTimeToDelivery() {
            return timeToDelivery;
        }

        PizzaStatus (int timeToDelivery) {
            this.timeToDelivery = timeToDelivery;
        }
    }

    public boolean isDeliverable() {
        return this.status.isReady();
    }

    public void printTimeToDeliver() {
        System.out.println("Time to delivery is " +
                this.getStatus().getTimeToDelivery());
    }

    public static List<Pizza> getAllUndeliveredPizzas(List<Pizza> input) {
        return input.stream().filter(
                (s) -> undeliveredPizzaStatuses.contains(s.getStatus()))
                .collect(Collectors.toList());
    }

    public static EnumMap<PizzaStatus, List<Pizza>> groupPizzaByStatus(List<Pizza> pizzaList){
        EnumMap<PizzaStatus, List<Pizza>> pzByStatus = new EnumMap<PizzaStatus, List<Pizza>>(PizzaStatus.class);
        Iterator<Pizza> iterator = pizzaList.iterator();
        while (iterator.hasNext()) {
            Pizza pz = iterator.next();
            PizzaStatus status = pz.getStatus();
            if (pzByStatus.containsKey(status)) {
                pzByStatus.get(status).add(pz);
            } else {
                List<Pizza> newPzList = new ArrayList<>();
                newPzList.add(pz);
                pzByStatus.put(status, newPzList);
            }
        }
        return pzByStatus;
    }


    // Methods that set and get the status variable.


    public static void main(String[] args){
//        Pizza testPz = new Pizza();
//
//        testPz.setStatus(PizzaStatus.DELIVERED);
//        System.out.println(testPz.isDeliverable());

        List<Pizza> pzList = new ArrayList<>();
        Pizza pz1 = new Pizza();
        pz1.setStatus(Pizza.PizzaStatus.DELIVERED);

        Pizza pz2 = new Pizza();
        pz2.setStatus(Pizza.PizzaStatus.ORDERED);

        Pizza pz3 = new Pizza();
        pz3.setStatus(Pizza.PizzaStatus.ORDERED);

        Pizza pz4 = new Pizza();
        pz4.setStatus(Pizza.PizzaStatus.READY);

/*        pzList.add(pz1);
        pzList.add(pz2);
        pzList.add(pz3);
        pzList.add(pz4);*/

//        List<Pizza> undeliveredPzs = Pizza.getAllUndeliveredPizzas(pzList);

/*
        EnumMap<Pizza.PizzaStatus,List<Pizza>> map = Pizza.groupPizzaByStatus(pzList);
        System.out.println(map.get(Pizza.PizzaStatus.DELIVERED).size() == 1);
        System.out.println(map.get(Pizza.PizzaStatus.ORDERED).size() == 2);
        System.out.println(map.get(Pizza.PizzaStatus.READY).size() == 1);
*/

    }

}

