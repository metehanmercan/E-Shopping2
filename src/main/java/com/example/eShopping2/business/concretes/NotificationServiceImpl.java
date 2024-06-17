package com.example.eShopping2.business.concretes;

import com.example.eShopping2.InvoiceGenerator;
import com.example.eShopping2.business.abstracts.NotificationService;
import com.example.eShopping2.entity.Order;
import com.example.eShopping2.entity.OrderItem;
import com.example.eShopping2.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@AllArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

    private JavaMailSender mailSender;
    private InvoiceGenerator invoiceGenerator; // fatura için

    @Override
    public void sendOrderConfirmation(User user, Order order) {
        String to = user.getEmail();
        String subject = "Sipariş Onayı - Sipariş Numaranız: " + order.getId();
        String text = generateOrderConfirmationText(user, order);

        // e-postası gönderimi
        sendMail(to, subject, text);


    }
    @Override
    public void sendOrderConfirmationInvoice(User user, Order order) {
        // Fatura  gönderimi
        sendInvoiceEmail(user, order);
    }


    private void sendInvoiceEmail(User user, Order order) {
        try {
            byte[] invoiceBytes = invoiceGenerator.generateInvoice(order);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8");

            helper.setTo(user.getEmail());
            helper.setSubject("Sipariş Numarası için Fatura " + order.getId());
            helper.setText("Sayın " + user.getFullName() + ",\n\nLütfen faturanızı kontrol ediniz.\n\nSiparişiniz için teşekkürler!\n\nSaygılarımla,\nTRENDYOL");

            // PDF olarak fatura dosyasını ekleyin
            helper.addAttachment("e-fatura.pdf", new ByteArrayResource(invoiceBytes), "application/pdf");

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // Hata işleme
        }
    }

    private void sendMail(String to, String subject, String text) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // Hata işleme geliştirilebilir
        }
    }

    private String generateOrderConfirmationText(User user, Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append("Sayın ").append(user.getFullName()).append(",<br><br>");
        sb.append("Siparişiniz için teşekkür ederiz! Sipariş detaylarınız aşağıdaki gibidir:<br><br>");
        sb.append("<b>Sipariş Numarası:</b> ").append(order.getId()).append("<br>");
        sb.append("<b>Sipariş Tarihi:</b> ").append(order.getOrderDate()).append("<br>");
        sb.append("<b>Toplam Fiyat:</b> ").append(order.getTotalPrice()).append(" TL<br><br>");
        sb.append("<b>Sipariş Ürünleri:</b><br>");
        for (OrderItem item : order.getOrderItems()) {
            sb.append("- ").append(item.getProduct().getName())
                    .append("  Açıklama: ").append(item.getProduct().getDescription()).append("<br>")
                    .append(" (Adet: ")
                    .append(item.getQuantity()).append(", Fiyat: ")
                    .append(item.getPrice()).append(" TL)<br>");
        }
        sb.append("<br>Siparişiniz en kısa sürede kargoya verilecektir. Kargo takip numaranız size ayrıca iletilecektir.<br><br>");
        sb.append("Herhangi bir sorunuz varsa, bizimle iletişime geçmekten çekinmeyin.<br><br>");
        sb.append("İletişim: 05443638139<br><br>");
        sb.append("Bizimle alışveriş yaptığınız için teşekkür ederiz!<br><br>");
        sb.append("Saygılarımızla,<br>");
        sb.append("TRENDYOL");

        return sb.toString();
    }


    public void sendOrderCancellationNotification(User user, Order order) {
        String to = user.getEmail();
        String subject = "Sipariş İptali - Sipariş Numaranız: " + order.getId();
        String text = generateOrderCancellationText(user, order);

        sendEmail(to, subject, text);
    }

    private String generateOrderCancellationText(User user, Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append("Sayın ").append(user.getFullName()).append(",\n\n");
        sb.append(order.getId()).append(" numaralı siparişiniz başarıyla iptal edilmiştir.\n\n");
        sb.append("Teşekkür ederiz,\n");
        sb.append("Şirket Adınız");

        return sb.toString();
    }

    private void sendEmail(String to, String subject, String text) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // Hata işleme geliştirilebilir
        }
    }

    public void sendOrderStatusUpdateNotification(User user, Order order) {
        String to = user.getEmail();
        String subject = "Order Status Update - Order ID " + order.getId();
        String text = generateOrderStatusUpdateText(user, order);

        sendEmail(to, subject, text);
    }

    private String generateOrderStatusUpdateText(User user, Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append("Dear ").append(user.getFullName()).append(",\n\n");
        sb.append("Your order with ID ").append(order.getId()).append(" has been updated to ").append(order.getStatus()).append(".\n\n");
        sb.append("Thank you,\n");
        sb.append("Your Company Name");

        return sb.toString();
    }
}

