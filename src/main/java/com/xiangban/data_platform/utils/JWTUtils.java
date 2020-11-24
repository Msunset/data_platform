package com.xiangban.data_platform.utils;


import com.xiangban.data_platform.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * jwt工具类
 */
public class JWTUtils {

    /**
     * 过期时间
     */
    private static final long EXPIRE= 60000 * 60 * 24 * 7 ;
//    private static final long EXPIRE= 1 ;

    /**
     * 加密密钥
     */
    private static final String SECRET = "xiangban365.com";

    /**
     * 令牌前缀
     */
    private static final String TOKEN_PREFIX="xiangban";

    /**
     * subject
     */
    private static final String SUBJECT="xiangban";


    /**
     * 根据用户信息生成密令
     * @param user
     * @return
     */
    public static String getJsonWebToken(User user){
       String token = Jwts.builder().setSubject(SUBJECT)
                .claim("nickname",user.getNickName())
                .claim("userid",user.getUserid())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE))
                .signWith(SignatureAlgorithm.HS256,SECRET).compact();

       return token;
    }
    /**
     * 校验token方法
     * @param token
     * @return
     */
    public static Claims checkJWT(String token){

        try{
            final Claims claims= Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            return claims;
        }catch (Exception e){
            return null;
        }



    }

}
