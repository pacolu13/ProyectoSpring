package com.proyecto.services;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.proyecto.models.Order;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@SuppressWarnings("null")
public class MailService {

  private final JavaMailSender mailSender;
  private final TemplateEngine templateEngine;

  @Value("${mail.from}")
  private String from;

  // Método central — todos los demás lo llaman
  private void sendEmail(String to, String subject, String templateName, Context context) {
    try {
      String html = templateEngine.process(templateName, context);

      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
      helper.setFrom(from);
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(html, true);

      mailSender.send(message);
    } catch (MessagingException e) {
      throw new RuntimeException("Error al enviar el mail", e);
    }
  }

  public void sendOrderConfirmation(String to, Order order) {
    Context context = new Context();
    context.setVariable("orderId", order.getId());
    context.setVariable("total", order.getTotalBalance());
    context.setVariable("items", order.getOrderDetailsList().stream()
        .map(item -> Map.of(
            "name", item.getProductListing().getProduct().getName(),
            "quantity", item.getQuantity(),
            "subtotal", item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()))))
        .toList());

    sendEmail(to, "Confirmación de tu compra", "mail/order-confirmation", context);
  }

  public void sendAdminNotification(String to, String subject, String body) {
    Context context = new Context();
    context.setVariable("subject", subject);
    context.setVariable("body", body);

    sendEmail(to, subject, "mail/admin-notification", context);
  }
}