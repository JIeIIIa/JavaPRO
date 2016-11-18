package HomeTasks.Task_02_03_YahooFinanceXml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by JIeIIIa on 18.11.2016.
 */
public class YahooFinanceXmlRunner {
    public static void main(String[] args) throws JAXBException {
        String request = "http://query.yahooapis.com/v1/public/yql?format=xml&q=select%20*%20from%20" +
                "yahoo.finance.xchange%20where%20pair%20in%20(\"USDEUR\",%20\"USDUAH\")&env=store://datatables.org/alltableswithkeys";

        String xmlText = null;
        try {
            xmlText = performRequest(request);
            System.out.println("Get XML:");
            System.out.println(xmlText);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JAXBContext jaxbContext = JAXBContext.newInstance(XML.class);
        Unmarshaller unmarshal = jaxbContext.createUnmarshaller();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xmlText.getBytes());
        Query query = (Query) unmarshal.unmarshal(byteArrayInputStream);
        System.out.println("Parse XML:");
        System.out.println(query);

        System.out.println();
        System.out.println("-=   RESULTS   =-");
        query.print();
    }

    private static String performRequest(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        StringBuilder sb = new StringBuilder();

        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
            char[] buf = new char[1000000];

            int r = 0;
            do {
                if ((r = br.read(buf)) > 0)
                    sb.append(new String(buf, 0, r));
            } while (r > 0);
        } finally {
            http.disconnect();
        }

        return sb.toString();
    }
}
