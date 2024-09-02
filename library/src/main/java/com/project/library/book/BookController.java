package com.project.library.book;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    // 네이버 도서 검색 api key
    private final String CLIENT_ID = "nGLQmVoQKaRg1ej9ex2m";
    private final String CLIENT_SECRET = "aa3qcEpMNh";


    @GetMapping("books.book")
    public String home(Model model) {
        model.addAttribute("board", new BookDto());
        return "addForm";
    }

    @GetMapping("/book-search")
    public String BookSearchHome(Model model) {
        String keyword = "";
        model.addAttribute("keyword", keyword);
        return "bookSearch";
    }

    @PostMapping("/book-search")
    public String search(@ModelAttribute("keyword") String keyword, Model model) {
        try {
            String encodedKeyword = URLEncoder.encode(keyword, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/book.json?query=" + encodedKeyword;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
            con.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();


            JSONObject jsonObject = new JSONObject(response.toString());
            JSONArray jsonArray = (JSONArray) jsonObject.get("items");
            List<BookDto> bookDtoList = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String title = obj.getString("title");
                String author = obj.getString("author");
                String image = obj.getString("image");
                String description = obj.getString("description");
                String isbn = obj.getString("isbn");
                String publisher = obj.getString("publisher");
                String pubdate = obj.getString("pubdate");

                bookDtoList.add(BookDto.builder()
                        .title(title)
                        .author(author)
                        .imageURL(image)
                		.description(description)
                		.isbn(isbn)
                		.publisher(publisher)
                		.pubdate(pubdate).build());
                
            }
            model.addAttribute("bookDtoList", bookDtoList);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "addForm";
    }
    
    @GetMapping("bookDetail.book")
    public String bookDetail(Model model, @RequestParam("query") String keyword) {
    	try {
            String encodedKeyword = URLEncoder.encode(keyword, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/book.json?query=" + encodedKeyword;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
            con.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();


            JSONObject jsonObject = new JSONObject(response.toString());
            JSONArray jsonArray = (JSONArray) jsonObject.get("items");
            List<BookDto> bookDtoList = new ArrayList<>();
            
            String description = null;
            String publisher = null;
            String pubdate = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String title = obj.getString("title");
                String author = obj.getString("author");
                String image = obj.getString("image");
                description = obj.getString("description");
                String isbn = obj.getString("isbn");
                publisher = obj.getString("publisher");
                pubdate = obj.getString("pubdate");

                bookDtoList.add(BookDto.builder()
                        .title(title)
                        .author(author)
                        .imageURL(image)
                		.description(description)
                		.isbn(isbn)
                		.publisher(publisher)
                		.pubdate(pubdate).build());
                
            }
            model.addAttribute("bookDtoList", bookDtoList);
            model.addAttribute("description", description);
            model.addAttribute("publisher", publisher);
            model.addAttribute("pubdate", pubdate);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "bookSearch";
    }
    
}
