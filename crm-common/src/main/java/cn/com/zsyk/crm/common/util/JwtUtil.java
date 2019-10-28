package cn.com.zsyk.crm.common.util;

import static cn.com.zsyk.crm.common.constant.JwtConsts.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.com.zsyk.crm.common.constant.JwtConsts;
import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.exception.JwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.PrematureJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.Base64Codec;

/**
 * JWT的工具类
 * 
 * @author
 */
public class JwtUtil {
	static final String HEADER_STRING = "Authorization";

	/**
	 * 生成Token
	 * 
	 * @param userId用户id
	 * @return Token字符串
	 */
	public static String createToken(String userId) {
		Map<String, Object> header = new HashMap<>();
		header.put("typ", "JWT");

		Map<String, Object> claims = new HashMap<>();
		claims.put(USERID, userId);

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		JwtBuilder builder = Jwts.builder().setHeader(header).setClaims(claims) // 自定义属性
				.setIssuer(ISSUER) // 签发者
				.setIssuedAt(now) // 签发时间
				.setNotBefore(now) // 生效时间
				.setExpiration(new Date(nowMillis + EXPIRATION)) // 失效时间
				.signWith(SignatureAlgorithm.HS256, createKey()); // 签名算法以及密匙

		return builder.compact();
	}

	/**
	 * 验证Token，如果验证失败直接抛出自定义的JwtException异常,由全局异常处理器处理
	 * @param token
	 * @return
	 */
	public static Claims validateToken(String token) throws Exception {
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(createKey()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			if (e instanceof IllegalArgumentException) {
				throw new JwtException("缺少Token信息！", e);
			} else if (e instanceof MalformedJwtException) {
				throw new JwtException("数据结构非法！", e);
			} else if (e instanceof UnsupportedJwtException) {
				throw new JwtException("含有非法信息！", e);
			} else if (e instanceof SignatureException) {
				throw new JwtException("签名信息非法！", e);
			} else if (e instanceof ExpiredJwtException) {
				throw new JwtException("超过有效期！", e);
			} else if (e instanceof PrematureJwtException) {
				throw new JwtException("未到生效期！", e);
			} else {
				throw new JwtException(e.getMessage(), e);
			}
		}
		
		if(!ISSUER.equals(claims.getIssuer())){
			throw new JwtException("签发者信息非法！");
		}
		
		String userId = (String)claims.get(USERID);
		if (userId == null || userId.isEmpty())
			throw new JwtException("缺少用户信息！");

		if (!userId.equals(ContextContainer.getContext().getUserId())) {
			throw new JwtException("用户信息非法！");
		}
		
		return claims;
	}

	/**
	 * 生成密钥
	 */
	private static String createKey() {
		return Base64Codec.BASE64.encode(JwtConsts.SECRET);
	}
}
