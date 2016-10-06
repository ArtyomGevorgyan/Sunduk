package com.javarush.test.level28.lesson15.big01.model;


import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy {
    private static final String URL_FORMAT = "http://moikrug.ru/vacancies?q=java+%s";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new ArrayList<>();
        try
        {
            Document document;
            int pageCounter = 1;

            while(true)
            {
                document = getDocument(searchString, pageCounter++);
                if(document == null) break;

                Elements elements = document.getElementsByClass("job");
                if(elements.size() == 0) break;

                for(Element element : elements)
                {
                    String title = "";
                    String salary = "";
                    String city = "";
                    String company = "";
                    String siteName = "https://moikrug.ru";
                    String url = "";

                    Vacancy vacancy = new Vacancy();




                    // Вариант разбора номер 2
                    Element titleElem = element.getElementsByClass("title").first();
                    if(titleElem != null)
                    {
                        title = titleElem.select("a").first().text();
                        url = siteName + titleElem.select("a").attr("href");
                    }

                    Element salaryElem = element.getElementsByClass("count").first();
                    if(salaryElem != null)
                    {
                        salary = salaryElem.text();
                    }

                    Element cityElem = element.getElementsByClass("location").first();
                    if(cityElem != null)
                    {
                        city = cityElem.text();
                    }

                    Element companyElem = element.getElementsByClass("company_name").first();
                    if(companyElem != null)
                    {
                        company = companyElem.select("a[href]").text();
                    }


                    vacancy.setTitle(title);
                    vacancy.setSalary(salary);
                    vacancy.setCity(city);
                    vacancy.setCompanyName(company);
                    vacancy.setSiteName(siteName);
                    vacancy.setUrl(url);

                    vacancies.add(vacancy);
                }
            }
        }
        catch (Exception e)
        {

        }

        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {

        Document document = Jsoup.connect("http://javarush.ru/testdata/big28data2.html").get();

        return document;
    }
}
