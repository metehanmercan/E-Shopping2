package com.example.eShopping2;

import com.example.eShopping2.entity.Order;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
public class InvoiceGenerator {
    public byte[] generateInvoice(Order order) {
        try {
            // 1. Rapor tasarımını yükleme
            InputStream reportStream = new ClassPathResource("invoice_template.jrxml").getInputStream();
            JasperDesign jasperDesign = JRXmlLoader.load(reportStream);

            // 2. Rapor derleme
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            // 3. Rapor parametrelerini hazırlama
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("orderId", order.getId());
            parameters.put("orderDate", order.getOrderDate());
            parameters.put("totalPrice", order.getTotalPrice());

            // 4. Rapor veri kaynağını hazırlama (isteğe bağlı, burada örnek veri kullanıldı)
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(order.getOrderItems());

            // 5. Raporu oluşturma
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // 6. PDF olarak çıktıyı oluşturma
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            // Hata yönetimi yapılabilir
            return null;
        }
    }

}
