package dev.renato.waiter.controller;

import dev.renato.waiter.dao.OrderDAO;
import dev.renato.waiter.entity.Item;
import dev.renato.waiter.entity.Order;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderController {

    private final OrderDAO orderDAO;
    private Order currentOrder;

    @Autowired
    public OrderController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @GetMapping("/orders")
    public String getHomeView(Model model){
        List<Order> orders = orderDAO.findAll();
        model.addAttribute("orders", orders);
        return "home";
    }

    @GetMapping("/add")
    public String addOrder(){
        currentOrder = orderDAO.save(new Order());
        return "redirect:/update?orderId="+currentOrder.getId();
    }

    @GetMapping("/clear")
    public String clearOrders(){
        List<Order> orders = orderDAO.findAll();
        for(var order : orders){
            orderDAO.deleteById(order.getId());
        }
        return "redirect:/orders";
    }


    @GetMapping("/delete")
    public String deleteOrder(@RequestParam("orderId") int orderId){
        orderDAO.deleteById(orderId);
        return "redirect:/orders";
    }

    @GetMapping("/update")
    public String updateOrder(@RequestParam("orderId") int orderId, Model model){
        currentOrder = orderDAO.findByIdJoinFetch(orderId);
        model.addAttribute("order", currentOrder);
        model.addAttribute("simples", Item.ItemFactory(1, "Simples"));
        model.addAttribute("especial", Item.ItemFactory(1, "Especial"));
        return "order";
    }

    @PostMapping(value = "/update", params = {"addSimples"})
    public String addSimples(@RequestParam("orderId") int orderId, @ModelAttribute("simples") Item item){
        currentOrder = orderDAO.findByIdJoinFetch(orderId);
        currentOrder.addItem(item);
        orderDAO.save(currentOrder);
        return "redirect:/update?orderId="+currentOrder.getId();
    }

    @PostMapping(value = "/update", params = {"addEspecial"})
    public String addEspecial(@RequestParam("orderId") int orderId, @ModelAttribute("especial") Item item){
        currentOrder = orderDAO.findByIdJoinFetch(orderId);
        currentOrder.addItem(item);
        orderDAO.save(currentOrder);
        return "redirect:/update?orderId="+currentOrder.getId();
    }

    @GetMapping(value="/overview")
    public String resumeOrder(@RequestParam("orderId") int orderId, Model model){
        currentOrder = orderDAO.findByIdJoinFetch(orderId);
        model.addAttribute("order", currentOrder);
        return "resume";
    }
    @PostMapping(value="/overview", params={"removeItem"})
    public String removeItem(@RequestParam("orderId") int orderId, final HttpServletRequest req) {
        int itemIndex = Integer.parseInt(req.getParameter("removeItem"));
        currentOrder = orderDAO.findByIdJoinFetch(orderId);
        currentOrder.getItemsOrdered().remove(itemIndex);
        orderDAO.save(currentOrder);
        return "redirect:/overview?orderId="+currentOrder.getId();
    }
}
