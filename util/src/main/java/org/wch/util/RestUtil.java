package org.wch.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weichunhe
 *         Created on 2016/9/2.
 * @version 1.0
 */
public class RestUtil {

    static Logger log = LoggerFactory.getLogger(RestUtil.class);

//    private static UserInfo validate(TokenBean bean) {
//        String token = bean.getToken();
//        String platNo = bean.getPlatNo();
//        String url = bean.getUrl();
//
//        Map<String, String> param = new HashMap<String, String>();
//        param.put("token", token);
//        UserInfo user = null;
//
//        HttpEntity<String> formEntity = restFormEntity(platNo, param);
//
//        String resp = restTmpl.postForObject(url, formEntity, String.class);
//        log.info("调用第三方API-token验证:{}-{}", JSONUtil.toJson(formEntity), resp);
//        user = JSONUtil.fromJson(resp, UserInfo.class);
//        if (user != null && user.getMemberId() == null) {
//            log.error("验证token失败,响应数据:{}", JSONUtil.toJson(user));
//            user = null;
//        }
//
//        return user;
//    }

    public static <T> T postJSON(RestTemplate restTemplate, String url, Map<String, ? extends Object> param, Class<T> responseType) {
        HttpEntity<String> formEntity = makePostJSONEntiry(param);
        T result = restTemplate.postForObject(url, formEntity, responseType);
        log.info("rest-post-json 响应信息:{}", JSONUtil.toJson(result));
        return result;
    }

    /**
     * 生成json形式的请求头
     *
     * @param param
     * @return
     */
    public static HttpEntity<String> makePostJSONEntiry(
            Map<String, ? extends Object> param) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
//        headers.add(HEADER_CRYPTOGRAPH, sha1(platNo, param));
//        headers.add(HEADER_AUTH_ID,
//                configService.getPlatValue(platNo, ConfigService.PLAT_AUTH_ID));
        HttpEntity<String> formEntity = new HttpEntity<String>(
                JSONUtil.toJson(param), headers);
        log.info("rest-post-json-请求参数:{}", formEntity.toString());
        return formEntity;
    }

    public static <T> T postText(RestTemplate restTemplate, String url, Map<String, ? extends Object> param, Class<T> responseType) {
        HttpEntity<String> formEntity = makePostTextEntiry(param);
        return restTemplate.postForObject(url, formEntity, responseType);
    }

    public static HttpEntity<String> makePostTextEntiry(Map<String, ? extends Object> param) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
//        headers.add(HEADER_CRYPTOGRAPH, sha1(platNo, param));
//        headers.add(HEADER_AUTH_ID,
//                configService.getPlatValue(platNo, ConfigService.PLAT_AUTH_ID));
        HttpEntity<String> formEntity = new HttpEntity<String>(
                makeGetParamContent(param), headers);
        log.info("rest-post-text-请求参数:{}", formEntity.toString());
        return formEntity;
    }

    /**
     * 生成get方法的url
     *
     * @param param      参数键值对
     * @param isAllParam 是否是所有参数，为true，字符串以?开头，否则以&开头
     * @param excluedes  map中不作为参数的key列表
     * @return
     */
    public static String makeGetUrl(Map<String, String> param, boolean isAllParam, String... excluedes) {
        StringBuilder search = new StringBuilder();
        if (isAllParam) {
            search.append("?");
        } else {
            search.append("&");
        }
        List<String> excludeKeys = Arrays.asList(excluedes);
        param.forEach((key, value) -> {
            if (!excludeKeys.contains(key)) {
                search.append(key).append("={").append(key).append("}&");
            }
        });
        if (search.length() > 0) {
            search.deleteCharAt(search.length() - 1);
        }
        return search.toString();
    }

    /**
     * 生成Get请求内容
     *
     * @param param
     * @param excluedes
     * @return
     */
    public static String makeGetParamContent(Map<String, ? extends Object> param, String... excluedes) {
        StringBuilder content = new StringBuilder();
        List<String> excludeKeys = Arrays.asList(excluedes);
        param.forEach((key, v) -> {
            content.append(key).append("=").append(v).append("&");
        });
        if (content.length() > 0) {
            content.deleteCharAt(content.length() - 1);
        }
        return content.toString();
    }
}