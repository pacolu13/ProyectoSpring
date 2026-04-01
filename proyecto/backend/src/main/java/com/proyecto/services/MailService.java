package com.proyecto.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.proyecto.models.Order;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    @Value("${mail.from}")
    private String from;

    @SuppressWarnings("null")
    public void sendOrderConfirmation(String to, Order order) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject("Confirmación de tu compra");
            helper.setText(buildHtml(order), true); // true = es HTML

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el mail", e);
        }
    }

    private String buildHtml(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append("""
                <div style="font-family: sans-serif; max-width: 600px; margin: auto; padding: 24px;">
                  <h2 style="color: #1a1a1a;">Gracias por tu compra</h2>
                  <p style="color: #666;">Orden <strong>#%d</strong></p>
                  <table style="width:100%%; border-collapse: collapse; margin-top: 16px;">
                    <thead>
                      <tr style="border-bottom: 1px solid #e0e0dc;">
                        <th style="text-align:left; padding: 8px; color:#999; font-weight:500;">Producto</th>
                        <th style="text-align:right; padding: 8px; color:#999; font-weight:500;">Cant.</th>
                        <th style="text-align:right; padding: 8px; color:#999; font-weight:500;">Subtotal</th>
                      </tr>
                    </thead>
                    <tbody>
                """.formatted(order.getId()));

        for (var item : order.getOrderDetailsList()) {
            sb.append("""
                    <tr style="border-bottom: 1px solid #f0f0ee;">
                      <td style="padding: 8px; color:#1a1a1a;">%s</td>
                      <td style="padding: 8px; text-align:right; color:#1a1a1a;">%d</td>
                      <td style="padding: 8px; text-align:right; color:#1a1a1a;">$%.2f</td>
                    </tr>
                    """.formatted(item.getProductListing().getProduct().getName(),
                     item.getQuantity(),(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))));
        }

        sb.append(
                """
                            </tbody>
                          </table>
                          <div style="margin-top: 16px; text-align:right;">
                            <span style="font-size: 18px; font-weight: 500; color:#1a1a1a;">Total: $%.2f</span>
                          </div>
                          <p style="margin-top: 32px; color:#999; font-size: 12px;">Este es un mail automático, no respondas este correo.</p>
                        </div>
                        """
                        .formatted(order.getTotalBalance()));

        return sb.toString();
    }
}
