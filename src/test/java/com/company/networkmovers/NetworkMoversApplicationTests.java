package com.company.networkmovers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.Map;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@SpringBootTest
class NetworkMoversApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;

	@Test
	void testLoginApi() throws Exception {
		System.out.println("=== TESTING DIRECT AUTHENTICATION ===");
		try {
			AuthenticationManager authManager = context.getBean(AuthenticationManager.class);
			Authentication auth = authManager.authenticate(
					new UsernamePasswordAuthenticationToken("admin", "adminpassword")
			);
			System.out.println("=== AUTHENTICATION SUCCESS: " + auth.isAuthenticated() + " ===");
			System.out.println("Principal: " + auth.getPrincipal());
		} catch (Exception e) {
			System.out.println("=== AUTHENTICATION FAILED ===");
			e.printStackTrace();
		}
	}



	@Test
	void inspectUsers() {
		System.out.println("=== INSPECTING USERS ===");
		try {
			List<Map<String, Object>> users = jdbcTemplate.queryForList("SELECT id, username, email, password, enabled FROM tbl_users");
			users.forEach(u -> System.out.println("User: id=" + u.get("id") + ", username=" + u.get("username") + ", email=" + u.get("email") + ", password=" + u.get("password") + ", enabled=" + u.get("enabled")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("=========================");
	}

	@Test
	void testPasswordMatch() {
		System.out.println("=== TESTING PASSWORD MATCH ===");
		org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder encoder = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
		boolean matches = encoder.matches("adminpassword", "$2a$10$.Mmy4iwt.I26UopA/dugI.BpstWMwk93dJM2KxLI.2zZNt4r.SkhW");
		System.out.println("=== PASSWORD MATCH RESULT: " + matches + " ===");
	}


	@Test
	void contextLoads() {
		System.out.println("=== INSPECTING AUTO-CONFIGURATION ===");
		if (context instanceof ConfigurableApplicationContext) {
			ConditionEvaluationReport report = ConditionEvaluationReport.get(
					((ConfigurableApplicationContext) context).getBeanFactory()
			);
			
			System.out.println("--- Condition Evaluation Report ---");
			Map<String, ConditionEvaluationReport.ConditionAndOutcomes> outcomes = report.getConditionAndOutcomesBySource();
			boolean foundAny = false;
			for (String key : outcomes.keySet()) {
				if (key.toLowerCase().contains("flyway") || key.toLowerCase().contains("way")) {
					System.out.println("Source matches: " + key);
					ConditionEvaluationReport.ConditionAndOutcomes conds = outcomes.get(key);
					System.out.println("  Is Full Match: " + conds.isFullMatch());
					conds.forEach(outcome -> {
						System.out.println("  Condition: " + outcome.getCondition().getClass().getSimpleName());
						System.out.println("    Outcome: " + outcome.getOutcome().getMessage());
					});
					foundAny = true;
				}
			}
			if (!foundAny) {
				System.out.println("No sources matching 'flyway' or 'way' found in outcomes!");
			}
		}
		System.out.println("======================================");
	}

}
