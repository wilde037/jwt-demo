package com.xxxx;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestJwt {
    @Test
    public void test(){
//创建一个JwtBuilder对象
        JwtBuilder jwtBuilder = Jwts.builder()
//声明的标识{"jti":"888"}
                .setId("888")
//主体，用户{"sub":"Rose"}
                .setSubject("Rose")
//创建日期{"ita":"yjxxtxx"}
                .setIssuedAt(new Date())
//签名手段，参数1：算法，参数2：盐
                .signWith(SignatureAlgorithm.HS256,"yjxxt");
//获取jwt的token
        String token = jwtBuilder.compact();
        System.out.println(token);
//三部分的base64解密
        System.out.println("--------------------");
        String[] split = token.split("\\.");
        System.out.println(Base64Codec.BASE64.decodeToString(split[0]));
        System.out.println(Base64Codec.BASE64.decodeToString(split[1]));
//无法解密
        System.out.println(Base64Codec.BASE64.decodeToString(split[2]));
    }
    @Test
    public void testCreatTokenHasExp() {
//当前系统时间的长整型
        long now = System.currentTimeMillis();
//过期时间，这里是1分钟后的时间长整型
        long exp = now + 60 * 1000;
//创建一个JwtBuilder对象
        JwtBuilder jwtBuilder = Jwts.builder()
//声明的标识{"jti":"888"}
                .setId("888")
//主体，用户{"sub":"Rose"}
                .setSubject("Rose")
//创建日期{"ita":"yjxxtxx"}
                .setIssuedAt(new Date())
//签名手段，参数1：算法，参数2：盐
                .signWith(SignatureAlgorithm.HS256, "yjxxt")
//设置过期时间
                .setExpiration(new Date(exp));
//获取jwt的token
        String token = jwtBuilder.compact();
        System.out.println(token);
    }
    @Test
    public void testParseTokenHasExp() {
//token
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiJSb3NlIiwiaWF0IjoxNjYwMDMxODE4LCJleHAiOjE2NjAwMzE4Nzh9.CvA9c_XUa8TwC32kGaAchyBh78MEA-6sDc0CkseEbUc";
        Claims claims = Jwts.parser()
                .setSigningKey("yjxxt")
                .parseClaimsJws(token)
                .getBody();
//打印声明的属性
        System.out.println("id:" + claims.getId());
        System.out.println("subject:" + claims.getSubject());
        System.out.println("issuedAt:" + claims.getIssuedAt());
        DateFormat sf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("签发时间:"+sf.format(claims.getIssuedAt()));
        System.out.println("过期时间:"+sf.format(claims.getExpiration()));
        System.out.println("当前时间:"+sf.format(new Date()));
    }
//    Sticky Fingers 找老头爆金币？
//    我好兄弟只说sb话
//    人生的终极目标是什么呢 去港口整点新鲜薯条
}

//eyJhbGciOiJIUzI1NiJ9.
// eyJqdGkiOiI4ODgiLCJzdWIiOiJSb3NlIiwiaWF0IjoxNjYwMDI5ODEwfQ.
// EWZnmSHUU4PaZR0nn4XpKHY7M9GnOBR88w3x97mowus