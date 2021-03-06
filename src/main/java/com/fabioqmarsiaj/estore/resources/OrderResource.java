package com.fabioqmarsiaj.estore.resources;

import com.fabioqmarsiaj.estore.domain.Order;
import com.fabioqmarsiaj.estore.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/orders")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Order> find(@PathVariable Integer id){
        return ResponseEntity.ok().body(orderService.find(id));
    }
}
