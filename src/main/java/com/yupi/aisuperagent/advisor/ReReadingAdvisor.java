package com.yupi.aisuperagent.advisor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.advisor.api.AdvisedRequest;
import org.springframework.ai.chat.client.advisor.api.AdvisedResponse;
import org.springframework.ai.chat.client.advisor.api.CallAroundAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAroundAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAroundAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAroundAdvisorChain;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.MessageAggregator;
import org.springframework.ai.model.ModelOptionsUtils;
import reactor.core.publisher.Flux;
public class ReReadingAdvisor implements CallAroundAdvisor, StreamAroundAdvisor {

	private AdvisedRequest before(AdvisedRequest advisedRequest) { // <1>

		Map<String, Object> advisedUserParams = new HashMap<>(advisedRequest.userParams());
		advisedUserParams.put("re2_input_query", advisedRequest.userText());

		return AdvisedRequest.from(advisedRequest)
			.userText("""
			    {re2_input_query}
			    Read the question again: {re2_input_query}
			    """)
			.userParams(advisedUserParams)
			.build();
	}

	@Override
	public AdvisedResponse aroundCall(AdvisedRequest advisedRequest, CallAroundAdvisorChain chain) { // <2>
		return chain.nextAroundCall(this.before(advisedRequest));
	}

	@Override
	public Flux<AdvisedResponse> aroundStream(AdvisedRequest advisedRequest, StreamAroundAdvisorChain chain) { // <3>
		return chain.nextAroundStream(this.before(advisedRequest));
	}

	@Override
	public int getOrder() { // <4>
		return 0;
	}

    @Override
    public String getName() { // <5>
		return this.getClass().getSimpleName();
	}
}