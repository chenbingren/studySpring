package com.cbr.cbrdemoone.config;

/**
 * Created by Lenovo on 2019/7/28.
 */
public class Constants {

    public static final String CBR_TOPIC_EXCHANGE_NAME = "cbrTopicExchange";
    public static final String CBR_TOPIC_QUEUE_NAME = "cbr.topic";
    public static final String CBR_TOPIC_A_QUEUE_NAME = "cbr.topic.AA";
    public static final String CBR_TOPIC_B_QUEUE_NAME = "cbr.topic.BB";
    public static final String CBR_TOPIC_C_QUEUE_NAME = "cbr.topic.CC";
    public static final String CBR_TOPIC_D_QUEUE_NAME = "cbr.topic.DD";

    //*（星号）：可以（只能）匹配一个单词【进过测试，只要匹配前面的就会认为匹配了，如：cbr.*.BB==匹配=cbr.topic.AA】
    //#（井号）：可以匹配多个单词（或者零个） cbr.topic.AA
    // 案列：“audit.#”能够匹配到“audit.irs.corporate”，但是“audit.*” 只会匹配到“audit.irs”
    public static final String TOPIC_O1_ROUTE_KEY = "#.topic.AA.BB";//
    public static final String TOPIC_O2_ROUTE_KEY = "cbr.topic.#";//
    public static final String TOPIC_O3_ROUTE_KEY = "cbr.*.AA";//相等于：：cbr.#
    public static final String TOPIC_O4_ROUTE_KEY = "topic.#";
    public static final String TOPIC_O5_ROUTE_KEY = "#";
}
