package ds.project1task2;

/*
 * @author Sreemoyee Mukherjee
 *
 * This file is the Model component of the MVC, and it models the business
 * logic for the web application.  In this case, the business logic involves
 * scraping multiple sites to gather all the information required about the selected
 * country's Women World Cup 2023 team.
 */

import com.google.gson.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task2Model {

    // Create URLs for the pages to be screen scraped
    public String nickNameURL = "https://www.topendsports.com/sport/soccer/team-nicknames-women.htm";
    public String capitalURL = "https://www.restcountries.com";
    public String topScorerURL = "https://www.espn.com/soccer/stats/_/league/FIFA.WWC/season/2019/view/scoring";
    public String flagSiteURL = "https://www.cia.gov/the-world-factbook/countries/";
    public String flagEmojiURL = "https://cdn.jsdelivr.net/npm/country-flag-emoji-json@2.0.0/dist/index.json";
    private Emoji[] emojis = new Emoji[29];

    // Set the Emoji array right when Task2Model is initialized
    public Task2Model() {
        getEmojiArray();
    }

    /**
     * Argument.
     * @param countryName Name of the country selected
     */
    public String getNickName(String countryName){
        String response = "", nickName = "";
        response = fetch(nickNameURL, "TLSV1.3");
        if(response.indexOf(countryName) != -1) {
            // Several small steps
            String cutLeft = response.substring(response.indexOf(countryName));
            cutLeft = cutLeft.substring(cutLeft.indexOf("</td><td>"));
            cutLeft = cutLeft.substring(9);
            nickName = cutLeft.substring(0, cutLeft.indexOf("</td><td>"));
        }
        else{
            nickName = "Not found";
        }
        return nickName;
    }

    /**
     * Argument.
     * @param countryName Name of the country selected
     */
    public String getCapital(String countryName){
        // special case for England
        if(countryName.equals("England"))
            countryName = "United Kingdom";
        countryName = countryName.replaceAll(" ", "%20");
        String url = capitalURL + "/v3.1/name/" + countryName + "?fullText=true";
        String response = "", capital = "";
        response = fetch(url, "TLSV1.3");
        // Several small steps
        String cutLeft = response.substring(response.indexOf("capital"));
        cutLeft = cutLeft.substring(cutLeft.indexOf("[")+2);
        capital = cutLeft.substring(0, cutLeft.indexOf("]"));
        capital = capital.replaceAll("\"", " ");
        return capital;
    }

    /**
     * Argument.
     * @param countryName Name of the country selected
     */
    public String getTopScorer(String countryName) {
        String topScorer = "";
        try {
            // using jsoup to parse the HTML
            Document doc = Jsoup.connect(topScorerURL).get();
            Elements div = doc.getElementsByClass("ResponsiveTable top-score-table");
            Elements table = div.select("table");
            Elements tbody = table.select("tbody");
            Elements tr = tbody.select("tr");
            for (Element e : tr) {
                if(countryName.equals(e.select("td").get(2).text().trim())) {
                    topScorer = e.select("td").get(1).text() + ", "
                            + e.select("td").get(4).text() + " goals";
                    break;
                }
            }
            // for countries with no top scorer ranking among the top 50 players
            if(topScorer.isBlank()){
                topScorer = "N/A";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return topScorer;
    }

    /**
     * Argument.
     * @param countryName Name of the country selected
     */
    public String getFlag(String countryName){
        String response = "", flagURL = "";
        // special case for England
        if(countryName.equals("England"))
            countryName = "United Kingdom";
        // replacing spaces with '-'
        countryName = countryName.replaceAll(" ", "-");
        String url = flagSiteURL + countryName.toLowerCase();
        response = fetch(url, "TLSV1.3");
        // Several small steps
        int end = response.indexOf("flag.jpg") + 8;
        response  = response.substring(0, end);
        int begin = response.lastIndexOf("src=\"");
        response = response.substring(begin + 5);
        flagURL = "https://www.cia.gov/" + response;
        return flagURL;
    }

    // Set the Emoji array once
    public Emoji[] getEmojiArray(){
        int index = 0;
        // our countries of interest
        String[] countries = new String[]{"Argentina", "Australia", "Brazil", "Canada", "China",
                "Colombia", "Costa Rica", "Denmark", "England", "France", "Germany", "Ireland",
                "Italy", "Jamaica", "Japan", "Morocco", "Netherlands", "New Zealand", "Nigeria",
                "Norway", "Philippines", "South Africa", "South Korea", "Spain", "Sweden",
                "Switzerland", "United States", "Vietnam", "Zambia"};
        try (InputStream is = new URL(flagEmojiURL).openStream();
             Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            // using gson to parse the JSON
            JsonParser parser = new JsonParser();
            JsonElement tree = parser.parse(reader);

            JsonArray array = tree.getAsJsonArray();

            for (JsonElement element : array) {
                if (element.isJsonObject()) {
                    Gson gson = new Gson();
                    Emoji emoji = gson.fromJson(element, Emoji.class);
                    for (String country: countries){
                        // only populate in array if it is a country of our interest
                        if(country.equals(emoji.getName())){
                            emojis[index] = emoji;
                            index++;
                        }
                    }
                }
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return emojis;
    }

    /**
     * Argument.
     * @param countryName Name of the country selected
     */
    public String getFlagEmoji(String countryName) {
        String emojiURL = "";
        for (Emoji e : emojis) {
            if(e.getName().equals(countryName)){
                emojiURL = e.getImage();
            }
        }
        return emojiURL;
    }
    private String fetch(String searchURL, String certType) {
        try {
            // Create trust manager, which lets you ignore SSLHandshakeExceptions
            createTrustManager(certType);
        } catch (KeyManagementException ex) {
            System.out.println("Shouldn't come here: ");
            ex.printStackTrace();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Shouldn't come here: ");
            ex.printStackTrace();
        }

        String response = "";
        try {
            URL url = new URL(searchURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String str;
            // Read each line of "in" until done, adding each to "response"
            while ((str = in.readLine()) != null) {
                // str is one line of text readLine() strips newline characters
                response += str;
            }
            in.close();
        } catch (IOException e) {
            System.err.println("Something wrong with URL");
            return null;
        }
        return response;
    }

    private void createTrustManager(String certType) throws KeyManagementException, NoSuchAlgorithmException{
        /**
         * Annoying SSLHandShakeException. After trying several methods, finally this
         * seemed to work.
         * Taken from: http://www.nakov.com/blog/2009/07/16/disable-certificate-validation-in-java-ssl-connections/
         */
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }
        };

        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance(certType);
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }

}
