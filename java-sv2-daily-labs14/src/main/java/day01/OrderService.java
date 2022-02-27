package day01;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class OrderService {
    private List<Order> orders = new ArrayList<>();

    public List<Order> findOrdersByStatus(String status){
        return orders.stream()
                .filter(o->o.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    public void saveOrder(Order order) {
        orders.add(order);
    }

    public long countOrdersByStatus(String status){
        return orders.stream()
                .filter(o->o.getStatus().equals(status)).count();
    }

    public List<Order> getOrdersBetweenDates(LocalDate start, LocalDate end){
        return orders.stream()
                .filter(o->o.getOrderDate().isAfter(start) && o.getOrderDate().isBefore(end))
                .collect(Collectors.toList());
    }

//    public List<Order> getOrdersBetweenDates(LocalDate start, LocalDate end) {
//        return orders.stream()
//                .filter(o -> o.getOrderDate().isAfter(start) )
//                .filter(o -> o.getOrderDate().isBefore(end))
//                .collect(Collectors.toList());
//    }

//    public boolean isOrderWithLessProductThan(int number){
//        return !orders.stream()
//                .filter(o->o.getProducts().size()<number)
//                .collect(Collectors.toList()).isEmpty();
//    }
//    public boolean isOrderWithLessProductThan(int number){
//        return orders.stream()
//                .anyMatch(o->o.getProducts().size()<number);
//        //anymatch igaz hamis visszatérési érték
//    }
    public boolean isOrderWithLessProductThan(int number){
        return orders.stream()
                .mapToInt(o->o.getProducts().size())
                .anyMatch(i->i<number);
    }

    public Order getOrderWithMaxNumberOfProducts(){
        return orders.stream()
                .max(Comparator.comparingInt(o -> o.getProducts().size())).orElseThrow(()->new IllegalStateException("List is empty"));
    }
    public Order getOrderWithMaxNumberOfProducts2(){
        return orders.stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(o->o.getProducts().size())))
                .findFirst().orElseThrow(()->new IllegalStateException("Empty list"));
    }

    public List<Order> getOrdersWithProductInCategory(String category){
        return orders.stream()
                .filter(o->o.getProducts()
                        .stream().anyMatch(p->p.getCategory().equals(category)))
                .collect(Collectors.toList());
    }

    public List<Product> findProductsOverPrice(int amount){
        return orders.stream()
                .flatMap(o->o.getProducts().stream())
                .filter(p->p.getPrice()>amount)
                .distinct() //ismétlődések kiszűrése
                .collect(Collectors.toList());
    }

    public List<Order> sortOrdersByStatusAndOrderDate(){
        return orders.stream()
                .sorted(Comparator.comparing(Order::getStatus).thenComparing(Order::getOrderDate))
                .collect(Collectors.toList());
    }

    //.reversed() akkor megfordítja a sorrendet
}
