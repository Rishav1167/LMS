package com.lms.paymentms.service;

import com.lms.paymentms.domain.dto.PaymentRequest;
import com.lms.paymentms.domain.dto.PaymentResponse;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final RazorpayClient razorpayClient;

    public PaymentService(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    public PaymentResponse createOrder(PaymentRequest request) throws RazorpayException {
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", request.amount * 100);
        orderRequest.put("currency", request.currency);
        orderRequest.put("receipt", request.receipt);
        orderRequest.put("payment_capture", true);

        Order order = razorpayClient.orders.create(orderRequest);

        PaymentResponse response = new PaymentResponse();
        response.orderId = order.get("id");
        response.paymentLink = "https://checkout.razorpay.com/v1/checkout.js?order_id=" + response.orderId;
        return response;
    }
}