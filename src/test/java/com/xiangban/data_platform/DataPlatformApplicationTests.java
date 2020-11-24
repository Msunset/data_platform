package com.xiangban.data_platform;

import com.xiangban.data_platform.domain.User;
import com.xiangban.data_platform.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DataPlatformApplicationTests {

	@Test
	public void testGeneJWT(){

		User user = new User();
		user.setUserid(66);
		user.setNickName("ABC");

		String jsonWebToken = JWTUtils.getJsonWebToken(user);
		System.out.println(jsonWebToken);

		try {
			Thread.sleep(4000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Claims claims = JWTUtils.checkJWT(jsonWebToken);
		System.out.println(claims.get("name"));

	}

}
