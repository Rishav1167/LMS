package com.lms.authms.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public MessageConverter jsonMessageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();

        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        typeMapper.setTrustedPackages("com.lms", "java.util");

        converter.setJavaTypeMapper(typeMapper);
        return converter;
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    public static final String EXCHANGE_NAME = "lms.exchange";

    public static final String USER_CREATED_QUEUE = "user.created.queue";
    public static final String OTP_SENT_QUEUE = "otp.sent.queue";
    public static final String BATCH_SCHEDULED_QUEUE = "batch.scheduled.queue";
    public static final String RESULT_PUBLISHED_QUEUE = "result.published.queue";
    public static final String PAYMENT_SUCCESS_QUEUE = "payment.success.queue";
    public static final String FEEDBACK_SUBMITTED_QUEUE = "feedback.submitted.queue";

    public static final String USER_CREATED_ROUTING_KEY = "user.created";
    public static final String OTP_SENT_ROUTING_KEY = "otp.sent";
    public static final String BATCH_SCHEDULED_ROUTING_KEY = "batch.scheduled";
    public static final String RESULT_PUBLISHED_ROUTING_KEY = "result.published";
    public static final String PAYMENT_SUCCESS_ROUTING_KEY = "payment.success";
    public static final String FEEDBACK_SUBMITTED_ROUTING_KEY = "feedback.submitted";

    @Bean
    public TopicExchange lmsExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue userCreatedQueue() {
        return new Queue(USER_CREATED_QUEUE);
    }

    @Bean
    public Queue otpSentQueue() {
        return new Queue(OTP_SENT_QUEUE);
    }

    @Bean
    public Queue batchScheduledQueue() {
        return new Queue(BATCH_SCHEDULED_QUEUE);
    }

    @Bean
    public Queue resultPublishedQueue() {
        return new Queue(RESULT_PUBLISHED_QUEUE);
    }

    @Bean
    public Queue paymentSuccessQueue() {
        return new Queue(PAYMENT_SUCCESS_QUEUE);
    }

    @Bean
    public Queue feedbackSubmittedQueue() {
        return new Queue(FEEDBACK_SUBMITTED_QUEUE);
    }

    @Bean
    public Binding userCreatedBinding(Queue userCreatedQueue, TopicExchange lmsExchange) {
        return BindingBuilder.bind(userCreatedQueue).to(lmsExchange).with(USER_CREATED_ROUTING_KEY);
    }

    @Bean
    public Binding otpSentBinding(Queue otpSentQueue, TopicExchange lmsExchange) {
        return BindingBuilder.bind(otpSentQueue).to(lmsExchange).with(OTP_SENT_ROUTING_KEY);
    }

    @Bean
    public Binding batchScheduledBinding(Queue batchScheduledQueue, TopicExchange lmsExchange) {
        return BindingBuilder.bind(batchScheduledQueue).to(lmsExchange).with(BATCH_SCHEDULED_ROUTING_KEY);
    }

    @Bean
    public Binding resultPublishedBinding(Queue resultPublishedQueue, TopicExchange lmsExchange) {
        return BindingBuilder.bind(resultPublishedQueue).to(lmsExchange).with(RESULT_PUBLISHED_ROUTING_KEY);
    }

    @Bean
    public Binding paymentSuccessBinding(Queue paymentSuccessQueue, TopicExchange lmsExchange) {
        return BindingBuilder.bind(paymentSuccessQueue).to(lmsExchange).with(PAYMENT_SUCCESS_ROUTING_KEY);
    }

    @Bean
    public Binding feedbackSubmittedBinding(Queue feedbackSubmittedQueue, TopicExchange lmsExchange) {
        return BindingBuilder.bind(feedbackSubmittedQueue).to(lmsExchange).with(FEEDBACK_SUBMITTED_ROUTING_KEY);
    }
}