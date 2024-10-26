package vn.edu.tlu.id2151173784.models;

import java.util.List;

public class Order {
    private int id;
    private String orderDate;
    private String estimateDeliveryTime;
    private String address;
    private String status;
    private List<OrderDetail> orderDetails;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getEstimateDeliveryTime() {
        return estimateDeliveryTime;
    }

    public void setEstimateDeliveryTime(String estimateDeliveryTime) {
        this.estimateDeliveryTime = estimateDeliveryTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}