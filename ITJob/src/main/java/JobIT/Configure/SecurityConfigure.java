package JobIT.Configure;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private CustomLoginSuccessHandler sucessHandler;

	@Autowired
	private DataSource dataSource;

	private final String USERS_QUERY = "SELECT email, password, '1' as enabled FROM Kamrul_Job_User WHERE email = ? and is_enabled '1'";
	private final String ROLES_QUERY = "SELECT u.email, r.role_name FROM Kamrul_Job_User u inner join Kamrul_Job_User_Role ur on (u.user_id = ur.user_id) inner join Kamrul_Job_Role r on (ur.role_id = r.role_id) WHERE u.email = ?";

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(USERS_QUERY).authoritiesByUsernameQuery(ROLES_QUERY)
				.dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/login").permitAll().antMatchers("/register")
				.permitAll().antMatchers("/confirm").permitAll().antMatchers("/home/**")
				.hasAnyAuthority("SUPER_USER", "ADMIN_USER", "SITE_USER").antMatchers("/admin/**")
				.hasAnyAuthority("SUPER_USER", "ADMIN_USER").and().csrf().disable().formLogin()
				.loginPage("/loginsubmit").failureUrl("/login?error=true")
				// .defaultSuccessUrl("/home")
				.successHandler(sucessHandler).usernameParameter("email").passwordParameter("password").and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").and().rememberMe()
				.rememberMeParameter("remember-me").tokenRepository(persistantTokenRepository())
				.tokenValiditySeconds(600 * 60).and().exceptionHandling().accessDeniedPage("/access_denied");
	}

	@Bean
	public PersistentTokenRepository persistantTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);
		return db;
	}
}
