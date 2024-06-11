package com.example.eShopping2.business.concretes;

import com.example.eShopping2.business.abstracts.NotificationService;
import com.example.eShopping2.entity.Order;
import com.example.eShopping2.entity.OrderItem;
import com.example.eShopping2.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@AllArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

        private JavaMailSender mailSender;
    @Override
    public void sendOrderConfirmation(User user, Order order) {
        String to=user.getEmail();
        String subject = "Sipariş Onayı - Sipariş Numaranız: " + order.getId();
        String text = generateOrderConfirmationText(user, order);
        
        sendMail(to,subject,text);
    }

    private void sendMail(String to, String subject, String text) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, false);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // Hata işleme geliştirilebilir
        }
    }

    private String generateOrderConfirmationText(User user, Order order) {
        StringBuilder sb=new StringBuilder();
        sb.append("Sayın ").append(user.getFullName()).append(",\n\n");
        sb.append("Siparişiniz için teşekkür ederiz! Sipariş detaylarınız aşağıdaki gibidir:\n\n");
        sb.append("Sipariş Numarası: ").append(order.getId()).append("\n");
        sb.append("Sipariş Tarihi: ").append(order.getOrderDate()).append("\n");
        sb.append("Toplam Fiyat: ").append(order.getTotalPrice()).append(" TL\n\n");
        sb.append("Sipariş Ürünleri:\n");
        for (OrderItem item : order.getOrderItems()) {
            sb.append("- ").append(item.getProduct().getName())
                    .append("  Açıklama: ").append(item.getProduct().getDescription()).append("\n")
                    .append(" (Adet: ")
                    .append(item.getQuantity()).append(", Fiyat: ")
                    .append(item.getPrice()).append(" TL)\n");
        }
        sb.append("Siparişiniz en kısa sürede kargoya verilecektir. Kargo takip numaranız size ayrıca iletilecektir.\n\n");
        sb.append("Herhangi bir sorunuz varsa, bizimle iletişime geçmekten çekinmeyin.\n\n");
        sb.append("İletişim: 05443638139\n");
        sb.append("\nBizimle alışveriş yaptığınız için teşekkür ederiz!\n\n");
        sb.append("Saygılarımızla,\n");
        sb.append("TRENDYOL");

        return sb.toString();
    }

}
