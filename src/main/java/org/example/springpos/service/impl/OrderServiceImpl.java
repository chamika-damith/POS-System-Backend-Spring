package org.example.springpos.service.impl;

import jakarta.transaction.Transactional;
import org.example.springpos.dao.CustomerDAO;
import org.example.springpos.dao.ItemDAO;
import org.example.springpos.dao.OrderDAO;
import org.example.springpos.dto.ItemDTO;
import org.example.springpos.dto.OrderDTO;
import org.example.springpos.entity.Customer;
import org.example.springpos.entity.Item;
import org.example.springpos.entity.Order;
import org.example.springpos.entity.OrderItem;
import org.example.springpos.exception.DataPersistException;
import org.example.springpos.service.OrderService;
import org.example.springpos.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderDAO orderDAO;


    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private ItemDAO itemDAO;


    @Override
    public void save(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderId(AppUtil.generateOrderId());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setTotal(orderDTO.getTotal());
        order.setTxtCash(orderDTO.getTxtCash());
        order.setTxtDiscount(orderDTO.getTxtDiscount());


        Customer customer;
        if (customerDAO.existsById(orderDTO.getCusId())){
            customer = customerDAO.getReferenceById(orderDTO.getCusId());
        }else {
            throw new DataPersistException("Customer not found");
        }
        order.setCustomer(customer);

        ArrayList<OrderItem> orderList = new ArrayList<>();

        for (ItemDTO itemDTO: orderDTO.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(AppUtil.generateOrderDetailId());
            orderItem.setOrder(order);

            Item item = itemDAO.findById(itemDTO.getId()).orElseThrow(() -> new RuntimeException("Item not found"));

            orderItem.setItem(item);
            orderItem.setOrderQty(itemDTO.getQty());

            orderList.add(orderItem);
        }

        order.setOrderItems(orderList);

        Order saveorder = orderDAO.save(order);
        if (saveorder == null) {
            throw new DataPersistException("Error saving order");
        }

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(String id, OrderDTO orderDTO) {

    }

    @Override
    public OrderDTO get(String id) {
        return null;
    }

    @Override
    public List<OrderDTO> getAll() {
        return null;
    }
}
