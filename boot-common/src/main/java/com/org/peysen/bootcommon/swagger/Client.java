package com.org.peysen.bootcommon.swagger;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import io.swagger.models.Info;
import io.swagger.models.Swagger;
import io.swagger.models.Tag;

import java.io.IOException;

/**
 * @Auther: peimengmeng
 * @Date: 2020/12/29_20:09
 * @Desc:
 */
public class Client {

    public static void main(String[] args) throws IOException {
        Swagger swagger = new Swagger();
        swagger.setBasePath("/");
        swagger.setHost("openapi.crov.com");
        swagger.setSwagger("2.0");
        Info info = new Info();
        info.setVersion("3.0.0");
        info.setTitle("Basic API Documentation");
        swagger.setInfo(info);
        Tag tag = new Tag();
        tag.setName("Get Basic Information");
        tag.setDescription("Api Basic Info Controller");
        swagger.setTags(Lists.newArrayList(tag));
        String s = JSON.toJSONString(swagger);


        String str = "{\"swagger\":\"2.0\",\"info\":{\"version\":\"3.0.0\",\"title\":\"Basic API Documentation\"},\"host\":\"openapi.crov.com\"," +
                "\"basePath\":\"/\",\"tags\":[{\"name\":\"Get Basic Information\",\"description\":\"Api Basic Info Controller\"}]," +
                "\"paths\":{" +
                "   \"/api/country/list\":{\"get\":{\"tags\":[\"Get Basic Information\"],\"summary\":\"GET Country/Region List\",\"description\":\"When obtaining global country/region information list, encryptRegionId is empty\",\"operationId\":\"getBaseRegionsUsingGET\",\"produces\":[\"*/*\"],\"parameters\":[{\"name\":\"encryptRegionId\",\"in\":\"query\",\"description\":\"Parent RegionId\",\"required\":false,\"type\":\"string\",\"allowEmptyValue\":false,\"x-example\":\"aCUKfpBujbFh\"}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"$ref\":\"#/definitions/API request response«List«Business result«List«BaseRegion»»»»\"}},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"},\"404\":{\"description\":\"Not Found\"}},\"deprecated\":false}},\"/api/platform/list\":{\"get\":{\"tags\":[\"Get Basic Information\"],\"summary\":\"Get the List of the Third Party Marketplace/Platform\",\"operationId\":\"listCategoriesUsingGET\",\"produces\":[\"*/*\"],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"$ref\":\"#/definitions/API request response«Business result«List«DSPlatformVO»»»\"}},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"},\"404\":{\"description\":\"Not Found\"}},\"deprecated\":false}}},\"definitions\":{\"API request response«Business result«List«DSPlatformVO»»»\":{\"type\":\"object\",\"properties\":{\"businessData\":{\"description\":\"Specific business data\",\"$ref\":\"#/definitions/Business result«List«DSPlatformVO»»\"},\"responseCode\":{\"type\":\"string\",\"description\":\"Response Code  000000-SUCCESS、999999-Fail\"},\"responseMessage\":{\"type\":\"string\",\"description\":\"Response Message\"}},\"title\":\"API request response«Business result«List«DSPlatformVO»»»\"},\"API request response«List«Business result«List«BaseRegion»»»»\":{\"type\":\"object\",\"properties\":{\"businessData\":{\"type\":\"array\",\"description\":\"Specific business data\",\"items\":{\"$ref\":\"#/definitions/Business result«List«BaseRegion»»\"}},\"responseCode\":{\"type\":\"string\",\"description\":\"Response Code  000000-SUCCESS、999999-Fail\"},\"responseMessage\":{\"type\":\"string\",\"description\":\"Response Message\"}},\"title\":\"API request response«List«Business result«List«BaseRegion»»»»\"},\"BaseRegion\":{\"type\":\"object\",\"required\":[\"encryptRegionId\",\"regionNameCn\",\"regionNameEn\"],\"properties\":{\"encryptRegionId\":{\"type\":\"string\",\"description\":\"Country/Region encrypt id\"},\"regionCode\":{\"type\":\"string\",\"description\":\"Country/Region Code\"},\"regionCodeAlphaThree\":{\"type\":\"string\",\"description\":\"ISO 3166 Three-letter country code\"},\"regionNameCn\":{\"type\":\"string\",\"description\":\"Country/Region CN\"},\"regionNameEn\":{\"type\":\"string\",\"description\":\"Country/Region EN\"}},\"title\":\"BaseRegion\"},\"Business result«List«BaseRegion»»\":{\"type\":\"object\",\"properties\":{\"businessMessage\":{\"type\":\"string\",\"description\":\"Message  for business results\"},\"businessStatus\":{\"type\":\"string\",\"description\":\"Status code for business results\"},\"data\":{\"type\":\"array\",\"description\":\"Business results\",\"items\":{\"$ref\":\"#/definitions/BaseRegion\"}}},\"title\":\"Business result«List«BaseRegion»»\"},\"Business result«List«DSPlatformVO»»\":{\"type\":\"object\",\"properties\":{\"businessMessage\":{\"type\":\"string\",\"description\":\"Message  for business results\"},\"businessStatus\":{\"type\":\"string\",\"description\":\"Status code for business results\"},\"data\":{\"type\":\"array\",\"description\":\"Business results\",\"items\":{\"$ref\":\"#/definitions/DSPlatformVO\"}}},\"title\":\"Business result«List«DSPlatformVO»»\"},\"DSPlatformVO\":{\"type\":\"object\",\"required\":[\"platformId\",\"platformName\"],\"properties\":{\"platformId\":{\"type\":\"string\",\"description\":\"The id of the marketplace or platform where the order generated, like Amazon, eBay, etc.\"},\"platformName\":{\"type\":\"string\",\"description\":\"The platform name.\"}},\"title\":\"DSPlatformVO\",\"description\":\"The Third Party Marketplace/Platform API\"}}}";

        ObjectMapper objectMapper = new ObjectMapper();

        Swagger swagger1 = objectMapper.readValue(s, Swagger.class);

        System.out.println(swagger1.getBasePath());



        System.out.println(swagger.getBasePath());
    }
}
