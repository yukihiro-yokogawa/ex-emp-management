package jp.co.sample.domain;

/**
 * @author yukihiro.yokogawa
 * 管理者情報を表すドメインです.
 */
public class Adiministrator {

	// ID
	private Integer id;
	// 名前
	private String name;
	// メールアドレス
	private String mailAddress;
	// パスワード
	private String password;

	@Override
	public String toString() {
		return "Adiministrator [id=" + id + ", name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}