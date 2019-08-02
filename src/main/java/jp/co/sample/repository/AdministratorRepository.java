package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * @author yukihiro.yokogawa 管理者情報を書き込むリポジトリクラスです.
 */

@Repository
public class AdministratorRepository {

	// GI
	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mailAddress"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};

	public void insert(Administrator administrator) {

		SqlParameterSource param
		=new BeanPropertySqlParameterSource(administrator);
		
		String sql = "INSERT INTO administrator"
				//挿入するカラム名
				+ " (name,mail_address,password)"
				//挿入する情報
				+ " VALUES"
				+ " (name=:name,mail_address=:mailAddress,password=:password);";
		
		template.update(sql, param);
	}
	
	
	/**
	 * @param mailAddress
	 * @param Password
	 * @return
	 * メールアドレスとパスワードから管理者の情報を1件検索するメソッドです.
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress, String Password) {
			
		String sql = "SELECT id,name,mail_address FROM administrators "
					+ "WHERE mail_address = :mail_adress AND password = :password";

		SqlParameterSource param
		= new MapSqlParameterSource().addValue("mail_address", mailAddress).addValue("password", Password);
					
		List<Administrator> administrator
		=template.query(sql, param,ADMINISTRATOR_ROW_MAPPER);
			
		if(administrator.size()==0) {
			return null;
		}else {
			return administrator.get(0);

		}
	}

}
