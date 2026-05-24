package com.company.networkmovers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

@SpringBootTest
class NetworkMoversApplicationTests {

	@Autowired
	private ApplicationContext context;

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
