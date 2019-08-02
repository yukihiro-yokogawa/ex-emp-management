package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/**
 * @author yukihiro.yokogawa
 * 従業員情報を検索するクラスです.
 */
@Repository
public class EmployeeRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs,i)->{
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characeristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		return employee;
	};
	
	
	/**
	 * @return
	 * 従業員の情報を全検索するメソッドです.
	 */
	public List<Employee> findAll(){
		
		String sql="SELECT"
				//検索、検索する情報
				+ " id,name,image,gender,hire_date,mail_address,zip_code"
				+ ",address,telephone,salary,characteristics,devendents_count"
				//参照したテーブルの情報を入社日の昇順で表示します
				+ " FROM employees ORDER BY hire_date;";
		
		
		List<Employee> employeeList
		=template.query(sql, EMPLOYEE_ROW_MAPPER);
		
		return employeeList;
	}
	
	/**
	 * @param id
	 * @return
	 * 従業員の情報を1件検索するメソッドです.
	 */
	public Employee load(Integer id) {
		
		String sql = "SELECT "
				//検索、表示する情報
				+ " id,name,image,gender,hire_date,mail_address,zip_code"
				+ ",address,telephone,salary,characteristics,devendents_count"
				//参照したテーブルの情報を入社日の昇順で表示します
				+ " FROM employees WHERE id=:id;";
		
		SqlParameterSource param
		= new MapSqlParameterSource().addValue("id",id);
		
		Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
		
		return employee;
	}
	
	/**
	 * @param employee
	 * 従業員の扶養人数を更新するメソッドです.
	 */
	public void	update(Employee employee) {
		
		SqlParameterSource param
		= new BeanPropertySqlParameterSource(employee);
		
		String sql = "UPDATE employees SET"
				//更新する情報と参照するテーブルを著す
				+ " dependents_count = :dependentsCount FROM employees;";
		
		template.update(sql, param);
		
	}
	
}
