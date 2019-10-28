package cn.com.zsyk.crm.ecif.bo.bom;

public class TagBasBom {

	private String custNo;
	/** 客户号 */

	private String sex;
	/** 性别 */

	private String nativePlace;
	/** 籍贯 */

	private String age;
	/** 年龄(当前日期-出生日期 或者 死亡日期-出生日期) */

	private String birthYear;
	/** 出生年份（用于80后、90后、70后标签等 */

	private String occupation;
	/** 职业 */

	private String trade;
	/** 行业 */

	private String degree;
	/** 学位 */

	private String nation;
	/** 民族 */

	private String birthPlace;
	/** 出生地 */

	private String nationality;
	/** 国籍 */

	private String marrigeSts;
	/** 婚否 */

	private String birthDate;
	/** 生日 */

	private String schoolNam;
	/** 学校 */
	private String politSts;

	/** 政治面貌 */
	private String hobby;
	/**兴趣爱好习惯*/

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getMarrigeSts() {
		return marrigeSts;
	}

	public void setMarrigeSts(String marrigeSts) {
		this.marrigeSts = marrigeSts;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getSchoolNam() {
		return schoolNam;
	}

	public void setSchoolNam(String schoolNam) {
		this.schoolNam = schoolNam;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getTrade() {
		return trade;
	}

	public void setTrade(String trade) {
		this.trade = trade;
	}

	public String getPolitSts() {
		return politSts;
	}

	public void setPolitSts(String politSts) {
		this.politSts = politSts;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

}
