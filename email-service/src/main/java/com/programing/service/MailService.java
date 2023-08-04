package com.programing.service;

import com.programing.model.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    @KafkaListener(topics = "send-mail", groupId = "send-mail-id")
    public void sendMail(OrderResponse order) throws MessagingException {
        System.out.println(order.toString());
        send(order.getCustomer().getEmail(), buildEmail(order));
    }

    public void send(String to, String email) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(email, true);
        helper.setTo(to);
        helper.setSubject("Info order ticket buses");
        helper.setFrom("quocbao642002@gmail.com");
        mailSender.send(mimeMessage);

    }

    private String buildEmail(OrderResponse order) {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\n" +
                "<head>\n" +
                "  <title></title>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
                "  <style type=\"text/css\">\n" +
                "    @media screen {\n" +
                "      @font-face {\n" +
                "        font-family: \"Lato\";\n" +
                "        font-style: normal;\n" +
                "        font-weight: 400;\n" +
                "        src: local(\"Lato Regular\"), local(\"Lato-Regular\"),\n" +
                "          url(https://fonts.gstatic.com/s/lato/v11/qIIYRU-oROkIk8vfvxw6QvesZW2xOQ-xsNqO47m55DA.woff) format(\"woff\");\n" +
                "      }\n" +
                "\n" +
                "      @font-face {\n" +
                "        font-family: \"Lato\";\n" +
                "        font-style: normal;\n" +
                "        font-weight: 700;\n" +
                "        src: local(\"Lato Bold\"), local(\"Lato-Bold\"),\n" +
                "          url(https://fonts.gstatic.com/s/lato/v11/qdgUG4U09HnJwhYI-uK18wLUuEpTyoUstqEm5AMlJo4.woff) format(\"woff\");\n" +
                "      }\n" +
                "\n" +
                "      @font-face {\n" +
                "        font-family: \"Lato\";\n" +
                "        font-style: italic;\n" +
                "        font-weight: 400;\n" +
                "        src: local(\"Lato Italic\"), local(\"Lato-Italic\"),\n" +
                "          url(https://fonts.gstatic.com/s/lato/v11/RYyZNoeFgb0l7W3Vu1aSWOvvDin1pK8aKteLpeZ5c0A.woff) format(\"woff\");\n" +
                "      }\n" +
                "\n" +
                "      @font-face {\n" +
                "        font-family: \"Lato\";\n" +
                "        font-style: italic;\n" +
                "        font-weight: 700;\n" +
                "        src: local(\"Lato Bold Italic\"), local(\"Lato-BoldItalic\"),\n" +
                "          url(https://fonts.gstatic.com/s/lato/v11/HkF_qI1x_noxlxhrhMQYELO3LdcAZYWl9Si6vvxL-qU.woff) format(\"woff\");\n" +
                "      }\n" +
                "    }\n" +
                "\n" +
                "    /* CLIENT-SPECIFIC STYLES */\n" +
                "    body,\n" +
                "    table,\n" +
                "    td,\n" +
                "    a {\n" +
                "      -webkit-text-size-adjust: 100%;\n" +
                "      -ms-text-size-adjust: 100%;\n" +
                "    }\n" +
                "\n" +
                "    table,\n" +
                "    td {\n" +
                "      mso-table-lspace: 0pt;\n" +
                "      mso-table-rspace: 0pt;\n" +
                "    }\n" +
                "\n" +
                "    img {\n" +
                "      -ms-interpolation-mode: bicubic;\n" +
                "    }\n" +
                "\n" +
                "    /* RESET STYLES */\n" +
                "    img {\n" +
                "      border: 0;\n" +
                "      height: auto;\n" +
                "      line-height: 100%;\n" +
                "      outline: none;\n" +
                "      text-decoration: none;\n" +
                "    }\n" +
                "\n" +
                "    table {\n" +
                "      border-collapse: collapse !important;\n" +
                "    }\n" +
                "\n" +
                "    body {\n" +
                "      height: 100% !important;\n" +
                "      margin: 0 !important;\n" +
                "      padding: 0 !important;\n" +
                "      width: 100% !important;\n" +
                "    }\n" +
                "\n" +
                "    /* iOS BLUE LINKS */\n" +
                "    a[x-apple-data-detectors] {\n" +
                "      color: inherit !important;\n" +
                "      text-decoration: none !important;\n" +
                "      font-size: inherit !important;\n" +
                "      font-family: inherit !important;\n" +
                "      font-weight: inherit !important;\n" +
                "      line-height: inherit !important;\n" +
                "    }\n" +
                "\n" +
                "    /* MOBILE STYLES */\n" +
                "    @media screen and (max-width: 600px) {\n" +
                "      h1 {\n" +
                "        font-size: 32px !important;\n" +
                "        line-height: 32px !important;\n" +
                "      }\n" +
                "    }\n" +
                "\n" +
                "    /* ANDROID CENTER FIX */\n" +
                "    div[style*=\"margin: 16px 0;\"] {\n" +
                "      margin: 0 !important;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"\n" +
                "        background-color: #f4f4f4;\n" +
                "        margin: 0 !important;\n" +
                "        padding: 0 !important;\">\n" +
                "\n" +
                "  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "    <!-- LOGO -->\n" +
                "    <tr>\n" +
                "      <td bgcolor=\"#FFA73B\" align=\"center\">\n" +
                "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px\">\n" +
                "          <tr>\n" +
                "            <td align=\"center\" valign=\"top\" style=\"padding: 40px 10px 40px 10px\"></td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td bgcolor=\"#FFA73B\" align=\"center\" style=\"padding: 0px 10px 0px 10px\">\n" +
                "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px\">\n" +
                "          <tr>\n" +
                "            <td bgcolor=\"#ffffff\" align=\"center\" valign=\"top\" style=\"\n" +
                "                    padding: 40px 20px 20px 20px;\n" +
                "                    border-radius: 4px 4px 0px 0px;\n" +
                "                    color: #111111;\n" +
                "                    font-family: 'Lato', Helvetica, Arial, sans-serif;\n" +
                "                    font-size: 48px;\n" +
                "                    font-weight: 400;\n" +
                "                    letter-spacing: 4px;\n" +
                "                    line-height: 48px;\n" +
                "                  \">\n" +
                "              <h1 style=\"font-size: 48px; font-weight: 400; margin: 2\">\n" +
                "                Xác Nhận Vé Xe\n" +
                "              </h1>\n" +
                "              <img src=\" https://img.icons8.com/clouds/100/000000/handshake.png\" width=\"125\" height=\"120\"\n" +
                "                style=\"display: block; border: 0px\" />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td bgcolor=\"#f4f4f4\" align=\"center\" style=\"padding: 0px 10px 0px 10px\">\n" +
                "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px\">\n" +
                "          <tr>\n" +
                "            <td bgcolor=\"#ffffff\" align=\"left\" style=\"\n" +
                "                    padding: 20px 30px 40px 30px;\n" +
                "                    color: #666666;\n" +
                "                    font-family: 'Lato', Helvetica, Arial, sans-serif;\n" +
                "                    font-size: 18px;\n" +
                "                    font-weight: 400;\n" +
                "                    line-height: 25px;\n" +
                "                  \">\n" +
                "              <p style=\"margin: 0\">\n" +
                "                Khách hàng:\n" +
                "              </p>\n" +
                "            </td>\n" +
                "            <td bgcolor=\"#ffffff\" align=\"left\" style=\"\n" +
                "                    padding: 20px 30px 40px 30px;\n" +
                "                    color: #666666;\n" +
                "                    font-family: 'Lato', Helvetica, Arial, sans-serif;\n" +
                "                    font-size: 18px;\n" +
                "                    font-weight: 400;\n" +
                "                    line-height: 25px;\n" +
                "                  \">\n" +
                "              <p style=\"margin: 0\">\n" +
                "                "+order.getCustomer().getFullName()+"\n" +
                "              </p>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "      \n" +
                "          <!-- COPY -->\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td bgcolor=\"#f4f4f4\" align=\"center\" style=\"padding: 0px 10px 0px 10px\">\n" +
                "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px\">\n" +
                "          <tr>\n" +
                "            <td bgcolor=\"#ffffff\" align=\"left\" style=\"\n" +
                "                    padding: 20px 30px 40px 30px;\n" +
                "                    color: #666666;\n" +
                "                    font-family: 'Lato', Helvetica, Arial, sans-serif;\n" +
                "                    font-size: 18px;\n" +
                "                    font-weight: 400;\n" +
                "                    line-height: 25px;\n" +
                "                  \">\n" +
                "              <p style=\"margin: 0\">\n" +
                "                Chặng đi:\n" +
                "              </p>\n" +
                "            </td>\n" +
                "            <td bgcolor=\"#ffffff\" align=\"left\" style=\"\n" +
                "                    padding: 20px 30px 40px 30px;\n" +
                "                    color: #666666;\n" +
                "                    font-family: 'Lato', Helvetica, Arial, sans-serif;\n" +
                "                    font-size: 18px;\n" +
                "                    font-weight: 400;\n" +
                "                    line-height: 25px;\n" +
                "                  \">\n" +
                "              <p style=\"margin: 0\">\n" +
                "                "+order.getBuses().getRoute()+"\n" +
                "              </p>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "      \n" +
                "          <!-- COPY -->\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td bgcolor=\"#f4f4f4\" align=\"center\" style=\"padding: 0px 10px 0px 10px\">\n" +
                "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px\">\n" +
                "          <tr>\n" +
                "            <td bgcolor=\"#ffffff\" align=\"left\" style=\"\n" +
                "                    padding: 20px 30px 40px 30px;\n" +
                "                    color: #666666;\n" +
                "                    font-family: 'Lato', Helvetica, Arial, sans-serif;\n" +
                "                    font-size: 18px;\n" +
                "                    font-weight: 400;\n" +
                "                    line-height: 25px;\n" +
                "                  \">\n" +
                "              <p style=\"margin: 0\">\n" +
                "                Giờ khởi hành:\n" +
                "              </p>\n" +
                "            </td>\n" +
                "            <td bgcolor=\"#ffffff\" align=\"left\" style=\"\n" +
                "                    padding: 20px 30px 40px 30px;\n" +
                "                    color: #666666;\n" +
                "                    font-family: 'Lato', Helvetica, Arial, sans-serif;\n" +
                "                    font-size: 18px;\n" +
                "                    font-weight: 400;\n" +
                "                    line-height: 25px;\n" +
                "                  \">\n" +
                "              <p style=\"margin: 0\">\n" +
                "                "+order.getBuses().getDepartureTime()+"\n" +
                "              </p>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "      \n" +
                "          <!-- COPY -->\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td bgcolor=\"#f4f4f4\" align=\"center\" style=\"padding: 0px 10px 0px 10px\">\n" +
                "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px\">\n" +
                "          <tr>\n" +
                "            <td bgcolor=\"#ffffff\" align=\"left\" style=\"\n" +
                "                    padding: 20px 30px 40px 30px;\n" +
                "                    color: #666666;\n" +
                "                    font-family: 'Lato', Helvetica, Arial, sans-serif;\n" +
                "                    font-size: 18px;\n" +
                "                    font-weight: 400;\n" +
                "                    line-height: 25px;\n" +
                "                  \">\n" +
                "              <p style=\"margin: 0\">\n" +
                "               Số lượng vé\n" +
                "              </p>\n" +
                "            </td>\n" +
                "            <td bgcolor=\"#ffffff\" align=\"left\" style=\"\n" +
                "                    padding: 20px 30px 40px 30px;\n" +
                "                    color: #666666;\n" +
                "                    font-family: 'Lato', Helvetica, Arial, sans-serif;\n" +
                "                    font-size: 18px;\n" +
                "                    font-weight: 400;\n" +
                "                    line-height: 25px;\n" +
                "                  \">\n" +
                "              <p style=\"margin: 0\">\n" +
                "                "+order.getQuantity()+" vé\n" +
                "              </p>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "      \n" +
                "          <!-- COPY -->\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td bgcolor=\"#f4f4f4\" align=\"center\" style=\"padding: 0px 10px 0px 10px\">\n" +
                "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px\">\n" +
                "          <tr>\n" +
                "            <td bgcolor=\"#ffffff\" align=\"left\" style=\"\n" +
                "                    padding: 20px 30px 40px 30px;\n" +
                "                    color: #666666;\n" +
                "                    font-family: 'Lato', Helvetica, Arial, sans-serif;\n" +
                "                    font-size: 18px;\n" +
                "                    font-weight: 400;\n" +
                "                    line-height: 25px;\n" +
                "                  \">\n" +
                "              <p style=\"margin: 0\">\n" +
                "                Tổng tiền:\n" +
                "              </p>\n" +
                "            </td>\n" +
                "            <td bgcolor=\"#ffffff\" align=\"left\" style=\"\n" +
                "                    padding: 20px 30px 40px 30px;\n" +
                "                    color: #666666;\n" +
                "                    font-family: 'Lato', Helvetica, Arial, sans-serif;\n" +
                "                    font-size: 18px;\n" +
                "                    font-weight: 400;\n" +
                "                    line-height: 25px;\n" +
                "                  \">\n" +
                "              <p style=\"margin: 0\">\n" +
                "                "+order.getTotalPrice()+"\n" +
                "              </p>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "      \n" +
                "          <!-- COPY -->\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td bgcolor=\"#f4f4f4\" align=\"center\" style=\"padding: 0px 10px 0px 10px\">\n" +
                "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px\">\n" +
                "          <tr>\n" +
                "            <td bgcolor=\"#ffffff\" align=\"left\" style=\"\n" +
                "                    padding: 20px 30px 40px 30px;\n" +
                "                    color: #666666;\n" +
                "                    font-family: 'Lato', Helvetica, Arial, sans-serif;\n" +
                "                    font-size: 18px;\n" +
                "                    font-weight: 400;\n" +
                "                    line-height: 25px;\n" +
                "                  \">\n" +
                "              <p style=\"margin: 0\">\n" +
                "                Điểm đón khách:\n" +
                "              </p>\n" +
                "            </td>\n" +
                "            <td bgcolor=\"#ffffff\" align=\"left\" style=\"\n" +
                "                    padding: 20px 30px 40px 30px;\n" +
                "                    color: #666666;\n" +
                "                    font-family: 'Lato', Helvetica, Arial, sans-serif;\n" +
                "                    font-size: 18px;\n" +
                "                    font-weight: 400;\n" +
                "                    line-height: 25px;\n" +
                "                  \">\n" +
                "              <p style=\"margin: 0\">\n" +
                "                "+order.getPickUpLocation()+"\n" +
                "              </p>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "      \n" +
                "          <!-- COPY -->\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td bgcolor=\"#f4f4f4\" align=\"center\" style=\"padding: 0px 10px 0px 10px\">\n" +
                "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px\">\n" +
                "          <tr>\n" +
                "            <td bgcolor=\"#ffffff\" align=\"left\" style=\"\n" +
                "                    padding: 20px 30px 40px 30px;\n" +
                "                    color: #666666;\n" +
                "                    font-family: 'Lato', Helvetica, Arial, sans-serif;\n" +
                "                    font-size: 18px;\n" +
                "                    font-weight: 400;\n" +
                "                    line-height: 25px;\n" +
                "                  \">\n" +
                "              <p style=\"margin: 0\">\n" +
                "                Thanh toán trước:\n" +
                "              </p>\n" +
                "            </td>\n" +
                "            <td bgcolor=\"#ffffff\" align=\"left\" style=\"\n" +
                "                    padding: 20px 30px 40px 30px;\n" +
                "                    color: #666666;\n" +
                "                    font-family: 'Lato', Helvetica, Arial, sans-serif;\n" +
                "                    font-size: 18px;\n" +
                "                    font-weight: 400;\n" +
                "                    line-height: 25px;\n" +
                "                  \">\n" +
                "              <p style=\"margin: 0\">\n" +
                "                "+order.getPaymentBeforeDate()+"\n" +
                "              </p>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "      \n" +
                "          <!-- COPY -->\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "\n" +
                "    <tr>\n" +
                "      <td bgcolor=\"#f4f4f4\" align=\"center\" style=\"padding: 30px 10px 0px 10px\">\n" +
                "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px\">\n" +
                "          <tr>\n" +
                "            <td bgcolor=\"#FFECD1\" align=\"center\" style=\"\n" +
                "                    padding: 30px 30px 30px 30px;\n" +
                "                    border-radius: 4px 4px 4px 4px;\n" +
                "                    color: #666666;\n" +
                "                    font-family: 'Lato', Helvetica, Arial, sans-serif;\n" +
                "                    font-size: 18px;\n" +
                "                    font-weight: 400;\n" +
                "                    line-height: 25px;\n" +
                "                  \">\n" +
                "              <h2 style=\"\n" +
                "                      font-size: 20px;\n" +
                "                      font-weight: 400;\n" +
                "                      color: #111111;\n" +
                "                      margin: 0;\n" +
                "                    \">\n" +
                "                Need more help?\n" +
                "              </h2>\n" +
                "              <p style=\"margin: 0\">\n" +
                "                <a href=\"https://www.facebook.com/cong.nguyenngoc.50159\" target=\"_blank\"\n" +
                "                  style=\"color: #ffa73b\">We&rsquo;re here to help you out</a>\n" +
                "              </p>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td bgcolor=\"#f4f4f4\" align=\"center\" style=\"padding: 0px 10px 0px 10px\">\n" +
                "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px\">\n" +
                "          <tr>\n" +
                "            <td bgcolor=\"#f4f4f4\" align=\"left\" style=\"\n" +
                "                    padding: 0px 30px 30px 30px;\n" +
                "                    color: #666666;\n" +
                "                    font-family: 'Lato', Helvetica, Arial, sans-serif;\n" +
                "                    font-size: 14px;\n" +
                "                    font-weight: 400;\n" +
                "                    line-height: 18px;\n" +
                "                  \">\n" +
                "              <br />\n" +
                "              <p style=\"margin: 0\">\n" +
                "                If these emails get annoying, please feel free to\n" +
                "                <a href=\"#\" target=\"_blank\" style=\"color: #111111; font-weight: 700\">unsubscribe</a>.\n" +
                "              </p>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </table>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }

}
