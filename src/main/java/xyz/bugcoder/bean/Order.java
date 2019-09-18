package xyz.bugcoder.bean;

import xyz.bugcoder.service.OrderService;

import java.util.Date;
import java.util.List;

public class Order {
    private Integer id;

    private String orderCode;

    private String address;

    private String post;

    private String receiver;

    private String mobile;

    private String userMessage;

    private Date createDate;

    private Date payDate;

    private Date deliveryDate;

    private Date confirmDate;

    private Integer uid;

    private String status;

    // 非数据库字段，业务需求
    // 哪个用户的订单
    private User user;
    // 该订单下的订单项
    private List<OrderItem> orderItems;
    // 该订单的总价钱
    private float total;
    // 该订单的总数量
    private float totalNum;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderCode='" + orderCode + '\'' +
                ", address='" + address + '\'' +
                ", post='" + post + '\'' +
                ", receiver='" + receiver + '\'' +
                ", mobile='" + mobile + '\'' +
                ", userMessage='" + userMessage + '\'' +
                ", createDate=" + createDate +
                ", payDate=" + payDate +
                ", deliveryDate=" + deliveryDate +
                ", confirmDate=" + confirmDate +
                ", uid=" + uid +
                ", status='" + status + '\'' +
                ", user=" + user +
                ", orderItems=" + orderItems +
                ", total=" + total +
                ", totalNum=" + totalNum +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(float totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage == null ? null : userMessage.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    // 获取订单状态，并转换成中文
    public String getStatusDesc(){

        String desc = "未知";
        switch (status){
            case OrderService.waitPay:
                desc = "待付款";
                break;
            case OrderService.waitDelivery:
                desc = "待发货";
                break;
            case OrderService.waitConfirm:
                desc = "待收货";
                break;
            case OrderService.waitReview:
                desc = "待评价";
                break;
            case OrderService.finished:
                desc = "完成";
                break;
            case OrderService.deleted:
                desc = "删除";
                break;
            default:
                desc = "未知";
                break;
        }

        return desc;
    }
}