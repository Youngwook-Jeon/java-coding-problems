package com.project.young.secondedition.P01_TextBlockSqlJsonHtml;

public class Solution {

    private static String sql="""
           UPDATE "public"."office"
           SET ("address_first", "address_second", "phone") =
             (SELECT "public"."employee"."first_name",
                     "public"."employee"."last_name", ?
              FROM "public"."employee"
              WHERE "public"."employee"."job_title" = ?""";

    private static String html = """
              <table>
                <tr>
                  <thcolspan="2">Name</th>
                  <th>Age</th>
                </tr>
                <tr>
                  <td>John</td>
                  <td>Smith</td>
                  <td>22</td>
                </tr>
              <table>""";

    private static String json = """
                       {
                         "widget": {
                           "debug": "on",
                           "window": {
                             "title": "Sample Widget 1",
                             "name": "back_window"
                           },
                           "image": {
                             "src": "images\\sw.png"
                           },
                           "text": {
                             "data": "Click Me",
                             "size": 39
                           }
                         }
                       }""";

    private static String xml = """
            <user>
                <firstName>
                    %s
                </firstName>
                <lastName>
                    %s
                </lastName>
            </user>
            """.formatted("Lucas", "Jeon");

    public static void main(String[] args) {
        System.out.println(sql);
        System.out.println(html);
        System.out.println(json);
        System.out.println(xml);
    }
}
