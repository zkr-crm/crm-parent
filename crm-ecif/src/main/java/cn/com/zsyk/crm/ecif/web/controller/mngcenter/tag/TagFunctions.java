package cn.com.zsyk.crm.ecif.web.controller.mngcenter.tag;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dto.Result;

@RestController
public class TagFunctions {

	/**
	 * @api {get} /crm/ecif/tagmng/function/calAge 通过出生、死亡日期计算年龄
	 * @apiName getAge
	 * @apiGroup Tag
	 * @apiParam {String} birthday 出生日期
	 * @apiParam {String} deathday 死亡日期
	 *
	 * @apiSuccess {Object} data 年龄数据
	 * 
	 */

	/**
	 * 主要用于规则的function函数内
	 * 
	 * @param birthday
	 * @param deathday
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/tagmng/function/calAge", method = RequestMethod.GET)
	public Result getAge(String birthday, String deathday) {

		Result res = new Result();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

		Date birth;
		try {
			birth = simpleDateFormat.parse(birthday);

			Date death;
			long age_l;
			float age;
			if (deathday != null && !deathday.equals("")) {
				death = simpleDateFormat.parse(deathday);
				if (new Date().compareTo(death) < 0) {
					return res.fail("死亡日期非法!");
				}
				age_l = death.getTime() - birth.getTime();
			} else {
				age_l = new Date().getTime() - birth.getTime();
				if (age_l < 0) {
					return res.fail("出生日期非法!");
				}
			}

			age = (float) age_l / 1000 / 3600 / 24 / 365;
			res.setData(age);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return res.exception("日期格式转换过程异常!");
		}

		return res;
	}

}
